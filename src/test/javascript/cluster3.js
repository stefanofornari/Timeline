options = {
    "style": "dot",
    "cluster": true,
    "maxClusterItems": 0
};

// Instantiate our timeline object.
var timeline = new links.Timeline(document.getElementById('diskone'));

// Draw our timeline with the created data and options
timeline.setOptions(options);
timeline.setData(data);