var options = {
    "style": "box",
    "cluster": true,
    "clusterMaxItems": 5,
    "animate": false,
    "axisOnTop": true
};

//
// NOTE: data must be ordered by crescent start
//

//
// data set 1: collisions
//
var data1 = [
    {id: 1, title:"file5", start: new Date(2013, 0, 1, 0, 0, 0, 1).getTime(), group: "file", type: "file"},
    {id: 2, title:"image1", start: new Date(2013, 5, 11, 13, 42, 31,0).getTime(), group: "image", type: "image"},
    {id: 3, title:"image2", start: new Date(2013, 6, 12, 15, 17, 9, 0).getTime(), group: "image", type: "image"},
    {id: 4, title:"file1", start: new Date(2013, 7, 24, 7, 32, 12, 0).getTime(), group: "file", type: "file"},
    {id: 5, title:"image3", start: new Date(2013, 8, 11, 8, 39, 0, 0).getTime(), group: "image", type: "image"},
    {id: 6, title:"file3", start: new Date(2013, 8, 24, 23, 44, 51, 0).getTime(), group: "file", type: "file"},
    {id: 7, title:"file2", start: new Date(2013, 9, 6, 0, 44, 16, 0).getTime(), group: "file", type: "file"},
    {id: 8, title:"file4", start: new Date(2013, 11, 8, 9, 57, 41, 0).getTime(), group: "file", type: "file"},
    {id: 9, title:"file6", start: new Date(2013, 11, 31, 23, 59, 59, 1).getTime(), group: "file", type: "file"}
];

//
// data set 2: no collisions
//
var data2 = [
    {id: 2, title:"image1", start: new Date(2013, 5, 11, 13, 42, 31,0).getTime(), group: "image", type: "image"},
    {id: 4, title:"file1", start: new Date(2013, 7, 24, 7, 32, 12, 0).getTime(), group: "file", type: "file"},
    {id: 5, title:"image3", start: new Date(2013, 8, 11, 8, 39, 0, 0).getTime(), group: "image", type: "image"},
    {id: 6, title:"file3", start: new Date(2013, 8, 24, 23, 44, 51, 0).getTime(), group: "file", type: "file"},
];

var lefts1 = [
    0, 100, 200, 250, 600, 650, 700, 750, 1000
];

var lefts2 = [
    0, 300, 600, 900, 1200
];

window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));
timeline.setOptions(options);

timeline.size.axis = {top: 2000, height: 50};
timeline.size.contentHeight = 1995;
timeline.addItemType('file', test.Item);
timeline.addItemType('image', test.Item);

var items1 = [], items2 = [];

for (i = 0; i<data1.length; ++i) {
    items1.push(timeline.createItem(data1[i]));
    items1[i].createDOM();
    items1[i].setPosition(lefts1[i], lefts1[i]+200);
}

for (i = 0; i<data2.length; ++i) {
    items2.push(timeline.createItem(data2[i]));
    items2[i].createDOM();
    items2[i].setPosition(lefts2[i], lefts2[i]+200);
}
