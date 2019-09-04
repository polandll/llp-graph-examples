system.graph('dse51').ifNotExists().create()
:remote config alias g dse51.g

schema.clear()
schema.config().option('graph.schema_mode').set('Development')
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
schema.propertyKey('person_id').Int().single().create()
schema.propertyKey('meal_id').Int().single().create()
schema.propertyKey('item_id').Int().single().create()
schema.propertyKey('recipe_id').Int().single().create()
schema.propertyKey('book_id').Int().single().create()
schema.propertyKey('ingred_id').Int().single().create()
schema.propertyKey('home_id').Int().single().create()
schema.propertyKey('store_id').Int().single().create()
schema.propertyKey('loc_id').Text().single().create()
schema.propertyKey('state_id').Int().single().create()
schema.propertyKey('city_id').Int().single().create()
schema.propertyKey('zipcode_id').Int().single().create()
schema.propertyKey('sensor_id').Int().single().create()
schema.propertyKey('name').Text().single().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('cal_goal').Int().single().create()
schema.propertyKey('macro_goal').Text().single().create()
schema.propertyKey('publish_year').Int().single().create()
schema.propertyKey('isbn').Text().single().create()
schema.propertyKey('book_discount').Text().ttl(604800).create()
schema.propertyKey('instructions').Text().single().create()
schema.propertyKey('notes').Text().single().create()
schema.propertyKey('type').Text().single().create()
schema.propertyKey('serv_amt').Text().single().create()
schema.propertyKey('macro').Text().single().create()
schema.propertyKey('calories').Int().single().create()
schema.propertyKey('geo_point').Point().withGeoBounds().create()
schema.propertyKey('address').Text().single().create()
schema.propertyKey('amount').Text().single().create()
// MULTIPLE CARDINALITY PROPERTY KEY
schema.propertyKey('nickname').Text().multiple().create()
schema.propertyKey('cuisine').Text().multiple().create()
// MULTIPLE CARDINALITY PROPERTY KEY WITH SINGLE CARDINALITY META-PROPERTY
schema.propertyKey('since').Int().single().create() // meta-property
schema.propertyKey('badge').Text().multiple().properties('since').create()
// MULTIPLE CARDINALITY PROPERTY KEY WITH MULTIPLE CARDINALITY META-PROPERTY
schema.propertyKey('start_year').Int().multiple().create()   // meta-property
schema.propertyKey('end_year').Int().multiple().create()   // meta-property
schema.propertyKey('country').Text().multiple().properties('start_year','end_year').create()

// EDGE PROPERTIES
schema.propertyKey('num_serv').Int().single().create()
schema.propertyKey('meal_date').Date().single().create()
schema.propertyKey('use_date').Date().single().create()
schema.propertyKey('create_date').Date().single().create()
schema.propertyKey('expire_date').Date().single().create()
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
schema.vertexLabel('person').properties('name','nickname','gender','cal_goal','macro_goal','country').add()
schema.vertexLabel('book').partitionKey('book_id').create()
schema.vertexLabel('book').properties('name','publish_year','isbn','book_discount').add()
schema.vertexLabel('meal_item').partitionKey('item_id').create()
schema.vertexLabel('meal_item').properties('name','serv_amt', 'macro', 'calories').add()
schema.vertexLabel('ingredient').partitionKey('ingred_id').create()
schema.vertexLabel('ingredient').properties('name').add()
schema.vertexLabel('home').partitionKey('home_id').create()
schema.vertexLabel('home').properties('name','address').add()
schema.vertexLabel('store').partitionKey('store_id').create()
schema.vertexLabel('store').properties('name','address').add()
schema.vertexLabel('location').partitionKey('loc_id').create()
schema.vertexLabel('location').properties('name', 'geo_point').add()
schema.vertexLabel('recipe').partitionKey('recipe_id').create()
schema.vertexLabel('recipe').properties('name','cuisine', 'instructions','notes').add()
// MULTIPLE-KEY VERTEX ID
schema.vertexLabel('meal').partitionKey('type', 'meal_id').create()
// COMPOSITE KEY VERTEX ID
schema.vertexLabel('fridge_sensor').partitionKey('state_id', 'city_id', 'zipcode_id').clusteringKey('sensor_id').create()
schema.vertexLabel('fridge_sensor').properties('name').add()

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
schema.edgeLabel('ate').properties('meal_date').add()
schema.edgeLabel('ate').connection('person', 'meal').add()
schema.edgeLabel('knows').multiple().create()
schema.edgeLabel('knows').properties('since').add()
schema.edgeLabel('knows').connection('person','person').add()
schema.edgeLabel('includes').multiple().create()
schema.edgeLabel('includes').properties('num_serv').add()
schema.edgeLabel('includes').connection('meal','meal_item').add()
schema.edgeLabel('includedIn').multiple().create()
schema.edgeLabel('includedIn').properties('amount').add()
schema.edgeLabel('includedIn').connection('recipe','meal').add()
schema.edgeLabel('includedIn').connection('meal','book').add()
schema.edgeLabel('includedIn').connection('recipe','book').add()
schema.edgeLabel('includedIn').connection('ingredient','recipe').add()
schema.edgeLabel('created').multiple().create()
schema.edgeLabel('created').properties('create_date').add()
schema.edgeLabel('created').connection('person', 'recipe').add()
schema.edgeLabel('reviewed').multiple().create()
schema.edgeLabel('reviewed').properties('time','year','stars','comment').add()
schema.edgeLabel('reviewed').connection('person','recipe').add()
schema.edgeLabel('authored').multiple().create()
schema.edgeLabel('authored').connection('person', 'book').add()
schema.edgeLabel('contains').multiple().ttl(60800).create()
schema.edgeLabel('contains').properties('expire_date').add()
schema.edgeLabel('contains').connection('fridge_sensor','ingredient').add()
schema.edgeLabel('isStockedWith').multiple().ttl(60800).create()
schema.edgeLabel('isStockedWith').properties('expire_date').add()
schema.edgeLabel('isStockedWith').connection('store','ingredient').add()
schema.edgeLabel('isLocatedAt').multiple().create()
schema.edgeLabel('isLocatedAt').connection('home','location').add()
schema.edgeLabel('isLocatedAt').connection('store','location').add()
schema.edgeLabel('isLocatedAt').connection('fridge_sensor','home').add()

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
schema.vertexLabel('fridge_sensor').index('search').search().by('cityId').by('sensorId').by('name').add()

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
  by('create_date').ifNotExists().add()

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
  property('country').by('start_year').add()
schema.vertexLabel('person').index('byEndYear').
  property('country').by('end_year').add()
