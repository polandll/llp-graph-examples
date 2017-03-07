import com.esri.core.geometry.ogc.OGCGeometry;
import com.esri.core.geometry.ogc.OGCLineString;

system.graph('ogc').create()
:remote config alias g ogc.g
//schema.config().option('graph.schema_mode').set('Production')
schema.config().option('graph.allow_scan').set('true')

// Property Keys
// Check for previous creation of property key with ifNotExists()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
schema.propertyKey('location').Point().ifNotExists().create()

// Vertex Labels
schema.vertexLabel('author').properties('name','gender').ifNotExists().create()
schema.vertexLabel('place').properties('name','location').create()

// Edge Labels
schema.edgeLabel('livesIn').connection('author','place').ifNotExists().create()

// Vertex Indexes
// Secondary
schema.vertexLabel('author').index('byName').secondary().by('name').add()
// Materialized
// Search
schema.vertexLabel('author').index('search').search().by('name').asString().ifNotExists().add()
schema.vertexLabel('place').index('search').search().by('location').add();

//populate graph
//vertices

// author vertices
juliaChild = graph.addVertex(label, 'author', 'name','Julia Child', 'gender', 'F')
simoneBeck = graph.addVertex(label, 'author', 'name', 'Simone Beck', 'gender', 'F')
louisetteBertholie = graph.addVertex(label, 'author', 'name', 'Louisette Bertholie', 'gender', 'F')
patriciaSimon = graph.addVertex(label, 'author', 'name', 'Patricia Simon', 'gender', 'F')
aliceWaters = graph.addVertex(label, 'author', 'name', 'Alice Waters', 'gender', 'F')
patriciaCurtan = graph.addVertex(label, 'author', 'name', 'Patricia Curtan', 'gender', 'F')
kelsieKerr = graph.addVertex(label, 'author', 'name', 'Kelsie Kerr', 'gender', 'F')
fritzStreiff = graph.addVertex(label, 'author', 'name', 'Fritz Streiff', 'gender', 'M')
emerilLagasse = graph.addVertex(label, 'author', 'name', 'Emeril Lagasse', 'gender', 'M')
jamesBeard = graph.addVertex(label, 'author', 'name', 'James Beard', 'gender', 'M')

// place vertices
newYork = graph.addVertex(label, 'place', 'name', 'New York', 'location', Geo.point(74.0059,40.7128));
paris = graph.addVertex(label, 'place', 'name', 'Paris', 'location', Geo.point(2.3522, 48.8566));
newOrleans = graph.addVertex(label, 'place', 'name', 'New Orleans', 'location', Geo.point(90.0715, 29.9511));
losAngeles = graph.addVertex(label, 'place', 'name', 'Los Angeles', 'location', Geo.point(118.2437, 34.0522));
london = graph.addVertex(label, 'place', 'name', 'London', 'location', Geo.point(0.1278, 51.5074));
chicago = graph.addVertex(label, 'place', 'name', 'Chicago', 'location', Geo.point(487.6298, 1.8781));
tokyo = graph.addVertex(label, 'place', 'name', 'Tokyo', 'location', Geo.point(139.6917, 35.6895));

//edges
juliaChild.addEdge('livesIn', newYork);
simoneBeck.addEdge('livesIn', paris);
louisetteBertholie.addEdge('livesIn', london);
patriciaSimon.addEdge('livesIn', newYork);
aliceWaters.addEdge('livesIn', losAngeles);
patriciaCurtan.addEdge('livesIn', chicago);
kelsieKerr.addEdge('livesIn', tokyo);
fritzStreiff.addEdge('livesIn', tokyo);
emerilLagasse.addEdge('livesIn', newOrleans);
jamesBeard.addEdge('livesIn', london);

schema.config().option('graph.traversal_sources.g.restrict_lambda').set(false)

// Queries
// Get the locations in sorted by distance order from the given point
g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 1))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
valueMap()
// Get the author names for the locations in the last query
g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 1))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
in().
values('name')
// Get the location and the author names in sort by distance order
g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 10))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')

// IT IS OBVIOUS THAT WHEN USING GETOGCGEOMETRY, THE POINTS ARE SIMPLY X/Y POINTS, AND THAT
// THE GEO.DISTANCE CALCULATION IS NOT USING KILOMETERS, BUT RATHER A SIMPLE DISTANCE
// FORMULA BETWEEN THE POINTS
g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 50))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')
g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 40))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')

g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 30))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')
g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 20))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')

g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 10))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')
g.V().has('place', 'location', Geo.inside(Geo.distance(74, 40, 1))).
order().
by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')
