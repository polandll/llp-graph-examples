:remote config alias g ftWithId.g
schema.config().option('graph.allow_scan').set('false')

// Get all the graph vertices and edges
//g.V()
//g.E()

// Get values 
//g.V().valueMap()
//g.E().valueMap()

// What is the food that two people ate that was in common? Here the answer is that Lori and Lisa both had a taco.
g.V().has('person','name','Lori').
    out('ate').out('includes').as('food').
    in('includes').in("ate").
    has('name',neq('Lori')).as('person').
    select('food','person').
        by('name').by('name')

// another (better?) algorithm
g.V().match(
        __.as('a').has('person','name','Lori').as('first_person'),
        __.as('first_person').out('ate').out('includes').as('food_item'),
        __.as('food_item').in('includes').in('ate').has('person','name','Lisa').as('second_person')).
  select('first_person','second_person','food_item').by('name').dedup()

// NEED TO CONSIDER HOW TO ONLY CREATE ONE ate EDGE FOR EACH PERSON FOR EACH MEAL
// THIS QUERY WILL CREATE MULTIPLE EDGES WITH THE SAME INFO
// Copy Lisa's meal on 2017-03-30 (lunch) to my meals. (make an edge from me to that meal)
g.V().has('person','name','Lori').as('copier').
  out('knows').has('name','Lisa').out('ate').
  has('mealDate','2017-03-30').
  has('type', 'lunch').as('copiee').
addE('ate').from('copier').to('copiee')

// What did Lori's lunch include on 2017-03-31?
g.V().has('person','name','Lori').
out('ate').has('mealDate','2017-03-31').
out('includes').valueMap('servingAmt', 'name')

// How many calories was that lunch?
g.V().has('person','name','Lori').
out('ate').has('mealDate','2017-03-31').
out('includes').values('calories').
sum()
