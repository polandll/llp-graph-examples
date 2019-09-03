:remote config alias g mapTest.g
schema.config().option('graph.allow_scan').set('true')

// List all the vertices
g.V()

// List all the chefs
g.V().valueMap()
