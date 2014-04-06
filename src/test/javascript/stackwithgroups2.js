var options = {
    "style": "box",
    "cluster": true,
    "animate": false,
    "maxClusterItems": 5,
    "axisOnTop": true
};

var data = [
    {id: 1, title:"image1", start: new Date(2013, 5, 11, 13, 42, 31,0).getTime(), group: "image", type: "image"},
    {id: 2, title:"image2", start: new Date(2013, 6, 12, 15, 17, 9, 0).getTime(), group: "image", type: "image"},
    {id: 3, title:"file1", start: new Date(2013, 7, 24, 7, 32, 12, 0).getTime(), group: "file", type: "file"},
    {id: 4, title:"image3", start: new Date(2013, 8, 11, 8, 39, 0, 0).getTime(), group: "image", type: "image"},
    {id: 5, title:"file2", start: new Date(2013, 9, 6, 0, 44, 16, 0).getTime(), group: "file", type: "file"},
    {id: 6, title:"file3", start: new Date(2013, 8, 24, 23, 44, 51, 0).getTime(), group: "file", type: "file"},
    {id: 7, title:"file4", start: new Date(2013, 11, 8, 9, 57, 41, 0).getTime(), group: "file", type: "file"},
    {id: 8, title:"file5", start: new Date(2013, 0, 1, 0, 0, 0, 1).getTime(), type: "box"},
    {id: 9, title:"file6", start: new Date(2013, 11, 31, 23, 59, 59, 1).getTime(), type: "box"}
];

window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));
