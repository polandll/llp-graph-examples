import com.esri.core.geometry.ogc.OGCGeometry;

system.graph('waypoint').create()
:remote config alias g waypoint.g
schema.config().option('graph.allow_scan').set('true')
graph.schema().config().option('graph.traversal_sources.g.restrict_lambda').set(false)
//SCHEMA
// Property Keys
// Check for previous creation of property key with ifNotExists()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
schema.propertyKey('location').Point().withGeoBounds().ifNotExists().create()
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
london = graph.addVertex(label, 'place', 'name', 'London', 'location', Geo.point(-0.1278, 51.5074));
chicago = graph.addVertex(label, 'place', 'name', 'Chicago', 'location', Geo.point(-87.6298, 41.8781136));
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

// Queries

// Find the place names for all cities within the given radius from New York
g.V().has('place', 'location', Geo.inside(Geo.point(74.0,40.5),50,Geo.Unit.DEGREES)).values('name')

// Order by name, not by distance from location point given
g.V().has('place', 'location', Geo.inside(Geo.point(74.0,40.5),50,Geo.Unit.DEGREES)).
order().by('name').
as('Location').
in().as('Author').
select('Location','Author').
by('name').
by('name')

// Order by distance from NYC 
g.V().has('place', 'location', Geo.inside(Geo.point(74.0,40.5),50,Geo.Unit.DEGREES)).
order().by{it.value('location').getOgcGeometry().distance(Geo.point(74.0059,40.7128).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location', 'Author').
by('name').
by('name')
