// Polygon example

// Create a graph for test and do remote config for traversal
// system.graph('poly').create()
// :remote config alias g ls.g

schema.propertyKey('boundary').Polygon().create()
schema.vertexLabel('plot').properties('boundary').create()

// Examples to add vertex either with g.addV or graph.addVertex - same linestring
g.addV(label, 'plot').property('boundary', Geo.polygon(0.0,0.0,0.0,10.0,10.0,10.0,0.0,0.0))
graph.addVertex(label, 'plot', 'boundary', Geo.polygon(0.0,0.0,0.0,10.0,10.0,10.0,0.0,0.0))

// Test it
g.V().hasLabel('plot').valueMap()
