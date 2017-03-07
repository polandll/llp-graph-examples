import com.esri.core.geometry.ogc.OGCGeometry;

:remote config alias g findingCelery.g
schema.config().option('graph.allow_scan').set('true')
graph.schema().config().option('graph.traversal_sources.g.restrict_lambda').set(false)

// DISTANCE FROM PAUL'S HOME TO EACH STORE
// Paul - Mamma's Grocery : 4.24
// Paul - Quik Mart: 7.07
// Paul - Zippy Mart: 5.099

// queries
// Find all stores within 10 units of distance from Paul's home
g.V().has('store', 'location', Geo.inside(Geo.point(0,0),10, Geo.Unit.DEGREES)).values('name')

// Find all stores within 10 units of distance from Paul's home that have celery
g.V().has('store', 'location', Geo.inside(Geo.point(0,0),10, Geo.Unit.DEGREES)).as('Store').
out().has('name','celery').as('Ingred').
select('Store', 'Ingred').
by('name').
by('name')

// Find all stores within 10 units of distance from Paul's home that have celery
// List store name, location, and ingredient in sorted order from Paul's home
g.V().has('store', 'location', Geo.inside(Geo.point(0,0),10, Geo.Unit.DEGREES)).as('Store').
order().by{it.value('location').getOgcGeometry().distance(Geo.point(0,0).getOgcGeometry())}.
as('Location').
out().has('name','celery').as('Ingred').
select('Store', 'Location','Ingred').
by('name').
by('location').
by('name')
