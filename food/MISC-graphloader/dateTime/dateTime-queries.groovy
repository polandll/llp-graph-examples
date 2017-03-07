:remote config alias g testdateTime.g
schema.config().option('graph.allow_scan').set('true')

// Find all edges that have a birthdate less than 1940-01-01
g.V().hasLabel('person').outE().has('born','year',lt('1940-01-01')).valueMap()

// Find the name of each child and their parent based on having a birthday less than 1940-01-01
g.V().hasLabel('person').as('child').
outE().has('born','year',lt('1940-01-01')).inV().as('parent').
select('child','parent').
by('name').
by('name')
