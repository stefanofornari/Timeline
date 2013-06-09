options = {
    "style": "dot",
    "cluster": true
};

// Instantiate our timeline object.
window.location='src/test/html/timeline1.html';
var timeline = new links.Timeline(document.getElementById('diskone'));

print("Rows: " + data.length);
data.forEach(function(row) {
   print(row.id + " - " + row.title);
});

// Draw our timeline with the created data and options
timeline.setOptions(options);
timeline.setData(data);

/**
var clusters = timeline.clusterGenerator.getClusters(0.02);

print("Clusters: " + clusters.length);
var i = 0;
clusters.forEach(function (cluster) {
    print(++i + ") " + cluster.items.length);
    cluster.items.forEach(function(item) {
        print("  " + item.id + " - " + item.title);
    });
});
*/