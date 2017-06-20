// Cartesian example - SEARCH INDEX

:remote config alias g cartSIData.g
schema.config().option('graph.allow_scan').set('true')

// Print all points first
g.V().hasLabel('location').valueMap()

// Test point with circle (centerpoint and radius) for a variety of centerpoint/radius settings:

// Find that no points are inside a zero radius from centerpoint (0, 0)
g.V().has('location','point', Geo.inside(Geo.point(0, 0), 0))
// Test that only p0 is inside distance from (0,0) to slightly less than (1,1)
g.V().has('location', 'point', Geo.inside(Geo.point(0, 0), 1)).valueMap()
// Test that three closer points (p0, p1, p2) are inside distance from (0,0) to slightly more than (1,1) - squareroot of 2
g.V().has('location', 'point', Geo.inside(Geo.point(0, 0), 1.415)).valueMap()
// Test that all five points are inside distance from (0,0) to slightly more than (-2,-2) - squareroot of 8
g.V().has('location', 'point', Geo.inside(Geo.point(0, 0), 2.829)).valueMap()

// Print all linestrings first
g.V().hasLabel('lineLocation').valueMap()

// Test linestring with circle (centerpoint and radius) for a variety of centerpoint/radius settings:

// Test that no linestrings are inside distance from (0,0) to slightly less than (1,1)
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(0, 0), 1)).valueMap()
// Test that two closer lines are inside distance from (0,0) to slightly more than (1,1) - squareroot of 2
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(0, 0), 1.415)).valueMap()
// Test that all four lines are inside distance from (0,0) to slightly more than (-2,-2) - squareroot of 8
g.V().has('lineLocation', 'line', Geo.inside(Geo.point(0, 0), 2.829)).valueMap()

// Print all polygons first
g.V().hasLabel('polyLocation').valueMap()

// Test polygon with circle (centerpoint and radius) for a variety of centerpoint/radius settings:

// Test that no polygons are inside distance from (0,0) to slightly less than (1,1)
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(0, 0), 1)).valueMap()
// Test that two closer polygons are inside distance from (0,0) to slightly more than (1,1) - squareroot of 2
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(0, 0), 1.415)).valueMap()
// Test that all four polygons are inside distance from (0,0) to slightly more than (-2,-2) - squareroot of 8
g.V().has('polyLocation', 'polygon', Geo.inside(Geo.point(0, 0), 2.829)).valueMap()

// Test point within polygon
 g.V().has('location', 'point', Geo.inside(Geo.polygon(0.0,0.0,2.0,0.0,2.0,2.0,0.0,0.0))).valueMap()
