system.graph('newComp').ifNotExists().create()
:remote config alias g newComp.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// ********
// PROPERTY KEYS
// ********
// SYNTAX:
// propertyKey('name').
//    type().
//    [ single() | multiple() ].
//    [ ttl ].
//    [ properties(metadata_property) ].
//    [ ifNotExists() ].
//    [ create() | add() | describe() | exists() ]
// DEFAULT IS SINGLE CARDINALITY
// ********

schema.propertyKey('personId').Int().single().create()
schema.propertyKey('mealId').Int().single().create()
schema.propertyKey('itemId').Int().single().create()
schema.propertyKey('recipeId').Int().single().create()
schema.propertyKey('bookId').Int().single().create()
schema.propertyKey('ingredId').Int().single().create()
schema.propertyKey('homeId').Int().single().create()
schema.propertyKey('storeId').Int().single().create()
schema.propertyKey('locId').Int().single().create()
schema.propertyKey('cityId').Int().single().create()
schema.propertyKey('sensorId').Int().single().create()

schema.propertyKey('name').Text().single().create()
schema.propertyKey('nickname').Text().multiple().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('calGoal').Int().single().create()
schema.propertyKey('macroGoal').Text().single().create()
schema.propertyKey('startYear').Int().create()   // meta-property
schema.propertyKey('endYear').Int().create()   // meta-property
schema.propertyKey('country').Text().multiple().properties('startYear','endYear').create()
schema.propertyKey('publishYear').Int().single().create()
schema.propertyKey('ISBN').Text().single().create()
schema.propertyKey('bookDiscount').Text().ttl(604800).create()
schema.propertyKey('cuisine').Text().multiple().create()
schema.propertyKey('instructions').Text().single().create()
schema.propertyKey('notes').Text().single().create()
schema.propertyKey('type').Text().single().create()
schema.propertyKey('servingAmt').Text().single().create()
schema.propertyKey('macro').Text().single().create()
schema.propertyKey('calories').Int().single().create()
schema.propertyKey('geoPoint').Point().withGeoBounds().create()

// EDGE PROPERTIES
schema.propertyKey('numServings').Int().single().create()
schema.propertyKey('since').Text().single().create()
schema.propertyKey('mealDate').Date().single().create()
schema.propertyKey('useDate').Date().single().create()
schema.propertyKey('createDate').Date().single().create()
schema.propertyKey('expireDate').Date().single().create()
schema.propertyKey('stars').Int().single().create()
schema.propertyKey('time').Time().single().create()
schema.propertyKey('year').Date().single().create()
schema.propertyKey('comment').Text().single().create()

// ********
// VERTEX LABELS
// ********
// SYNTAX:
// schema.vertexLabel('vertexLabel').
//    [ partitionKey(propertyKey, [ partitionKey(propertyKey) ]) ].
//    [ clusteringKey(propertyKey) ].
//    [ ttl ].
//    [ properties(property, property) ].
//    [ index ].
//    [ partition() ].
//    [ cache() ].
//    [ ifNotExists() ].
//    [ create() | add() | describe() | exists() ]
// ********

schema.vertexLabel('person').partitionKey('personId').create()
schema.vertexLabel('person').properties('name','nickname','gender','calGoal','macroGoal','country').add()
schema.vertexLabel('book').partitionKey('bookId').create()
schema.vertexLabel('book').properties('name','publishDate','ISBN','bookDiscount').add()
schema.vertexLabel('meal_item').partitionKey('itemId').create()
schema.vertexLabel('meal_item').properties('name','servingAmt', 'macro', 'calories').add()
schema.vertexLabel('meal').partitionKey('mealId').create()
schema.vertexLabel('meal').properties('type').add()
schema.vertexLabel('ingredient').partitionKey('ingredId').create()
schema.vertexLabel('ingredient').properties('name').create()
schema.vertexLabel('recipe').partitionKey('recipeId').create()
schema.vertexLabel('recipe').properties('name','cuisine','instructions','notes').create()
schema.vertexLabel('home').partitionKey('homeId').create()
schema.vertexLabel('home').properties('name','address').create()
schema.vertexLabel('store').partitionKey('storeId').create()
schema.vertexLabel('store').properties('name','address').create()
schema.vertexLabel('location').partitionKey('locId').create()
schema.vertexLabel('location').properties('geoPoint').create()
schema.vertexLabel('fridgeSensor').partitionKey(('cityId','sensorId')).create()
schema.vertexLabel('fridgeSensor').properties('name').create()

// ********
// EDGE LABELS
// ********
// SYNTAX:
//schema.edgeLabel('edgeLabel').
//    [ single() | multiple() ].
//    [ connection( outVertex, inVertex) ].
//    [ ttl ].
//    [ properties(property[, property]) ].
//    [ ifNotExists() ].
//    [ create() | add() | describe() | exists() ]
// DEFAULT IS MULTIPLE CARDINALITY
// ********

schema.edgeLabel('knows').multiple().create()
schema.edgeLabel('knows').properties('since').add()
schema.edgeLabel('knows').connection('person', 'person').add()
schema.edgeLabel('includes').multiple().create()
schema.edgeLabel('includes').properties('numServ').add()
schema.edgeLabel('includes').connection('meal_item','meal').add()
schema.edgeLabel('includedIn').multiple().create()
schema.edgeLabel('includedIn').properties('amount').add()
schema.edgeLabel('includedIn').connection('recipe','meal').add()
schema.edgeLabel('includedIn').connection('meal','book').add()
schema.edgeLabel('includedIn').connection('recipe','book').add()
schema.edgeLabel('includedIn').connection('ingredient','recipe').add()
schema.edgeLabel('ate').multiple().create()
schema.edgeLabel('ate').properties('mealDate').add()
schema.edgeLabel('ate').connection('person', 'meal').add()
schema.edgeLabel('created').multiple().create()
schema.edgeLabel('created').properties('createDate').add()
schema.edgeLabel('created').connection('person', 'recipe').add()
schema.edgeLabel('reviewed').multiple().create()
schema.edgeLabel('reviewed').properties('time','year','stars','comment').add()
schema.edgeLabel('reviewed').connection('person', 'recipe').add()
schema.edgeLabel('authored').multiple().create()
schema.edgeLabel('authoried').connection('person', 'book').add()
schema.edgeLabel('contains').multiple().ttl(60800).create()
schema.edgeLabel('contains').properties('expireDate').add()
schema.edgeLabel('contains').connection('fridge_sensor', 'ingredient').add()
schema.edgeLabel('isStockedWith').multiple().ttl(60800).create()
schema.edgeLabel('isStockedWith').properties('expireDate').add()
schema.edgeLabel('isStockedWith').connection('store', 'ingredient').add()

// ********
// VERTEX INDEX
// ********
// SYNTAX:
// index('index_name').
//    [secondary() | materialized() | search()].
//    by('propertykey_name').
//    [ asText() | asString() ].
//    add()
// ********

schema.vertexLabel('person').index('byName').materialized().by('name').add()
schema.vertexLabel('meal_item').index('byName').materialized().by('name').add()
schema.vertexLabel('meal').index('byType').materialized().by('type').add()
//schema.vertexLabel('recipe').index('byCuisine').materialized().by('cuisine').add()
//schema.vertexLabel('book').index('byName').materialized().by('name').add()
schema.vertexLabel('ingredient').index('byName').materialized().by('name').add()

schema.vertexLabel('recipe').index('search').search().
  by('instructions').by('name').by('cuisine').add()
schema.vertexLabel('book').index('search').search().
  by('name').by('publishYear').add()
schema.vertexLabel('location').index('search').search().by('point').add()
schema.vertexLabel('location').index('search').search().
  by('point').withError(0.000009,0.0).ifNotExists().add()
schema.vertexLabel('store').index('search').search().by('name').by('location').add()

// ********
// EDGE INDEX
// ********
// SYNTAX:
// index('index_name').
//    [outE('edgeLabel') | inE('edgeLabel') ].
//    by('propertykey_name').
//    add()
// ********

schema.vertexLabel('recipe').index('byStars').inE('rated').
  by('stars').ifNotExists().add()
schema.vertexLabel('person').index('ratedByStars').outE('rated').
  by('stars').ifNotExists().add()
schema.vertexLabel('person').index('ratedByDate').outE('rated').
  by('year').ifNotExists().add()
schema.vertexLabel('person').index('ratedByComments').outE('rated').
  by('comment').ifNotExists().add()
schema.vertexLabel('recipe').index('byPersonOrRecipe').bothE('created').
  by('publishYear').ifNotExists().add()

// ********
// PROPERTY INDEX using meta-property 'livedIn'
// ********
// SYNTAX:
// index('index_name').
//    property('propertykey_name').
//    by('meta-propertykey_name').
//    add()
// ********

schema.vertexLabel('author').index('byStartYear').
  property('country').by('startYear').ifNotExists().add()
