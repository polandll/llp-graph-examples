import com.esri.core.geometry.ogc.OGCGeometry;
// SCHEMA
system.graph('proteinCarbData').create()
:remote config alias g proteinCarbData.g
schema.config().option('graph.allow_scan').set('true')
graph.schema().config().option('graph.traversal_sources.g.restrict_lambda').set(false)

//SCHEMA
schema.propertyKey('name').Text().create()
schema.propertyKey('point').Point().withBounds(-1,-1,100,100).create()
schema.vertexLabel('data').properties('name','point').create()
schema.vertexLabel('data').index('search').search().by('point').add()


// CREATE POINTS
graph.addVertex(label,'data','name','p0','point',Geo.point(2.90,0.8))
graph.addVertex(label,'data','name','p1','point',Geo.point(1.30,0))
graph.addVertex(label,'data','name','p2','point',Geo.point(0.20,0))
graph.addVertex(label,'data','name','p3','point',Geo.point(4.00,0.4))
graph.addVertex(label,'data','name','p4','point',Geo.point(6.10,0))
graph.addVertex(label,'data','name','p5','point',Geo.point(21.10,6.9))
graph.addVertex(label,'data','name','p6','point',Geo.point(21.20,7.0))
graph.addVertex(label,'data','name','p7','point',Geo.point(7.80,2.5))
graph.addVertex(label,'data','name','p8','point',Geo.point(3.00,0.3))
graph.addVertex(label,'data','name','p9','point',Geo.point(3.50,0.3))
graph.addVertex(label,'data','name','p10','point',Geo.point(0.50,13.7))
graph.addVertex(label,'data','name','p11','point',Geo.point(25.20,0.0))
graph.addVertex(label,'data','name','p12','point',Geo.point(17.60,0))
graph.addVertex(label,'data','name','p13','point',Geo.point(0.50,57.6))
graph.addVertex(label,'data','name','p14','point',Geo.point(0.10,9.7))
graph.addVertex(label,'data','name','p15','point',Geo.point(0.30,20.2))
graph.addVertex(label,'data','name','p16','point',Geo.point(0.20,17.1))
graph.addVertex(label,'data','name','p17','point',Geo.point(0.20,14.3))
graph.addVertex(label,'data','name','p18','point',Geo.point(0.20,11.4))
graph.addVertex(label,'data','name','p19','point',Geo.point(0.20,9.6))

g.V().valueMap()

// Test for certain conditions
g.V().has('data','point',Geo.inside(Geo.point(17,0), 1,DEGREES)).valueMap()
g.V().has('data','point',Geo.inside(Geo.point(3,0), 1, DEGREES)).valueMap()
// Find  points in an approximate ratio of carb:protein 100:1
g.V().has('data','point',Geo.inside(Geo.point(0.1,10),1, DEGREES)).valueMap()
g.V().has('data','point',Geo.inside(Geo.point(0.2,20),1, DEGREES)).valueMap()

// Complex
// Order by distance from given point
g.V().has('data', 'point', Geo.inside(Geo.point(3,0),1,Geo.Unit.DEGREES)).
order().by{it.value('point').getOgcGeometry().distance(Geo.point(3,0).getOgcGeometry())}.
valueMap()
