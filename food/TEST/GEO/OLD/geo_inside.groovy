// Test if the point created in point.groovy is found inside a specified polygon:
g.V().has('point',Geo.inside(Geo.polygon(0.0,0.0,0.0,10.0,10.0,10.0,0.0,0.0))).valueMap()

// Result: 
// ==>{gender=[M], name=[Jamie Oliver], point=[POINT (1 2)]}
