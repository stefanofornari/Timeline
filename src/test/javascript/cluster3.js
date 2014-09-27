options = {
    "style": "dot",
    "cluster": true,
    "animate": false,
    "clusterMaxItems": 0
};

// Instantiate our timeline object.
var timeline = new links.Timeline(document.getElementById('diskone'));

timeline.setOptions(options);
timeline.setData(data);