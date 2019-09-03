:remote config alias g filterTest.g
schema.config().option('graph.allow_scan').set('true')

// List all the vertices
g.V()

// List all the living chefs
g.V().hasLabel('chefAlive').valueMap()

// List all the deceased chefs
g.V().hasLabel('chefDeceased').valueMap()

// List all the chefs 41 years old and younger
g.V().hasLabel('chefYoung').valueMap()
