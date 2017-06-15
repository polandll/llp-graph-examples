//:remote config alias g testFilePatJSON.g
:remote config alias g testFilePatMod2.g
schema.config().option('graph.allow_scan').set('true')

g.V().valueMap()
