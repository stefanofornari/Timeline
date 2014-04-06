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


import static org.assertj.core.api.BDDAssertions.then;
import org.junit.Test;
import org.mozilla.javascript.NativeArray;

import ste.xtest.js.BugFreeJavaScript;


/**
 *
 * @author ste
 */
public class BugFreeItemsByGroupStackEventsOn extends BugFreeJavaScript {

    public static final String OPTION_MAX_CLUSTERING_ITEM = "maxClusterItems";

    public BugFreeItemsByGroupStackEventsOn() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
        loadScript("src/test/javascript/timeline.testitem.js");
    }

    @Test
    public void stackCalculateFinalWithGroupsAndEmptyItemsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");

        //
        // empty array first
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal({});");
        then(ret.getLength()).isZero();
    }

    @Test
    public void stackCalculateFinalWithGroupsAndEmptyItemsAxisAtBottom() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.axisOnTop = false; timeline.setOptions(options);");

        //
        // empty array first
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal({});");
        then(ret.getLength()).isZero();
    }

    @Test
    public void stackCalculateFinalWithGroupsWithCollisionsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");

        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items1);");
        then(ret.getLength()).isEqualTo(9);

        then(exec("ret[0].item.title;")).isEqualTo("file5");
        then(exec("ret[0].top;")).isEqualTo(75.0);
        then(exec("ret[0].item.group.itemsHeight;")).isEqualTo(20.0);

        then(exec("ret[1].item.title;")).isEqualTo("file1");
        then(exec("ret[1].top;")).isEqualTo(75.0);

        then(exec("ret[2].item.title;")).isEqualTo("file3");
        then(exec("ret[2].top;")).isEqualTo(75.0);

        then(exec("ret[3].item.title;")).isEqualTo("file2");
        then(exec("ret[3].top;")).isEqualTo(85.0);

        then(exec("ret[4].item.title;")).isEqualTo("file4");
        then(exec("ret[4].top;")).isEqualTo(95.0);

        then(exec("ret[5].item.title;")).isEqualTo("file6");
        then(exec("ret[5].top;")).isEqualTo(75.0);

        then(exec("ret[6].item.title;")).isEqualTo("image1");
        then(exec("ret[6].top;")).isEqualTo(105.0);
        then(exec("ret[6].item.group.itemsHeight;")).isEqualTo(10.0);

        then(exec("ret[7].item.title;")).isEqualTo("image2");
        then(exec("ret[7].top;")).isEqualTo(115.0);

        then(exec("ret[8].item.title;")).isEqualTo("image3");
        then(exec("ret[8].top;")).isEqualTo(105.0);
    }

    @Test
    public void stackCalculateFinalWithGroupsWithCollisionsAxisAtBottom() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.axisOnTop = false; timeline.setOptions(options);");

        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items1);");
        then(ret.getLength()).isEqualTo(9);

        then(exec("ret[0].item.title;")).isEqualTo("file5");
        then(exec("ret[0].top;")).isEqualTo(1970.0);
        then(exec("ret[0].item.group.itemsHeight;")).isEqualTo(20.0);

        then(exec("ret[1].item.title;")).isEqualTo("file1");
        then(exec("ret[1].top;")).isEqualTo(1970.0);

        then(exec("ret[2].item.title;")).isEqualTo("file3");
        then(exec("ret[2].top;")).isEqualTo(1970.0);

        then(exec("ret[3].item.title;")).isEqualTo("file2");
        then(exec("ret[3].top;")).isEqualTo(1960.0);

        then(exec("ret[4].item.title;")).isEqualTo("file4");
        then(exec("ret[4].top;")).isEqualTo(1950.0);

        then(exec("ret[5].item.title;")).isEqualTo("file6");
        then(exec("ret[5].top;")).isEqualTo(1970.0);

        then(exec("ret[6].item.title;")).isEqualTo("image1");
        then(exec("ret[6].top;")).isEqualTo(1940.0);
        then(exec("ret[6].item.group.itemsHeight;")).isEqualTo(10.0);

        then(exec("ret[7].item.title;")).isEqualTo("image2");
        then(exec("ret[7].top;")).isEqualTo(1930.0);

        then(exec("ret[8].item.title;")).isEqualTo("image3");
        then(exec("ret[8].top;")).isEqualTo(1940.0);
    }

    @Test
    public void stackCalculateFinalWithGroupsWithoutCollisionsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");

        //
        // with items with collisions now
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items2);");
        then(ret.getLength()).isEqualTo(4);
        then(exec("ret[0].item.group.itemsHeight;")).isEqualTo(10.0);
        then(exec("ret[1].item.group.itemsHeight;")).isEqualTo(10.0);
    }

}