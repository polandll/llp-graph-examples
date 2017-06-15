// CSV
:remote config alias g testFilePatCSV.g
schema.config().option('graph.allow_scan').set('true')
g.V().valueMap()

// JSON
:remote config alias g testFilePatJSON.g
schema.config().option('graph.allow_scan').set('true')
g.V().valueMap()

// MULT
:remote config alias g testFilePatMULT.g
schema.config().option('graph.allow_scan').set('true')
g.V().valueMap()

// RANGE
:remote config alias g testFilePatRANGE.g
schema.config().option('graph.allow_scan').set('true')
g.V().valueMap()

