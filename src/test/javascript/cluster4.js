options = {
    "style": "dot",
    "cluster": true,
    "maxClusterItems": 5
};

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));

//
// We for both image and cluster the same testitem since for now we do not need
// a specific item for each type.
//
timeline.addItemType('image', test.ImageItem);
timeline.addItemType('cluster', test.ImageItem);

timeline.setOptions(options);
timeline.setData(data);
