// indexing.groovy
// All indexes can be run with this script to test
// :load /Users/lorinapoland/CLONES/graph-examples/food/Gremlin/indexing.groovy

// schema required to run these tests

schema.propertyKey('name').Text().ifNotExists().create()        
schema.propertyKey('gender').Text().ifNotExists().create()
schema.propertyKey('nickname').Text().multiple().ifNotExists().create()
//schema.propertyKey('country').Text().ifNotExists().create()
schema.propertyKey('livedIn').Text().ifNotExists().create()
schema.propertyKey('country').Text().multiple().properties('livedIn').ifNotExists().create()
schema.propertyKey('instructions').Text().ifNotExists().create()
schema.propertyKey('year').Int().ifNotExists().create()
schema.propertyKey('city_id').Int().ifNotExists().create()
schema.propertyKey('sensor_id').Uuid().ifNotExists().create()
schema.propertyKey('location').Point().ifNotExists().create()

schema.vertexLabel('author').properties('name','gender','nickname').ifNotExists().create()
schema.vertexLabel('recipe').properties('name','instructions').ifNotExists().create()
schema.vertexLabel('book').properties('name','year').ifNotExists().create()
schema.vertexLabel('FridgeSensor').partitionKey('city_id').clusteringKey('sensor_id').ifNotExists().create()
schema.vertexLabel('FridgeSensor').properties('location').add()
schema.vertexLabel('reviewer').ifNotExists().create()
schema.edgeLabel('rated').connection('reviewer','recipe').ifNotExists().create()
schema.propertyKey('stars').Int().ifNotExists().create()

// Secondary
schema.vertexLabel('author').index('byName').secondary().by('name').ifNotExists().add()
// Materialized	  		
schema.vertexLabel('recipe').index('byRecipe').materialized().by('name').ifNotExists().add()
//schema.vertexLabel('meal').index('byMeal').materialized().by('name').ifNotExists().add()
//schema.vertexLabel('ingredient').index('byIngredient').materialized().by('name').ifNotExists().add()
schema.vertexLabel('reviewer').index('byReviewer').materialized().by('name').ifNotExists().add()
// Search
schema.vertexLabel('author').index('search').search().by('nickname').
													  by('name').asString().ifNotExists().add()
schema.vertexLabel('book').index('search').search().by('year').ifNotExists().add()
schema.vertexLabel('recipe').index('search').search().by('instructions').asText().
													  by('name').asString().ifNotExists().add()
schema.vertexLabel('FridgeSensor').index('search').search().by('location').ifNotExists().add()

// Edge index
schema.vertexLabel('reviewer').index('ratedByStars').outE('rated').by('stars').ifNotExists().add()

// Property index using meta-property 'livedIn': 
schema.vertexLabel('author').index('byLocation').property('country').by('livedIn').ifNotExists().add()

graph.tx().commit()

// add data required beyond generateRecipe
graph.addVertex(label, 'FridgeSensor', 'name', 'jones1', 'city_id', 100, 'sensor_id', '60bcae02-f6e5-11e5-9ce9-5e5517507c66', 'location', Geo.point(-118.359770, 34.171221))
graph.addVertex(label, 'FridgeSensor', 'name', 'smith1', 'city_id', 100, 'sensor_id', '61deada0-3bb2-4d6d-a606-a44d963f03b5', 'location', Geo.point(-115.655068, 35.163427))
graph.addVertex(label, 'author','name', 'Jamie Oliver', 'nickname', 'Jimmy', 'gender', 'M')


// query traversals
// INT
g.V().hasLabel('book').has('year', lt(1968)).values('name')
// expected answer: The Art of French Cooking, Vol. 1
g.V().hasLabel('book').has('year', gt(1968)).values('name')
// expected answer: Simca's and The Art of Simple Food
g.V().hasLabel('book').has('year', eq(1968)).values('name')
// expected answer: The French Chef Cookbook

// NO DOUBLE queries yet

// STRING
g.V().hasLabel('recipe').has('name', Search.prefix('R')).values('name')
// expected answer: Ratatouille, Roast Pork Loin
g.V().hasLabel('recipe').has('name', Search.regex('.*ee.*')).values('name')
// expected answer: Beef Bourguignon
g.V().hasLabel('recipe').has('name', eq('Carrot Soup')).values('name')
// expected answer: Carrot Soup
g.V().hasLabel('recipe').has('name', eq('Carrot')).values('name')
// expected answer: Will fail, no result

// TEXT
g.V().hasLabel('recipe').has('instructions', Search.token('Saute')).values('name')
// expected answer: Beef Bourguignon, Oysters Rockefeller, Wild Mushrooms Stroganoff
g.V().hasLabel('recipe').has('instructions', Search.tokenPrefix('Sea')).values('name','instructions')
// expected answer: Oysters Rockefeller [seasonings], Roast Pork Loin [Season]
g.V().hasLabel('recipe').has('instructions', Search.tokenRegex('.*sea.*in.*')).values('name','instructions')
// expected answer: Oysters Rockefeller [seasonings]

// GEO
Distance d = Geo.distance(-110,30,20)
g.V().hasLabel('FridgeSensor').has('location', Geo.inside(d)).values('name')
// expected answer: jones1, smith1

// NEGATIVE TEST
// Solr index not used when Solr core doesn't back field
g.V().hasLabel('recipe').has('instructions', Search.regex('.*sea.*in.*').values('name')
// expected answer: Error or no response

// TWO Solr Cores
g.V().has('recipe', 'instructions', Search.tokenRegex('Braise.*')).as('r').
  V().has('author', 'name', Search.prefix('J')).out().where(eq('r')).values('name')
// expected answer: Beef Bourguignon  (finds "Julia Child" and "Beef Bourguignon")
// Repeat replacing 'J' with 'M' and there are no results
g.V().has('recipe', 'instructions', Search.tokenRegex('Braise.*')).as('r').
  V().has('author', 'name', Search.prefix('M')).out().where(eq('r')).values('name')
// expected answer: no result

// TESTING WITH INDEX AND WITHOUT
g.V().hasLabel('author').has('nickname', Search.prefix('J')).values('name')
// expected answer: Jamie Oliver [nickname: Jimmy]
//drop index
schema.vertexLabel('author').index('search').search().properties('nickname').remove()
// try query again
g.V().hasLabel('author').has('nickname', Search.prefix('J')).values('name')
// expected answer: It should fail without full scan.



