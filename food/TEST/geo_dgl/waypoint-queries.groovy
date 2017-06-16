import com.esri.core.geometry.ogc.OGCGeometry;

:remote config alias g waypoint.g
//schema.config().option('graph.allow_scan').set('true')
schema.config().option('graph.allow_scan').set('false')
graph.schema().config().option('graph.traversal_sources.g.restrict_lambda').set(false)

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
order().by{it.value('location').getOgcGeometry().distance(Geo.point(74, 40).getOgcGeometry())}.
as('Location').
in().as('Author').
select('Location', 'Author').
by('name').
by('name')
