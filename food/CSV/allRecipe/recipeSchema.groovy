// RECIPE SCHEMA
system.graph('recipeTest').ifNotExists().create()
:remote config alias g recipeTest.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// Property Keys
// Check for previous creation of property key with ifNotExists()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().create()
schema.propertyKey('instructions').Text().create()
// schema.propertyKey('category').Text().create()
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
schema.propertyKey('nickname').Text().multiple().create()
//schema.propertyKey('country').Text().create()
schema.propertyKey('livedIn').Text().create()
schema.propertyKey('country').Text().multiple().properties('livedIn').create()

// Vertex Labels
schema.vertexLabel('author').properties('name','gender','nickname').ifNotExists().create()
// Example of creating vertex label with properties
schema.vertexLabel('recipe').properties('name','instructions').create()
schema.vertexLabel('ingredient').create()
schema.vertexLabel('book').properties('name','year','ISBN').create()
schema.vertexLabel('meal').properties('timestamp', 'calories').create()
schema.vertexLabel('reviewer').create()
// Example of custom vertex id:
schema.propertyKey('fridgeItem').Text().create()
schema.propertyKey('city_id').Text().create()
schema.propertyKey('sensor_id').Uuid().create()
schema.propertyKey('location').Point().withGeoBounds().create()
schema.vertexLabel('FridgeSensor').partitionKey('city_id').clusteringKey('sensor_id').create()
schema.vertexLabel('FridgeSensor').properties('fridgeItem', 'location').add()

// Edge Labels
schema.edgeLabel('authored').connection('author','book').ifNotExists().create()
schema.edgeLabel('created').properties('year').connection('author','recipe').create()
schema.edgeLabel('includes').properties('amount').connection('recipe','ingredient').create()
schema.edgeLabel('includedIn').connection('recipe','book').connection('recipe','meal').connection('meal', 'book').create()
schema.edgeLabel('rated').properties('stars').connection('reviewer','recipe').create()

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
schema.vertexLabel('author').index('search').search().by('name').asString().by('nickname').ifNotExists().add()
schema.vertexLabel('recipe').index('search').search().by('instructions').asText().by('name').asString().add()
schema.vertexLabel('book').index('search').search().by('name').asString().by('year').add()
schema.vertexLabel('FridgeSensor').index('search').search().by('location').ifNotExists().add()

// Edge Index
schema.vertexLabel('reviewer').index('ratedByStars').outE('rated').by('stars').add()
schema.vertexLabel('reviewer').index('ratedByTimestamp').outE('rated').by('timestamp').add()
schema.vertexLabel('reviewer').index('ratedByComments').outE('rated').by('comment').add()

// Property index using meta-property 'livedIn':
schema.vertexLabel('author').index('byLocation').property('country').by('livedIn').add()

// commit the transaction
//graph.tx().commit()

// Schema description
// Use to check that the schema is built as desired
schema.describe()
