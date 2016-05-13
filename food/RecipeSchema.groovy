// RECIPE SCHEMA

// To run in Studio, copy and paste all lines to a cell and run.

// To run in Gremlin console, use the load command
// :load /Users/lorinapoland/CLONES/graph-examples/food/NEWRecipeSchema.groovy

    	
// Property Keys 
// Check for previous creation of property key with ifNotExists() 
schema.propertyKey('name').Text().ifNotExists().create()        
schema.propertyKey('gender').Text().create()
schema.propertyKey('instructions').Text().create()
schema.propertyKey('category').Text().create()
schema.propertyKey('year').Int().create()
schema.propertyKey('timestamp').Timestamp().create()
schema.propertyKey('ISBN').Text().create()
schema.propertyKey('calories').Int().create()
schema.propertyKey('amount').Text().create()
schema.propertyKey('stars').Int().create()
// single() is optional, as it is the default
schema.propertyKey('comment').Text().single().create()
// Example of a multiple property that can have several values
// Next 4 lines define two properties, then create a meta-property 'livedIn' on 'country'  
// A meta-property is a property of a property
// EX: 'livedIn': '1999-2005' 'country': 'Belgium'     
// schema.propertyKey('nickname').Text().multiple().create()    
// schema.propertyKey('country').Text().create()                        
// schema.propertyKey('livedIn').Text().create()                        
// schema.propertyKey('country').Text().properties('livedIn').create()  
    		
// Vertex Labels
schema.vertexLabel('author').ifNotExists().create()
schema.vertexLabel('recipe').create()
// Example of creating vertex label with properties
// schema.vertexLabel('recipe').properties('name','instructions').create()
schema.vertexLabel('ingredient').create()
schema.vertexLabel('book').create()
schema.vertexLabel('meal').create()
schema.vertexLabel('reviewer').create()
// Example of custom vertex id:
// schema.propertyKey('city_id').Int().create()
// schema.propertyKey('sensor_id').Uuid().create()
// schema().vertexLabel('FridgeSensor').partitionKey('city_id').clusteringKey('sensor_id').create()
                
// Edge Labels
schema.edgeLabel('authored').ifNotExists().create()
schema.edgeLabel('created').create()
schema.edgeLabel('includes').create()
schema.edgeLabel('includedIn').create()
schema.edgeLabel('rated').connection('reviewer','recipe').create()
                
// Vertex Indexes
// Secondary
schema.vertexLabel('author').index('byName').secondary().by('name').add()
// Materialized	  		
schema.vertexLabel('recipe').index('byRecipe').materialized().by('name').add()
schema.vertexLabel('meal').index('byMeal').materialized().by('name').add()
schema.vertexLabel('ingredient').index('byIngredient').materialized().by('name').add()
schema.vertexLabel('reviewer').index('byReviewer').materialized().by('name').add()
// Search
// schema.vertexLabel('recipe').index('search').search().by('instructions').asText().add()
// schema.vertexLabel('recipe').index('search').search().by('instructions').asString().add()
// If more than one property key is search indexed
// schema.vertexLabel('recipe').index('search').search().by('instructions').asText().by('category').asString().add()

// Edge Index
schema.vertexLabel('reviewer').index('ratedByStars').outE('rated').by('stars').add()

// Property index using meta-property 'livedIn': 
schema.vertexLabel('author').index('byLocation').property('country').by('livedIn').add()

// Schema description
// Use to check that the schema is built as desired
schema.describe()

