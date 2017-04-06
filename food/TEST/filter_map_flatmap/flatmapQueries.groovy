:remote config alias g flatmapTest.g
schema.config().option('graph.allow_scan').set('true')

schema.describe()
g.V().count()
g.V().valueMap()
g.V()
