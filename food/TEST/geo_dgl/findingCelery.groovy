import com.esri.core.geometry.ogc.OGCGeometry;

system.graph('findingCelery').create()
:remote config alias g findingCelery.g
schema.config().option('graph.allow_scan').set('true')
graph.schema().config().option('graph.traversal_sources.g.restrict_lambda').set(false)

//SCHEMA
// Property Keys
// Check for previous creation of property key with ifNotExists()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('address').Text().ifNotExists().create()
schema.propertyKey('location').Point().withBounds(-100,-100,100,100).ifNotExists().create()
// Vertex Labels
schema.vertexLabel('person').properties('name').ifNotExists().create()
schema.vertexLabel('home').properties('address','location').ifNotExists().create()
schema.vertexLabel('store').properties('name','location').ifNotExists().create()
schema.vertexLabel('ingredient').properties('name').ifNotExists().create()
// Edge Labels
schema.edgeLabel('livesIn').multiple().connection('person','home').ifNotExists().create()
schema.edgeLabel('isStockedWith').multiple().connection('store','ingredient').ifNotExists().create()

// Search indexes
schema.vertexLabel('person').index('search').search().by('name').asString().ifNotExists().add()
schema.vertexLabel('home').index('search').search().by('name').by('location').add();
schema.vertexLabel('store').index('search').search().by('name').by('location').add();
schema.vertexLabel('ingredient').index('search').search().by('name').add();

//populate graph
//vertices
// person vertices
pam = graph.addVertex(label, 'person', 'name','Pam')
les = graph.addVertex(label, 'person', 'name','Les')
paul = graph.addVertex(label, 'person', 'name','Paul')
victoria = graph.addVertex(label, 'person', 'name','Victoria')
terri = graph.addVertex(label, 'person', 'name','Terri')

// home vertices
home1 = graph.addVertex(label, 'home', 'address', '555 4th St',  'location', Geo.point(7,2));
home2 = graph.addVertex(label, 'home', 'address', '1700 Coyote Rd',  'location', Geo.point(-2,1));
home3 = graph.addVertex(label, 'home', 'address', '99 Mountain Pass Hwy',  'location', Geo.point(0,0));

// store vertices
store1 = graph.addVertex(label, 'store', 'name', 'ZippyMart',  'location', Geo.point(1,5));
store2 = graph.addVertex(label, 'store', 'name', 'Quik Station',  'location', Geo.point(7,-1));
store3 = graph.addVertex(label, 'store', 'name', "Mamma's Grocery",  'location', Geo.point(-3,-3));

// ingredient vertices
celery = graph.addVertex(label, 'ingredient','name', 'celery');
milk = graph.addVertex(label, 'ingredient','name', 'milk');
bokChoy = graph.addVertex(label, 'ingredient','name', 'bok choy');
steak = graph.addVertex(label, 'ingredient','name', 'steak');
carrots = graph.addVertex(label, 'ingredient','name', 'carrots');
porkChops = graph.addVertex(label, 'ingredient','name', 'pork chops');

// person - home edges
pam.addEdge('livesIn', home1);
les.addEdge('livesIn', home1);
paul.addEdge('livesIn',home3);
victoria.addEdge('livesIn',home3);
terri.addEdge('livesIn',home2);

// store - ingredient edges
store1.addEdge('isStockedWith', milk);
store1.addEdge('isStockedWith', bokChoy);
store1.addEdge('isStockedWith', steak);
store1.addEdge('isStockedWith', celery);
store2.addEdge('isStockedWith', steak);
store2.addEdge('isStockedWith', carrots);
store2.addEdge('isStockedWith', porkChops);
store2.addEdge('isStockedWith', celery);
store3.addEdge('isStockedWith', milk);
store3.addEdge('isStockedWith', carrots);
store3.addEdge('isStockedWith', celery);
