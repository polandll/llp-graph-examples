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
schema.vertexLabel('author').properties('name').ifNotExists().create()
schema.vertexLabel('author').properties('gender').add()
schema.vertexLabel('author').properties('nickname').add()
// Example of creating vertex label with properties
schema.vertexLabel('recipe').properties('name').create()
schema.vertexLabel('recipe').properties('instructions').add()
schema.vertexLabel('ingredient').properties('name').create()
schema.vertexLabel('book').properties('name').create()
schema.vertexLabel('book').properties('year').add()
schema.vertexLabel('book').properties('ISBN').add()
schema.vertexLabel('meal').properties('timestamp').create()
schema.vertexLabel('meal').properties('calories').add()
schema.vertexLabel('reviewer').properties('name').create()
// Example of custom vertex id:
schema.propertyKey('fridgeItem').Text().create()
schema.propertyKey('city_id').Text().create()
schema.propertyKey('sensor_id').Uuid().create()
schema.propertyKey('location').Point().withGeoBounds().create()
schema.vertexLabel('FridgeSensor').partitionKey('city_id').clusteringKey('sensor_id').create()
schema.vertexLabel('FridgeSensor').properties('fridgeItem', 'location').add()

// Edge Labels
schema.edgeLabel('authored').connection('author','book').ifNotExists().create()
schema.edgeLabel('created').connection('author','recipe').create()
schema.edgeLabel('created').properties('year').add()
schema.edgeLabel('includes').connection('recipe','ingredient').create()
schema.edgeLabel('includes').properties('amount').add()
schema.edgeLabel('includedIn').connection('recipe','book').create()
schema.edgeLabel('includedIn').connection('recipe','meal').add()
schema.edgeLabel('includedIn').connection('meal', 'book').add()
schema.edgeLabel('rated').connection('reviewer','recipe').create()
schema.edgeLabel('rated').properties('stars').add()
schema.edgeLabel('rated').properties('timestamp').add()
schema.edgeLabel('rated').properties('comment').add()

// Vertex Indexes
// Secondary
schema.vertexLabel('author').index('byName').secondary().by('name').add()
// Materialized
schema.vertexLabel('author').index('byAuthor').materialized().by('name').add()
schema.vertexLabel('book').index('byBook').materialized().by('name').add()
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

// Schema description
// Use to check that the schema is built as desired
schema.describe()
