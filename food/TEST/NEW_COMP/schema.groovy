system.graph('newComp').ifNotExists().create()
:remote config alias g newComp.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// ********
// CALCULATED VALUES
// ********
// THERE ARE SOME CALCULATED VALUES USED IN RECIPE:
// vertexLabel: recipe calcValue: avgRating
// vertexLabel: meal calcValue: calories

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

// SINGLE CARDINALITY PROPERTY KEY
schema.propertyKey('personId').Int().single().create()
schema.propertyKey('mealId').Int().single().create()
schema.propertyKey('itemId').Int().single().create()
schema.propertyKey('recipeId').Int().single().create()
schema.propertyKey('bookId').Int().single().create()
schema.propertyKey('ingredId').Int().single().create()
schema.propertyKey('homeId').Int().single().create()
schema.propertyKey('storeId').Int().single().create()
schema.propertyKey('locId').Text().single().create()
schema.propertyKey('stateId').Int().single().create()
schema.propertyKey('cityId').Int().single().create()
schema.propertyKey('sensorId').Int().single().create()
schema.propertyKey('name').Text().single().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('calGoal').Int().single().create()
schema.propertyKey('macroGoal').Text().single().create()
schema.propertyKey('publishYear').Int().single().create()
schema.propertyKey('ISBN').Text().single().create()
schema.propertyKey('bookDiscount').Text().ttl(604800).create()
schema.propertyKey('instructions').Text().single().create()
schema.propertyKey('notes').Text().single().create()
schema.propertyKey('type').Text().single().create()
schema.propertyKey('servAmt').Text().single().create()
schema.propertyKey('macro').Text().single().create()
schema.propertyKey('calories').Int().single().create()
schema.propertyKey('geoPoint').Point().withGeoBounds().create()
schema.propertyKey('address').Text().single().create()
schema.propertyKey('amount').Text().single().create()
// MULTIPLE CARDINALITY PROPERTY KEY
schema.propertyKey('nickname').Text().multiple().create()
schema.propertyKey('cuisine').Text().multiple().create()
// MULTIPLE CARDINALITY PROPERTY KEY WITH SINGLE CARDINALITY META-PROPERTY
schema.propertyKey('since').Int().single().create() // meta-property
schema.propertyKey('badge').Text().multiple().properties('since').create()
// MULTIPLE CARDINALITY PROPERTY KEY WITH MULTIPLE CARDINALITY META-PROPERTY
schema.propertyKey('startYear').Int().multiple().create()   // meta-property
schema.propertyKey('endYear').Int().multiple().create()   // meta-property
schema.propertyKey('country').Text().multiple().properties('startYear','endYear').create()

// EDGE PROPERTIES
schema.propertyKey('numServ').Int().single().create()
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

// SINGLE-KEY VERTEX ID
schema.vertexLabel('person').partitionKey('personId').create()
// ********* NEED TO ADD BADGE TO PERSON !!! USED FOR REVIEWS *********** 
// ==>{badge=[Silver Badge, Gold Badge], gender=[F], name=[Jane Doe]}
// ==>{badge=[Gold Badge], gender=[M], name=[Jon Doe]}
// gremlin> g.V().properties('badge').valueMap()
// ==>{since=2005}
// ==>{since=2011}
// ==>{since=2012}
schema.vertexLabel('person').properties('name','nickname','gender','calGoal','macroGoal','country').add()
schema.vertexLabel('book').partitionKey('bookId').create()
schema.vertexLabel('book').properties('name','publishYear','ISBN','bookDiscount').add()
schema.vertexLabel('meal_item').partitionKey('itemId').create()
schema.vertexLabel('meal_item').properties('name','servAmt', 'macro', 'calories').add()
schema.vertexLabel('ingredient').partitionKey('ingredId').create()
schema.vertexLabel('ingredient').properties('name').add()
schema.vertexLabel('home').partitionKey('homeId').create()
schema.vertexLabel('home').properties('name','address').add()
schema.vertexLabel('store').partitionKey('storeId').create()
schema.vertexLabel('store').properties('name','address').add()
schema.vertexLabel('location').partitionKey('locId').create()
schema.vertexLabel('location').properties('name', 'geoPoint').add()
schema.vertexLabel('recipe').partitionKey('recipeId').create()
schema.vertexLabel('recipe').properties('name','cuisine', 'instructions','notes').add()
// MULTIPLE-KEY VERTEX ID
schema.vertexLabel('meal').partitionKey('type', 'mealId').create()
// COMPOSITE KEY VERTEX ID
schema.vertexLabel('fridgeSensor').partitionKey('stateId', 'cityId').clusteringKey('sensorId').create()
schema.vertexLabel('fridgeSensor').properties('name').add()

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

schema.edgeLabel('ate').multiple().create()
schema.edgeLabel('ate').properties('mealDate').add()
schema.edgeLabel('ate').connection('person', 'meal').add()
schema.edgeLabel('knows').multiple().create()
schema.edgeLabel('knows').properties('since').add()
schema.edgeLabel('knows').connection('person','person').add()
schema.edgeLabel('includes').multiple().create()
schema.edgeLabel('includes').properties('numServ').add()
schema.edgeLabel('includes').connection('meal','meal_item').add()
schema.edgeLabel('includedIn').multiple().create()
schema.edgeLabel('includedIn').properties('amount').add()
schema.edgeLabel('includedIn').connection('recipe','meal').add()
schema.edgeLabel('includedIn').connection('meal','book').add()
schema.edgeLabel('includedIn').connection('recipe','book').add()
schema.edgeLabel('includedIn').connection('ingredient','recipe').add()
schema.edgeLabel('created').multiple().create()
schema.edgeLabel('created').properties('createDate').add()
schema.edgeLabel('created').connection('person', 'recipe').add()
schema.edgeLabel('reviewed').multiple().create()
schema.edgeLabel('reviewed').properties('time','year','stars','comment').add()
schema.edgeLabel('reviewed').connection('person','recipe').add()
schema.edgeLabel('authored').multiple().create()
schema.edgeLabel('authored').connection('person', 'book').add()
schema.edgeLabel('contains').multiple().ttl(60800).create()
schema.edgeLabel('contains').properties('expireDate').add()
schema.edgeLabel('contains').connection('fridgeSensor','ingredient').add()
schema.edgeLabel('isStockedWith').multiple().ttl(60800).create()
schema.edgeLabel('isStockedWith').properties('expireDate').add()
schema.edgeLabel('isStockedWith').connection('store','ingredient').add()
schema.edgeLabel('isLocatedAt').multiple().create()
schema.edgeLabel('isLocatedAt').connection('home','location').add()
schema.edgeLabel('isLocatedAt').connection('store','location').add()
schema.edgeLabel('isLocatedAt').connection('fridgeSensor','home').add()

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
schema.vertexLabel('location').index('search').search().by('geoPoint').withError(0.000009,0.0).add()
schema.vertexLabel('store').index('search').search().by('name').add()
schema.vertexLabel('home').index('search').search().by('name').add()
schema.vertexLabel('fridgeSensor').index('search').search().by('cityId').by('sensorId').by('name').add()

// ********
// EDGE INDEX
// ********
// SYNTAX:
// index('index_name').
//    [outE('edgeLabel') | inE('edgeLabel') ].
//    by('propertykey_name').
//    add()
// ********

schema.vertexLabel('recipe').index('byStars').inE('reviewed').
  by('stars').ifNotExists().add()
schema.vertexLabel('person').index('ratedByStars').outE('reviewed').
  by('stars').ifNotExists().add()
schema.vertexLabel('person').index('ratedByDate').outE('reviewed').
  by('year').ifNotExists().add()
schema.vertexLabel('person').index('ratedByComments').outE('reviewed').
  by('comment').ifNotExists().add()
schema.vertexLabel('recipe').index('byPersonOrRecipe').bothE('created').
  by('createDate').ifNotExists().add()

// ********
// PROPERTY INDEX using meta-property 'livedIn'
// ********
// SYNTAX:
// index('index_name').
//    property('propertykey_name').
//    by('meta-propertykey_name').
//    add()
// ********

schema.vertexLabel('person').index('byStartYear').
  property('country').by('startYear').add()
schema.vertexLabel('person').index('byEndYear').
  property('country').by('endYear').add()
