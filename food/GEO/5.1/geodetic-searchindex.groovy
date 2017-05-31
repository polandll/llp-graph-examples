// Geodetic example - SEARCH INDEX

system.graph('geodeticSearch51').ifNotExists().create()
:remote config alias g geodeticSearch51.g
schema.config().option('graph.allow_scan').set('true')

// Schema
schema.propertyKey('name').Text().create()
schema.propertyKey('point').Point().withGeoBounds().create()
schema.vertexLabel('location').properties('name','point').create()
schema.propertyKey('line').Linestring().withGeoBounds().create()
schema.vertexLabel('lineLocation').properties('name','line').create()
schema.propertyKey('polygon').Polygon().withGeoBounds().create()
schema.vertexLabel('polyLocation').properties('name','polygon').create()
//SEARCH INDEX ONLY WORKS FOR POINT AND LINESTRING
schema.vertexLabel('location').index('search').search().by('point').add()
schema.vertexLabel('lineLocation').index('search').search().by('line').add()

// Create a point
graph.addVertex(label,'location','name','Paris','point',Geo.point(2.352222, 48.856614))
graph.addVertex(label,'location','name','London','point',Geo.point(-0.127758,51.507351))
graph.addVertex(label,'location','name','Dublin','point',Geo.point(-6.26031, 53.349805))
graph.addVertex(label,'location','name','Aachen','point',Geo.point(6.083887, 50.775346))
graph.addVertex(label,'location','name','Tokyo','point',Geo.point(139.691706, 35.689487))

// Create a linestring
graph.addVertex(label, 'lineLocation', 'name', 'ParisLondon', 'line', "LINESTRING(2.352222 48.856614, -0.127758 51.507351)")
graph.addVertex(label, 'lineLocation', 'name', 'LondonDublin', 'line', "LINESTRING(-0.127758 51.507351, -6.26031 53.349805)")
graph.addVertex(label, 'lineLocation', 'name', 'ParisAachen', 'line', "LINESTRING(2.352222 48.856614, 6.083887 50.775346)")
graph.addVertex(label, 'lineLocation', 'name', 'AachenTokyo', 'line', "LINESTRING(6.083887 50.775346, 139.691706 35.689487)")

// Create a polygon
graph.addVertex(label, 'polyLocation','name', 'ParisLondonDublin', 'polygon',Geo.polygon(2.352222, 48.856614, -0.127758, 51.507351, -6.26031, 53.349805))
graph.addVertex(label, 'polyLocation','name', 'LondonDublinAachen', 'polygon',Geo.polygon(-0.127758, 51.507351, -6.26031, 53.349805, 6.083887, 50.775346))
graph.addVertex(label, 'polyLocation','name', 'DublinAachenTokyo', 'polygon',Geo.polygon(-6.26031, 53.349805, 6.083887, 50.775346, 139.691706, 35.689487))

// DISTANCES
// PARIS TO LONDON: 3.08 DEGREES; 344 KM; 214 MI; 344,000 M
// PARIS TO AACHEN: 3.07 DEGREES; 343 KM; 213 MI; 343,000 M
// PARIS TO DUBLIN: 7.02 DEGREES; 781 KM; 485 MI; 781,000 M
// PARIS TO TOYKO: 86.3 DEGREES; 9713 KM; 6035 MI; 9,713,000 M

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
// Test that Paris is inside distance of 1 mile centered on Paris
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 1, Geo.Unit.MILES)).values('name')
// Test that Paris and Aachen are inside distance of 212 mile centered on Paris
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 212, Geo.Unit.MILES)).values('name')
// Test that Paris is inside distance of 10 meters centered on Paris
g.V().has('location', 'point', Geo.inside(Geo.point(2.352222, 48.856614), 10, Geo.Unit.METERS)).values('name')

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
