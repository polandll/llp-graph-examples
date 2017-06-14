//:remote config alias g testFilePatJSON.g
:remote config alias g testFilePat.g
schema.config().option('graph.allow_scan').set('true')

g.V().valueMap()
