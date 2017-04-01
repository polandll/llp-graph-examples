:remote config alias g testfitnessTracker.g
schema.config().option('graph.allow_scan').set('true')

// Get all the graph vertices and edges
g.V()

// What is the food that two people ate that was in common? Here the answer is that Lori and Lisa both had a taco.
g.V().has('user','name','Lori').
    out('ate').in('includes').as('food').
    out('includes').in("ate").
    has('name',neq('Lori')).as('person').
    select('food','person').
        by('name').by('name')

// Copy Lisa's meal on 2017-03-30 (lunch) to my meals. (make an edge from me to that meal)
g.V().has('user','name','Lori').as('a').
out('knows').out('ate').as('b').
addE('ate').from('a').to('b')
