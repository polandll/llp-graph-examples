:remote config alias g ftWithId.g
schema.config().option('graph.allow_scan').set('true')

// Get all the graph vertices and edges
g.V()
g.E()

// Get values 
g.V().valueMap()
g.E().valueMap()

// What is the food that two people ate that was in common? Here the answer is that Lori and Lisa both had a taco.
g.V().has('person','name','Lori').
    out('ate').in('includes').as('food').
    out('includes').in("ate").
    has('name',neq('Lori')).as('person').
    select('food','person').
        by('name').by('name')

// Copy Lisa's meal on 2017-03-30 (lunch) to my meals. (make an edge from me to that meal)
g.V().has('person','name','Lori').as('a').
out('knows').out('ate').as('b').
addE('ate').from('a').to('b')

// What did lunch include on 2017-03-31?
g.V().has('person','name','Lori').
out('ate').has('mealDate','2017-03-31').
in('includes').values('name')

// How many calories was that lunch?
g.V().has('person','name','Lori').
out('ate').has('mealDate','2017-03-31').
in('includes').values('calories').
sum()
