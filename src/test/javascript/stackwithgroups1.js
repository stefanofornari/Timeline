options = {
    "style": "dot",
    "cluster": true,
    "animate": false,
    "clusterMaxItems": 5,
    "axisOnTop": true
};

var items = [
    {title:"image1", start:"2013-06-11 13:42:31Z", group: "image"},
    {title:"image2", start:"2013-07-12 15:17:09Z", group: "image"},
    {title:"file1", start:"2013-07-24 07:32:12Z", group: "file"},
    {title:"image3", start:"2013-09-11 08:39:00Z", group: "image"},
    {title:"file2", start:"2013-10-06 00:44:16Z", group: "file"},
    {title:"file3", start:"2013-09-16 23:44:51Z", group: "file"},
    {title:"file4", start:"2013-09-24 09:57:41Z", group: "file" },
    {title:"file5", start:"2013-12-08 11:57:41Z"}
];

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));
timeline.setData(items);
