:remote config alias g newComp513.g
schema.config().option('graph.allow_scan').set('true')

// Check all the vertices
// Should be 99 
g.V().count()

// Check all the edges
// Should be 104 
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
// fridgeSensor
g.V().hasLabel('fridgeSensor').valueMap()
// ingredient
g.V().hasLabel('ingredient').valueMap()

// ate
g.V().hasLabel('person').as('person').out().hasLabel('meal').as('meal').inE('ate').as('mealDate').select('person','meal','mealDate').by('name').by('mealId').by('mealDate')
// contains
g.V().hasLabel('fridgeSensor').as('sensor').out().hasLabel('ingredient').as('ingred').inE('contains').as('expireDate').select('sensor','ingred','expireDate').by('cityId').by('name').by('expireDate')
