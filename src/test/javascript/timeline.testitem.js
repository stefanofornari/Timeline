/*
 * Copyright (C) 2013 Stefano Fornari.
 * All Rights Reserved.  No use, copying or distribution of this
 * work may be made except in accordance with a valid license
 * agreement from Stefano Fornari.  This notice must be
 * included on all copies, modifications and derivatives of this
 * work.
 *
 * STEFANO FORNARI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. STEFANO FORNARI SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * Declare a unique namespace for CHAP's Common Hybrid Visualisation Library,
 * "links"
 */
if (typeof one === 'undefined') {
    test = {};
    // important: do not use var, as "var ste = {};" will overwrite
    //            the existing links variable value with undefined in IE8, IE7.
}


/**
 * @constructor test.Item
 * @extends links.Timeline.Item
 * @param {Object} data       Object containing parameters start, end
 *                            content, group. type, group.
 * @param {Object} [options]  Options to set initial property values
 *                                {Number} top
 *                                {Number} left
 *                                {Number} width
 *                                {Number} height
 */
test.Item = function (data, options) {
    for (var p in data) {
        console.info("p: "+p+'('+data[p]+')');
        console.info(data.title);
        this[p] = data[p];
    }
    links.Timeline.Item.call(this, data, options);
};

test.Item.prototype = new links.Timeline.Item();

/**
 * Reflow the Item: retrieve its actual size from the DOM
 * @return {boolean} resized    returns true if the axis is resized
 * @override
 */
test.Item.prototype.reflow = function () {
    var dom = this.dom,
        dotHeight = dom.dot.offsetHeight,
        dotWidth = dom.dot.offsetWidth,
        lineWidth = dom.line.offsetWidth,
        resized = (
            (this.dotHeight != dotHeight) ||
                (this.dotWidth != dotWidth) ||
                (this.lineWidth != lineWidth)
            );

    this.dotHeight = dotHeight;
    this.dotWidth = dotWidth;
    this.lineWidth = lineWidth;

    return resized;
};

/**
 * Select the item
 * @override
 */
test.Item.prototype.select = function () {
    var dom = this.dom;
    links.Timeline.addClassName(dom, 'timeline-event-selected');
    links.Timeline.addClassName(dom.line, 'timeline-event-selected');
    links.Timeline.addClassName(dom.dot, 'timeline-event-selected');
};

/**
 * Unselect the item
 * @override
 */
test.Item.prototype.unselect = function () {
    var dom = this.dom;
    links.Timeline.removeClassName(dom, 'timeline-event-selected');
    links.Timeline.removeClassName(dom.line, 'timeline-event-selected');
    links.Timeline.removeClassName(dom.dot, 'timeline-event-selected');
};

/**
 * Creates the DOM for the item, depending on its type
 * @return {Element | undefined}
 * @override
 */
test.Item.prototype.createDOM = function () {
    // background box
    var divBox = document.createElement("DIV");
    divBox.style.position = "absolute";
    divBox.style.left = this.left + "px";
    divBox.style.top = this.top + "px";

    // contents box (inside the background box). used for making margins
    var divContent = document.createElement("DIV");
    divContent.className = "timeline-event-content";
    divContent.innerHTML = this.title;
    divBox.appendChild(divContent);

    // line to axis
    var divLine = document.createElement("DIV");
    divLine.style.position = "absolute";
    divLine.style.width = "0px";
    // important: the vertical line is added at the front of the list of elements,
    // so it will be drawn behind all boxes and ranges
    divBox.line = divLine;

    // dot on axis
    var divDot = document.createElement("DIV");
    divDot.style.position = "absolute";
    divDot.style.width  = "0px";
    divDot.style.height = "0px";
    divBox.dot = divDot;

    this.dom = divBox;
    this.updateDOM();

    return divBox;
};

/**
 * Append the items DOM to the given HTML container. If items DOM does not yet
 * exist, it will be created first.
 * @param {Element} container
 * @override
 */
test.Item.prototype.showDOM = function (container) {
    var dom = this.dom;
    if (!dom) {
        dom = this.createDOM();
    }

    if (dom.parentNode != container) {
        if (dom.parentNode) {
            // container is changed. remove from old container
            this.hideDOM();
        }

        // append to this container
        container.appendChild(dom);
        container.insertBefore(dom.line, container.firstChild);
        // Note: line must be added in front of the this,
        //       such that it stays below all this
        container.appendChild(dom.dot);
        this.rendered = true;
    }
};

/**
 * Remove the items DOM from the current HTML container, but keep the DOM in
 * memory
 * @override
 */
test.Item.prototype.hideDOM = function () {
    var dom = this.dom;
    if (dom) {
        if (dom.parentNode) {
            dom.parentNode.removeChild(dom);
        }
        if (dom.line && dom.line.parentNode) {
            dom.line.parentNode.removeChild(dom.line);
        }
        if (dom.dot && dom.dot.parentNode) {
            dom.dot.parentNode.removeChild(dom.dot);
        }
        this.rendered = false;
    }
};

/**
 * Update the DOM of the item. This will update the content and the classes
 * of the item
 * @override
 */
test.Item.prototype.updateDOM = function () {
    var divBox = this.dom;
    if (divBox) {
        var divLine = divBox.line;
        var divDot = divBox.dot;

        // update contents
        divBox.firstChild.innerHTML = this.title;

        // update class
        divBox.className = "timeline-event timeline-event-box";
        divLine.className = "timeline-event timeline-event-line";
        divDot.className  = "timeline-event timeline-event-dot";

        if (this.isCluster) {
            links.Timeline.addClassName(divBox, 'timeline-event-cluster');
            links.Timeline.addClassName(divLine, 'timeline-event-cluster');
            links.Timeline.addClassName(divDot, 'timeline-event-cluster');
        }

        // add item specific class name when provided
        if (this.className) {
            links.Timeline.addClassName(divBox, this.className);
            links.Timeline.addClassName(divLine, this.className);
            links.Timeline.addClassName(divDot, this.className);
        }

        // TODO: apply selected className?
    }
};

/**
 * Reposition the item, recalculate its left, top, and width, using the current
 * range of the timeline and the timeline options.
 * @param {links.Timeline} timeline
 * @override
 */
test.Item.prototype.updatePosition = function (timeline) {
    var dom = this.dom;
    if (dom) {
        var left = timeline.timeToScreen(this.start),
            axisOnTop = timeline.options.axisOnTop,
            axisTop = timeline.size.axis.top,
            axisHeight = timeline.size.axis.height,
            boxAlign = (timeline.options.box && timeline.options.box.align) ?
                timeline.options.box.align : undefined;

        dom.style.top = this.top + "px";
        if (boxAlign == 'right') {
            dom.style.left = (left - this.width) + "px";
        }
        else if (boxAlign == 'left') {
            dom.style.left = (left) + "px";
        }
        else { // default or 'center'
            dom.style.left = (left - this.width/2) + "px";
        }

        var line = dom.line;
        var dot = dom.dot;
        line.style.left = (left - this.lineWidth/2) + "px";
        dot.style.left = (left - this.dotWidth/2) + "px";
        if (axisOnTop) {
            line.style.top = axisHeight + "px";
            line.style.height = Math.max(this.top - axisHeight, 0) + "px";
            dot.style.top = (axisHeight - this.dotHeight/2) + "px";
        }
        else {
            line.style.top = (this.top + this.height) + "px";
            line.style.height = Math.max(axisTop - this.top - this.height, 0) + "px";
            dot.style.top = (axisTop - this.dotHeight/2) + "px";
        }
    }
};

/**
 * Check if the item is visible in the timeline, and not part of a cluster
 * @param {Date} start
 * @param {Date} end
 * @return {Boolean} visible
 * @override
 */
test.Item.prototype.isVisible = function (start, end) {
    if (this.cluster) {
        return false;
    }

    return (this.start > start) && (this.start < end);
};

/**
 * Reposition the item
 * @param {Number} left
 * @param {Number} right
 * @override
 */
test.Item.prototype.setPosition = function (left, right) {
    var dom = this.dom;

    dom.style.left = (left - this.width / 2) + "px";
    dom.line.style.left = (left - this.lineWidth / 2) + "px";
    dom.dot.style.left = (left - this.dotWidth / 2) + "px";

    if (this.group) {
        this.top = this.group.top;
        dom.style.top = this.top + 'px';
    }
};

/**
 * Calculate the right position of the item
 * @param {links.Timeline} timeline
 * @return {Number} right
 * @override
 */
test.Item.prototype.getRight = function (timeline) {
    var boxAlign = (timeline.options.box && timeline.options.box.align) ?
        timeline.options.box.align : undefined;

    var left = timeline.timeToScreen(this.start);
    var right;
    if (boxAlign == 'right') {
        right = left;
    }
    else if (boxAlign == 'left') {
        right = (left + this.width);
    }
    else { // default or 'center'
        right = (left + this.width / 2);
    }

    return right;
};


