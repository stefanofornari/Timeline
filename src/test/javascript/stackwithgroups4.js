var options = {
    "style": "dot",
    "cluster": true,
    "animate": false,
    "clusterMaxItems": 5
};

var data = [
    {id: 1, title:"file5", start: new Date(2013, 0, 1, 0, 0, 0, 1).getTime(), type: "box"},
    {id: 2, title:"image1", start: new Date(2013, 5, 11, 13, 42, 31,0).getTime(), type: "box"},
    {id: 3, title:"image2", start: new Date(2013, 6, 12, 15, 17, 9, 0).getTime(), type: "box"},
    {id: 4, title:"file1", start: new Date(2013, 7, 24, 7, 32, 12, 0).getTime(), type: "box"},
    {id: 5, title:"image3", start: new Date(2013, 8, 11, 8, 39, 0, 0).getTime(), type: "box"},
    {id: 6, title:"file3", start: new Date(2013, 8, 24, 23, 44, 51, 0).getTime(), type: "box"},
    {id: 7, title:"file2", start: new Date(2013, 9, 6, 0, 44, 16, 0).getTime(), type: "box"},
    {id: 8, title:"file4", start: new Date(2013, 11, 8, 9, 57, 41, 0).getTime(), type: "box"},
    {id: 9, title:"file6", start: new Date(2013, 11, 31, 23, 59, 59, 1).getTime(), type: "box"}
];

var lefts = [
    0, 100, 200, 250, 600, 650, 700, 750, 1000
];

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));
timeline.setOptions(options);
timeline.size.axis = {top: 2000, height: 50};
timeline.size.contentHeight = 1995;
timeline.addItemType('box', test.Item);

var items = [];

for (i = 0; i<data.length; ++i) {
    items.push(timeline.createItem(data[i]));
    items[i].createDOM();
    items[i].setPosition(lefts[i], lefts[i]+200);
}