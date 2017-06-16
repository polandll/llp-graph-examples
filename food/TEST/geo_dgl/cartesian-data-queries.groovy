// Cartesian example - NO SEARCH INDEX

:remote config alias g cartesianData.g
//schema.config().option('graph.allow_scan').set('true')
schema.config().option('graph.allow_scan').set('false')

// Find a single point
g.V().has('location','point', Geo.inside(Geo.point(0, 0), 0, Geo.Unit.METERS))

// Test point with circle (centerpoint and radius)
g.V().hasLabel('location').valueMap()
// Test that no points are inside distance from (0,0) to slightly less than (1,1)
g.V().has('location', 'point', Geo.inside(Geo.point(0.0, 0.0), 1, DEGREES)).values()
// Test that two closer points are inside distance from (0,0) to slightly more than (1,1) - squareroot of 2
g.V().has('location', 'point', Geo.inside(Geo.point(0.0, 0.0), 1.415, DEGREES)).values()
// Test that all four points are inside distance from (0,0) to slightly more than (-2,-2) - squareroot of 8
g.V().has('location', 'point', Geo.inside(Geo.point(0.0, 0.0), 2.829, DEGREES)).values()

// Test linestring with circle (centerpoint and radius)
g.V().hasLabel('lineLocation').valueMap()
// Test that no linestrings are inside distance from (0,0) to slightly less than (1,1)
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(0.0, 0.0), 1, DEGREES)).values()
// Test that two closer lines are inside distance from (0,0) to slightly more than (1,1) - squareroot of 2
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(0.0, 0.0), 1.415, DEGREES)).values()
// Test that all four lines are inside distance from (0,0) to slightly more than (-2,-2) - squareroot of 8
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(0.0, 0.0), 2.829, DEGREES)).values()

// Test polygon with circle (centerpoint and radius)
g.V().hasLabel('polyLocation').valueMap()
// Test that no polygons are inside distance from (0,0) to slightly less than (1,1)
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(0.0, 0.0), 1, DEGREES)).values()
// Test that two closer polygons are inside distance from (0,0) to slightly more than (1,1) - squareroot of 2
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(0.0, 0.0), 1.415, DEGREES)).values()
// Test that all four polygons are inside distance from (0,0) to slightly more than (-2,-2) - squareroot of 8
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(0.0, 0.0), 2.829, DEGREES)).values()

// Test point within polygon
 g.V().has('location', 'point', Geo.inside(Geo.polygon(0.0,0.0,2.0,0.0,2.0,2.0,0.0,0.0))).values()
