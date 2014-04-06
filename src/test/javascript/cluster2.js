options = {
    "style": "dot",
    "cluster": true,
    "animate": false,
    "maxClusterItems": 3
};

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));

timeline.setOptions(options);
timeline.setData(data);