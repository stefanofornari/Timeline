options = {
    "style": "dot",
    "cluster": true,
    "maxClusterItems": 3
};

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));

// Draw our timeline with the created data and options
timeline.setOptions(options);
timeline.setData(data);