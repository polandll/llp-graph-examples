// Recipe Schema for Studio

// Property Keys
name = graph.graph.schema()().buildPropertyKey('name', String).add()
gender = graph.schema().buildPropertyKey('gender', String).add()
instructions = graph.schema().buildPropertyKey('instructions', String).add()
year = graph.schema().buildPropertyKey('year', Integer).add()
timestamp = graph.schema().buildPropertyKey('timestamp', Timestamp).add()
ISBN = graph.schema().buildPropertyKey('ISBN', String).add()
calories = graph.schema().buildPropertyKey('calories', Integer).add()
amount = graph.schema().buildPropertyKey('amount', String).add()
stars = graph.schema().buildPropertyKey('stars', Integer).add()
comment = graph.schema().buildPropertyKey('comment', String).add()
    		
// Vertex Labels
author = graph.schema().buildVertexLabel('author').add()
recipe = graph.schema().buildVertexLabel('recipe').add()
ingredient = graph.schema().buildVertexLabel('ingredient').add()
book = graph.schema().buildVertexLabel('book').add()
meal = graph.schema().buildVertexLabel('meal').add()
reviewer = graph.schema().buildVertexLabel('reviewer').add()
                
// Edge Labels
authored = graph.schema().buildEdgeLabel('authored').add()
created = graph.schema().buildEdgeLabel('created').add()
includes = graph.schema().buildEdgeLabel('includes').add()
includedIn = graph.schema().buildEdgeLabel('includedIn').add()
rated = graph.schema().buildEdgeLabel('rated').add()
                
// Indexes	
ratedByStars = reviewer.buildEdgeIndex('ratedByStars', rated).direction(OUT).byPropertyKey('stars').add()
byRecipe = recipe.buildVertexIndex('byRecipe').materialized().byPropertyKey('name').add()
byMeal = meal.buildVertexIndex('byMeal').materialized().byPropertyKey('name').add()
byIngredient = ingredient.buildVertexIndex('byIngredient').materialized().byPropertyKey('name').add()
byReviewer = reviewer.buildVertexIndex('byReviewer').materialized().byPropertyKey('name').add()
graph.schema().print()