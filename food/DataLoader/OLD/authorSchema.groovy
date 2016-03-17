Schema schema = graph.schema();
    	
// Property Keys
schema.buildPropertyKey('myId', Integer.class).add()
schema.buildPropertyKey('aname', String.class).add()
schema.buildPropertyKey('gender', String.class).add()
schema.buildPropertyKey('revname', String.class).add()
schema.buildPropertyKey('recipeTitle', String.class).add()
schema.buildPropertyKey('instructions', String.class).add()
schema.buildPropertyKey('iName', String.class).add()
schema.buildPropertyKey('bookTitle', String.class).add()
schema.buildPropertyKey('publishDate', Integer.class).add()
schema.buildPropertyKey('ISBN', String.class).add()
schema.buildPropertyKey('mealTitle', String.class).add()
schema.buildPropertyKey('mCreateDate', Instant.class).add()
schema.buildPropertyKey('calories', Integer.class).add()
                
schema.buildPropertyKey('rCreateDate', Integer.class).add()
schema.buildPropertyKey('amount', String.class).add()
schema.buildPropertyKey('stars', Integer.class).add()
schema.buildPropertyKey('ratedDate', Instant.class).add()
schema.buildPropertyKey('comment', String.class).add()
    		
// Vertex Labels
schema.buildVertexLabel('author').add()
schema.buildVertexLabel('recipe').add()
schema.buildVertexLabel('ingredient').add()
schema.buildVertexLabel('book').add()
schema.buildVertexLabel('meal').add()
schema.buildVertexLabel('reviewer').add()
                
// Edge Labels
schema.buildEdgeLabel('authored').add()
schema.buildEdgeLabel('created').add()
schema.buildEdgeLabel('includes').add()
schema.buildEdgeLabel('includedIn').add()
schema.buildEdgeLabel('rated').add()
                
// Indexes	
//reviewer.buildEdgeIndex('ratedByStars', rated).direction(OUT).byPropertyKey('stars').add()
//recipe.buildVertexIndex('byRecipe', MATERIALIZED).byPropertyKey('recipeTitle').add()
//meal.buildVertexIndex('byMeal', MATERIALIZED).byPropertyKey('mealTitle').add()
//ingredient.buildVertexIndex('byIngredient', MATERIALIZED).byPropertyKey('iName').add()
//reviewer.buildVertexIndex('byReviewer', MATERIALIZED).byPropertyKey('revname').add()

graph.tx().commit()