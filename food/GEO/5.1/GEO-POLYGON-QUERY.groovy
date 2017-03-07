:remote config alias g geodetic51.g
// Finds Dublin, Aachen, and Tokyo in geodeticSearch51
// It doesn't find any cities in geodetic51
g.V().has('location', 'point', Geo.inside(Geo.polygon(-6.26031, 53.349805, 6.083887, 50.775346, 139.691706, 35.689487))).values('name')
// This one find Paris and Aachen for geodetic51 and finds Paris, London, and Aachen in geodeticSearch51
g.V().has('location', 'point', Geo.inside(Geo.polygon(0,0,0,55,25,55,25,0))).values('name')

:remote config alias g geodeticSearch51.g
// Finds Dublin, Aachen, and Tokyo in geodeticSearch51
// It doesn't find any cities in geodetic51 or cartesian51
g.V().has('location', 'point', Geo.inside(Geo.polygon(-6.26031, 53.349805, 6.083887, 50.775346, 139.691706, 35.689487))).values('name')
// This one find Paris and Aachen for geodetic51 and cartesian51 and finds Paris, London, and Aachen in geodeticSearch51
g.V().has('location', 'point', Geo.inside(Geo.polygon(0,0,0,55,25,55,25,0))).values('name')

:remote config alias g cartesian51.g
// Finds Dublin, Aachen, and Tokyo in geodeticSearch51
// It doesn't find any cities in geodetic51 or cartesian51
g.V().has('location', 'point', Geo.inside(Geo.polygon(-6.26031, 53.349805, 6.083887, 50.775346, 139.691706, 35.689487))).values('name')
// This one find Paris and Aachen for geodetic51 and cartesian51 and finds Paris, London, and Aachen in geodeticSearch51
g.V().has('location', 'point', Geo.inside(Geo.polygon(0,0,0,55,25,55,25,0))).values('name')
