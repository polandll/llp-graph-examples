:remote config alias g newComp.g
schema.config().option('graph.allow_scan').set('false')

// Find all review comments by John Doe
g.V().has('person', 'name','John Doe').outE('reviewed').values('comment')
// Find all the recipes that John Doe reviewed
g.V().has('person', 'name','John Doe').outE('reviewed').inV().values('name')
//
g.V().has('person', 'name','John Doe').outE('reviewed').has('stars', gt(3)).valueMap()
//
g.V().has('person', 'name','John Doe').outE('reviewed').has('stars', gt(3)).inV().valueMap()

g.V().hasLabel('person').as('person','starCount').
  select('person','starCount').
    by('name').
    by(outE('reviewed').values('stars').mean())
