console = {
    msg: [],
    log: function(msg) {
        this.msg.push(msg);
    }
};

window.location='src/test/html/timeline1.html';
var diskone = document.getElementById('diskone');
timeline = new links.Timeline(diskone);
timeline.draw([], {min: 15});


