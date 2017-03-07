// Point example:

// Create a point property
schema.propertyKey('point').Point().create()
schema.propertyKey('name').Text().create()
schema.propertyKey('gender').Text().create()
schema.vertexLabel('author').properties('name','gender','point').create()
graph.addVertex(label,'author','name','Jamie Oliver','gender','M','point',Geo.point(1,2))

// Test it
g.V().hasLabel('author').valueMap()
