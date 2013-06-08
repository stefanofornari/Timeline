options = {
    "style": "dot",
    "cluster": true
};

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));
timeline.setOptions(options);

// Draw our timeline with the created data and options
timeline.setData(data);