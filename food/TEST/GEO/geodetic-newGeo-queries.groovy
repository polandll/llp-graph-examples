// Geodetic example
// NO SEARCH INDEX

:remote config alias g geodeticnewGeo.g
schema.config().option('graph.allow_scan').set('true')

// Test point
g.V().hasLabel('location').valueMap()
// Test that no points are inside distance from (0,0) to 1 degree of radius
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 1, Geo.Unit.DEGREES)).values('name')
// Test that Paris and London are inside distance from Paris to London
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 3.7, Geo.Unit.DEGREES)).values('name')
// Test that Paris, London, and Aachen are inside distance from Paris to Aachen
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 4.2, Geo.Unit.DEGREES)).values('name')
// Test that Paris, London, Aachen, and Dublin are inside distance from Paris to Dublin
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 9.8, Geo.Unit.DEGREES)).values('name')
// Test that all location are inside distance from Paris to Tokyo
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 138, Geo.Unit.DEGREES)).values('name')
// Test that Paris is inside distance of 300 km centered on Paris
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 300, Geo.Unit.KILOMETERS)).values('name')
// Test that Paris and Aachen are inside distance of 341 km centered on Paris
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 341, Geo.Unit.KILOMETERS)).values('name')

// Test linestring
g.V().hasLabel('lineLocation').valueMap()
// Test that no linestrings are inside distance from Paris to 1 degree of radius
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(2.352222, 48.856614), 1, Geo.Unit.DEGREES)).values('name')
// Test that line between Paris and London are inside distance from Paris to London
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(2.352222, 48.856614), 3.7, Geo.Unit.DEGREES)).values('name')
// Test that lines between Paris and London and Paris and Aachen are inside distance from Paris to Aachen
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(2.352222, 48.856614), 4.2, Geo.Unit.DEGREES)).values('name')
// Test that all lines are inside distance from Paris to Dublin
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(2.352222, 48.856614), 9.8, Geo.Unit.DEGREES)).values('name')

// Test polygon
g.V().hasLabel('polyLocation').valueMap()
// Test that no polygons are inside distance from Paris to 1 degree of radius
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(2.352222, 48.856614), 1, Geo.Unit.DEGREES)).values('name')
// Test that ParisLondonDublin and LondonDublinAachen polygons are inside distance from Paris to Dublin
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(2.352222, 48.856614), 9.8, Geo.Unit.DEGREES)).values('name')
// Test that all polygons are inside distance from Paris to Toyko
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(2.352222, 48.856614), 138, Geo.Unit.DEGREES)).values('name')
