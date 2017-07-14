:remote config alias g newComp.g
schema.config().option('graph.allow_scan').set('true')

// Check all the vertices
// Should be 96 
g.V().count()

// Check all the edges
// Should be 57 
g.E().count()

// person
g.V().hasLabel('person').valueMap()
// recipe
g.V().hasLabel('recipe').valueMap()
// book
g.V().hasLabel('book').valueMap()
// meal
g.V().hasLabel('meal').valueMap()
// meal_item
g.V().hasLabel('meal_item').valueMap()
// home
g.V().hasLabel('home').valueMap()
// store
g.V().hasLabel('store').valueMap()
// location
g.V().hasLabel('location').valueMap()
// fridge_sensor
g.V().hasLabel('fridge_sensor').valueMap()
// ingredient
g.V().hasLabel('ingredient').valueMap()

// ate
g.V().hasLabel('person').as('person').out().hasLabel('meal').as('meal').inE('ate').as('mealDate').select('person','meal','mealDate').by('name').by('mealId').by('mealDate')