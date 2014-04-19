// Instantiate our timeline object.

window.location='src/test/html/timeline1.html';
var diskone = document.getElementById('diskone');
timeline1 = new links.Timeline(diskone);
timeline2 = new links.Timeline(diskone, {});
timeline3 = new links.Timeline(diskone, {zoomMin: 50, zoomable: false});
