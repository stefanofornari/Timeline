options = {
    "style": "box"
};

data1 = {"title":"this will be a box","start":"2012-03-04T18:27:24Z"};
data2 =
    {"id":1,"icon":"..\/opt\/serverone\/diskone\/pictures\/9258-1-100x100.png","title":"9258-1.jpg","start":"2012-03-04T18:27:24Z","description":"\/opt\/serverone\/diskone\/pictures","link":"\/opt\/serverone\/diskone\/pictures\/9258-1.jpg","image":"\/opt\/serverone\/diskone\/pictures\/9258-1-100x100.png","type":"image","date_display":"hour"};
data3 = {"title":"this will be a dot","start":"2012-03-04T18:27:24Z","type":"dot"};
data4 = {"title":"this will be a range", "start":"2012-03-04T18:27:24Z", "end":"2012-03-04T18:30:00Z"};

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));

timeline.addItemType('image', test.ImageItem);

timeline.setOptions(options);

item1 = timeline.createItem(data1);
item2 = timeline.createItem(data2);
item3 = timeline.createItem(data3);
item4 = timeline.createItem(data4);
