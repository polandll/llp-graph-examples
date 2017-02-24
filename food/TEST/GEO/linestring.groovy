// Linestring example

// Create a graph for test and do remote config for traversal
// system.graph('ls').create()
// :remote config alias g ls.g

schema.propertyKey('linestring').Linestring().create()
schema.vertexLabel('hwy').properties('linestring').create()

// Examples to add vertex either with g.addV or graph.addVertex - same linestring
g.addV(label, 'hwy').property('linestring', 
  'LINESTRING (2.81153130531311 32.377727031707764, 2.91153130531321 32.47772703170786)')
graph.addVertex(label, 'hwy', 'linestring', 
  'LINESTRING (2.81153130531311 32.377727031707764, 2.91153130531321 32.47772703170786)')

// Test it
g.V().hasLabel('hwy').valueMap()

