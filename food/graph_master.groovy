---------------------------------
|  // FOR CLEANUP                |
|     g.V().drop().iterate()     |
|     system.graph('test').drop()|
|  // TO GET WHAT GRAPHS EXIST   |
|     system.graphs()            |
---------------------------------

// CONNECT TO GREMLIN SERVER - OLD STYLE
gremlin> :remote connect tinkerpop.server resources/graph/gremlin-console/conf/remote-objects.yaml
==>Connected - localhost/127.0.0.1:8182
// CONNECT TO GREMLIN SERVER - NEW STYLE
// automatically connected remotely, but can drop into local mode with:
gremlin> :remote console
// can reconnect remotely using the same command (it's a toggle):
gremlin> :remote console

// CREATE GRAPH "TEST"
// Gremlin console only, Studio creates graphs from connections
gremlin> system.graph('test').ifNotExists().create()
==>null

// CONFIG GRAPH TRAVERSAL g TO ALIAS test.g
// Gremlin console only, Studio configs graph traversals from connections
gremlin> :remote config alias g test.g
==>g=test.g

// CONFIG TIMEOUT TO MAXIMUM VALUE
// Gremlin console only, Studio cannot do this command
gremlin> :remote config timeout max
==>Set remote timeout to 2147483647ms

*******************
* Getting Started *
*******************

// CHECK THAT NO VERTICES EXIST
// BAD TO DO FOR LARGE GRAPHS!!
gremlin> g.V().count()
=>0

// ENTER AUTHOR VERTEX
gremlin> juliaChild = graph.addVertex(label,'author', 'name','Julia Child', 'gender','F')
==>v[{member_id=1, community_id=184435, ~label=author}]

// ENTER BOOK VERTEX
gremlin> artOfFrenchCookingVolOne = graph.addVertex(label, 'book','name', 'The Art of French Cooking, Vol. 1', 'year', 1961)
==>v[{member_id=2, community_id=264788, ~label=book}]

// ENTER AUTHOR-BOOK EDGE
gremlin> juliaChild.addEdge('authored', artOfFrenchCookingVolOne)
==>e[{out_vertex={member_id=1, community_id=184435, ~label=author}, local_id=312468c0-b99a-11e5-bdeb-e778035ca1a5, 
in_vertex={member_id=2, community_id=113313, ~label=book}, ~type=authored}][{member_id=2, community_id=184435, 
~label=author}-authored->{member_id=2, community_id=113313, ~label=book}]

// GET VERTEX FOR PARTICULAR AUTHOR
gremlin> g.V().has('name','Julia Child')
==>v[{member_id=1, community_id=184435, ~label=author}]
 
// GET VALUEMAP
gremlin> g.V().valueMap()
==>[gender:[F], name:[Julia Child], id:[1]]

// SHOW USE OF VALUES FOR ONE PROPERTY
gremlin> g.V().values('name')
==>Julia Child
==>The Art of French Cooking, Vol. 1

// SHOW EDGE INFORMATION
g.E().hasLabel('authored')
==>e[{out_vertex={~label=author, member_id=34, community_id=1733329920}, 
  local_id=72086b3b-0e6c-11e6-b5e4-0febe4822aa4, 
  in_vertex={~label=book, member_id=52, community_id=1733329920}, ~type=authored}]
    [{~label=author, member_id=34, community_id=1733329920}-authored->
     {~label=book, member_id=52, community_id=1733329920}]

// CHECK THAT ONE EDGE NOW EXISTS
gremlin> g.E().count()
=>1

// CHECK THAT TWO VERTICES NOW EXISTS
gremlin> g.V().count()
=>2

// CLEAR SCHEMA
schema.clear()
==>null

// CREATE SCHEMA
// To run in Studio, copy and paste all lines to a cell and run.

// To run in Gremlin console, use the load command
// :load /Users/lorinapoland/CLONES/graph-examples/food/RecipeSchema.groovy
// DOC: :load /tmp/RecipeSchema.groovy

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

// SAMPLE RESULTS FROM GREMLIN CONSOLE
// A series of returns  for vertices and edges will mark the successful completion of the script
// Sample vertex
==>v[{~label=author, member_id=0, community_id=1878171264}]
// Sample edge
==>e[{out_vertex={~label=meal, member_id=27, community_id=1989847424}, 
local_id=545b88b0-0e7b-11e6-b5e4-0febe4822aa4, 
in_vertex={~label=book, member_id=10, community_id=1878171264}, 
~type=includedIn}]
[{~label=meal, member_id=27, community_id=1989847424}-includedIn->{~label=book, member_id=10, community_id=1878171264}]

// GREMLIN CONSOLE ONLY
// commit the transaction to save the schema
graph.tx().commit()

// Schema description
// Use to check that the schema is built as desired
schema.describe()

// SCRIPT TO INSERT DATA
// generateRecipe.groovy 
// Generates all Recipe Toy Graph vertices and edges except Reviews

// For DataStax Studio, copy and paste entire script into a cell and run

// For Gremlin console, use the load command with the file
// :load /Users/lorinapoland/CLONES/graph-examples/food/Gremlin/generateRecipe.groovy
// DOC: :load /tmp/generateRecipe.groovy

// Drop previously loaded data before inserting fresh data
g.V().drop().iterate()

// author vertices
juliaChild = graph.addVertex(label, 'author', 'name','Julia Child', 'gender', 'F')
simoneBeck = graph.addVertex(label, 'author', 'name', 'Simone Beck', 'gender', 'F')
louisetteBertholie = graph.addVertex(label, 'author', 'name', 'Louisette Bertholie', 'gender', 'F')
patriciaSimon = graph.addVertex(label, 'author', 'name', 'Patricia Simon', 'gender', 'F')
aliceWaters = graph.addVertex(label, 'author', 'name', 'Alice Waters', 'gender', 'F')
patriciaCurtan = graph.addVertex(label, 'author', 'name', 'Patricia Curtan', 'gender', 'F')
kelsieKerr = graph.addVertex(label, 'author', 'name', 'Kelsie Kerr', 'gender', 'F')
fritzStreiff = graph.addVertex(label, 'author', 'name', 'Fritz Streiff', 'gender', 'M')
emerilLagasse = graph.addVertex(label, 'author', 'name', 'Emeril Lagasse', 'gender', 'M')
jamesBeard = graph.addVertex(label, 'author', 'name', 'James Beard', 'gender', 'M')

// book vertices
artOfFrenchCookingVolOne = graph.addVertex(label, 'book', 'name', 'The Art of French Cooking, Vol. 1', 'year', 1961)
simcasCuisine = graph.addVertex(label, 'book', 'name', "Simca's Cuisine: 100 Classic French Recipes for Every Occasion", 'year', 1972, 'ISBN', '0-394-40152-2')
frenchChefCookbook = graph.addVertex(label, 'book', 'name','The French Chef Cookbook', 'year', 1968, 'ISBN', '0-394-40135-2')
artOfSimpleFood = graph.addVertex(label, 'book', 'name', 'The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution', 'year', 2007, 'ISBN', '0-307-33679-4')

// recipe vertices
beefBourguignon = graph.addVertex(label, 'recipe', 'name', 'Beef Bourguignon', 'instructions', 'Braise the beef. Saute the onions and carrots. Add wine and cook in a dutch oven at 425 degrees for 1 hour.')
ratatouille = graph.addVertex(label, 'recipe', 'name', 'Rataouille', 'instructions', 'Peel and cut the egglant. Make sure you cut eggplant into lengthwise slices that are about 1-inch wmyIde, 3-inches long, and 3/8-inch thick')
saladeNicoise = graph.addVertex(label, 'recipe', 'name', 'Salade Nicoise', 'instructions', 'Take a salad bowl or platter and line it with lettuce leaves, shortly before serving. Drizzle some olive oil on the leaves and dust them with salt.')
wildMushroomStroganoff = graph.addVertex(label, 'recipe', 'name', 'Wild Mushroom Stroganoff', 'instructions', 'Cook the egg noodles according to the package directions and keep warm. Heat 1 1/2 tablespoons of the oliveoil in a large saute pan over medium-high heat.')
spicyMeatloaf = graph.addVertex(label, 'recipe', 'name', 'Spicy Meatloaf', 'instructions', 'Preheat the oven to 375 degrees F. Cook bacon in a large skillet over medium heat until very crisp and fat has rendered, 8-10 minutes.')
oystersRockefeller = graph.addVertex(label, 'recipe', 'name', 'Oysters Rockefeller', 'instructions', 'Saute the shallots, celery, herbs, and seasonings in 3 tablespoons of the butter for 3 minutes. Add the watercress and let it wilt.')
carrotSoup = graph.addVertex(label, 'recipe', 'name', 'Carrot Soup', 'instructions', 'In a heavy-bottomed pot, melt the butter. When it starts to foam, add the onions and thyme and cook over medium-low heat until tender, about 10 minutes.')
roastPorkLoin = graph.addVertex(label, 'recipe', 'name', 'Roast Pork Loin', 'instructions', 'The day before, separate the meat from the ribs, stopping about 1 inch before the end of the bones. Season the pork liberally insmyIde and out with salt and pepper and refrigerate overnight.')

// ingredients vertices
beef = graph.addVertex(label, 'ingredient', 'name', 'beef')
onion = graph.addVertex(label, 'ingredient', 'name', 'onion')
mashedGarlic = graph.addVertex(label, 'ingredient', 'name', 'mashed garlic')
butter = graph.addVertex(label, 'ingredient', 'name', 'butter')
tomatoPaste = graph.addVertex(label, 'ingredient', 'name', 'tomato paste')
eggplant = graph.addVertex(label, 'ingredient', 'name', 'eggplant')
zucchini = graph.addVertex(label, 'ingredient', 'name', 'zucchini')
oliveOil = graph.addVertex(label, 'ingredient', 'name', 'olive oil')
yellowOnion = graph.addVertex(label, 'ingredient', 'name', 'yellow onion')
greenBean = graph.addVertex(label, 'ingredient', 'name', 'green beans')
tuna = graph.addVertex(label, 'ingredient', 'name', 'tuna')
tomato = graph.addVertex(label, 'ingredient', 'name', 'tomato')
hardBoiledEgg = graph.addVertex(label, 'ingredient', 'name', 'hard-boiled egg')
eggNoodles = graph.addVertex(label, 'ingredient', 'name', 'egg noodles')
mushroom = graph.addVertex(label, 'ingredient', 'name', 'mushrooms')
bacon = graph.addVertex(label, 'ingredient', 'name', 'bacon')
celery = graph.addVertex(label, 'ingredient', 'name', 'celery')
greenBellPepper = graph.addVertex(label, 'ingredient', 'name', 'green bell pepper')
groundBeef = graph.addVertex(label, 'ingredient', 'name', 'ground beef')
porkSausage = graph.addVertex(label, 'ingredient', 'name', 'pork sausage')
shallot = graph.addVertex(label, 'ingredient', 'name', 'shallots')
chervil = graph.addVertex(label, 'ingredient', 'name', 'chervil')
fennel = graph.addVertex(label, 'ingredient', 'name', 'fennel')
parsley = graph.addVertex(label, 'ingredient', 'name', 'parsley')
oyster = graph.addVertex(label, 'ingredient', 'name', 'oyster')
pernod = graph.addVertex(label, 'ingredient', 'name', 'Pernod')
thyme = graph.addVertex(label, 'ingredient', 'name', 'thyme')
carrot = graph.addVertex(label, 'ingredient', 'name', 'carrots')
chickenBroth = graph.addVertex(label, 'ingredient', 'name', 'chicken broth')
porkLoin = graph.addVertex(label, 'ingredient', 'name', 'pork loin')
redWine = graph.addVertex(label, 'ingredient', 'name', 'red wine')

// meal vertices
SaturdayFeast = graph.addVertex(label, 'meal', 'name', 'Saturday Feast', 'timestamp', Instant.parse('2015-11-30T00:00:00.00Z'), 'calories', 1000)
EverydayDinner = graph.addVertex(label, 'meal', 'name', 'EverydayDinner', 'timestamp', Instant.parse('2016-01-14T00:00:00.00Z'), 'calories', 600)
JuliaDinner = graph.addVertex(label, 'meal', 'name', 'JuliaDinner', 'timestamp', Instant.parse('2016-01-14T00:00:00.00Z'), 'calories', 900)

// author-book edges
juliaChild.addEdge('authored', artOfFrenchCookingVolOne)
simoneBeck.addEdge('authored', artOfFrenchCookingVolOne)
louisetteBertholie.addEdge('authored', artOfFrenchCookingVolOne)
simoneBeck.addEdge('authored', simcasCuisine)
patriciaSimon.addEdge('authored', simcasCuisine)
juliaChild.addEdge('authored', frenchChefCookbook)
aliceWaters.addEdge('authored', artOfSimpleFood)
patriciaCurtan.addEdge('authored', artOfSimpleFood)
kelsieKerr.addEdge('authored', artOfSimpleFood)
fritzStreiff.addEdge('authored', artOfSimpleFood)

// author - recipe edges
juliaChild.addEdge('created', beefBourguignon, 'year', 1961)
juliaChild.addEdge('created', ratatouille, 'year', 1965)
juliaChild.addEdge('created', saladeNicoise, 'year', 1962)
emerilLagasse.addEdge('created', wildMushroomStroganoff, 'year', 2003)
emerilLagasse.addEdge('created', spicyMeatloaf, 'year', 2000)
aliceWaters.addEdge('created', carrotSoup, 'year', 1995)
aliceWaters.addEdge('created', roastPorkLoin, 'year', 1996)
jamesBeard.addEdge('created', oystersRockefeller, 'year', 1970)

// recipe - ingredient edges
beefBourguignon.addEdge('includes', beef, 'amount', '2 lbs')
beefBourguignon.addEdge('includes', onion, 'amount', '1 sliced')
beefBourguignon.addEdge('includes', mashedGarlic, 'amount', '2 cloves')
beefBourguignon.addEdge('includes', butter, 'amount', '3.5 Tbsp')
beefBourguignon.addEdge('includes', tomatoPaste, 'amount', '1 Tbsp')
beefBourguignon.addEdge('includes', carrot, 'amount', '3 carrots chopped')
ratatouille.addEdge('includes', eggplant, 'amount', '1 lb')
ratatouille.addEdge('includes', zucchini, 'amount', '1 lb')
ratatouille.addEdge('includes', mashedGarlic, 'amount', '2 cloves')
ratatouille.addEdge('includes', oliveOil, 'amount', '4-6 Tbsp')
ratatouille.addEdge('includes', yellowOnion, 'amount', '1 1/2 cups or 1/2 lb thinly sliced')
saladeNicoise.addEdge('includes', oliveOil, 'amount', '2-3 Tbsp')
saladeNicoise.addEdge('includes', greenBean, 'amount', '1 1/2 lbs blanched, trimmed')
saladeNicoise.addEdge('includes', tuna, 'amount', '8-10 ozs oil-packed, drained and flaked')
saladeNicoise.addEdge('includes', tomato, 'amount', '3 or 4 red, peeled, quartered, cored, and seasoned')
saladeNicoise.addEdge('includes', hardBoiledEgg, 'amount', '8 halved lengthwise')
wildMushroomStroganoff.addEdge('includes', eggNoodles, 'amount', '16 ozs wmyIde')
wildMushroomStroganoff.addEdge('includes', mushroom, 'amount', '2 lbs wild or exotic, cleaned, stemmed, and sliced')
wildMushroomStroganoff.addEdge('includes', yellowOnion, 'amount', '1 cup thinly sliced')
spicyMeatloaf.addEdge('includes', bacon, 'amount', '3 ozs diced')
spicyMeatloaf.addEdge('includes', onion, 'amount', '2 cups finely chopped')
spicyMeatloaf.addEdge('includes', celery, 'amount', '2 cups finely chopped')
spicyMeatloaf.addEdge('includes', greenBellPepper, 'amount', '1/4 cup finely chopped')
spicyMeatloaf.addEdge('includes', porkSausage, 'amount', '3/4 lbs hot')
spicyMeatloaf.addEdge('includes', groundBeef, 'amount', '1 1/2 lbs chuck')
oystersRockefeller.addEdge('includes', shallot, 'amount', '1/4 cup chopped')
oystersRockefeller.addEdge('includes', celery, 'amount', '1/4 cup chopped')
oystersRockefeller.addEdge('includes', chervil, 'amount', '1 tsp')
oystersRockefeller.addEdge('includes', fennel, 'amount', '1/3 cup chopped')
oystersRockefeller.addEdge('includes', parsley, 'amount', '1/3 cup chopped')
oystersRockefeller.addEdge('includes', oyster, 'amount', '2 dozen on the half shell')
oystersRockefeller.addEdge('includes', pernod, 'amount', '1/3 cup')
carrotSoup.addEdge('includes', butter, 'amount', '4 Tbsp')
carrotSoup.addEdge('includes', onion, 'amount', '2 medium sliced')
carrotSoup.addEdge('includes', thyme, 'amount', '1 sprig')
carrotSoup.addEdge('includes', carrot, 'amount', '2 1/2 lbs, peeled and sliced')
carrotSoup.addEdge('includes', chickenBroth, 'amount', '6 cups')
roastPorkLoin.addEdge('includes', porkLoin, 'amount', '1 bone-in, 4-rib')
roastPorkLoin.addEdge('includes', redWine, 'amount', '1/2 cup')
roastPorkLoin.addEdge('includes', chickenBroth, 'amount', '1 cup')

// book - recipe edges
beefBourguignon.addEdge('includedIn', artOfFrenchCookingVolOne)
saladeNicoise.addEdge('includedIn', artOfFrenchCookingVolOne)
carrotSoup.addEdge('includedIn', artOfSimpleFood)

// meal - recipe edges
beefBourguignon.addEdge('includedIn', SaturdayFeast)
carrotSoup.addEdge('includedIn', SaturdayFeast)
oystersRockefeller.addEdge('includedIn', SaturdayFeast)
carrotSoup.addEdge('includedIn', EverydayDinner)
roastPorkLoin.addEdge('includedIn', EverydayDinner)
beefBourguignon.addEdge('includedIn', JuliaDinner)
saladeNicoise.addEdge('includedIn', JuliaDinner)

// meal - book edges
EverydayDinner.addEdge('includedIn', artOfSimpleFood)
SaturdayFeast.addEdge('includedIn', simcasCuisine)
JuliaDinner.addEdge('includedIn', artOfFrenchCookingVolOne)

// IN STUDIO, use the following command as the last line to display the graph viz
g.V()

// CHECK THE VERTEX COUNT
g.V().count()
==>56

// GET VERTEX FOR PARTICULAR AUTHOR
g.V().has('author', 'name','Julia Child')
==>v[{member_id=1, community_id=184435, ~label=author}]

// GET EDGES FROM PARTICULAR AUTHOR VERTEX
g.V().has('name','Julia Child').outE('authored')
==>e[{out_vertex={member_id=0, community_id=966350, ~label=author, group_id=3}, local_id=1c947f80-ba67-11e5-bdeb-e778035ca1a5, in_vertex={member_id=0, community_id=966350, ~label=book, group_id=14}, ~type=authored}][{member_id=0, community_id=966350, ~label=author, group_id=3}-authored->{member_id=0, community_id=966350, ~label=book, group_id=14}]
==>e[{out_vertex={member_id=0, community_id=184435, ~label=author, group_id=4}, local_id=c41d5980-ba61-11e5-bdeb-e778035ca1a5, in_vertex={member_id=0, community_id=966350, ~label=book, group_id=1}, ~type=authored}][{member_id=0, community_id=184435, ~label=author, group_id=4}-authored->{member_id=0, community_id=966350, ~label=book, group_id=1}]

// GET BOOK TITLES FOR ALL AUTHORS
// Note duplication for multiple authors
g.V().outE('authored').inV().values('name')
==>The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution
==>The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution
==>Simca's Cuisine: 100 Classic French Recipes for Every Occasion
==>The Art of French Cooking, Vol. 1
==>The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution
==>The Art of French Cooking, Vol. 1
==>Simca's Cuisine: 100 Classic French Recipes for Every Occasion
==>The Art of French Cooking, Vol. 1
==>The French Chef Cookbook

// DEDUPLICATE THE LAST LISTING
g.V().outE('authored').inV().values('name').dedup()
==>Simca's Cuisine: 100 Classic French Recipes for Every Occasion
==>The Art of French Cooking, Vol. 1
==>The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution
==>The French Chef Cookbook

// GET BOOK TITLES FOR PARTICULAR AUTHOR
// Uses outE() and inV() which is overkill - edge information is not needed, just passed through
g.V().has('name','Julia Child').outE('authored').inV().values('name')
==>The Art of French Cooking, Vol. 1
==>The French Chef Cookbook

// GET BOOK TITLES FOR PARTICULAR AUTHOR
// Use out() which gets next vertex
g.V().has('name','Julia Child').out('authored').values('name')
==>The Art of French Cooking, Vol. 1
==>The French Chef Cookbook

// GET BOOK TITLES FOR PARTICULAR AUTHOR WITH A PUBLISH DATE GREATER THAN 1967
g.V().has('name','Julia Child').out('authored').has('year', gt(1967)).values('name')
==>The French Chef Cookbook

// FOR EACH VERTEX LABEL, GET THE NUMBER OF VERTICES
gremlin> :> g.V().label().groupCount()
==>[ingredient:31, author:10, book:4, recipe:8]

// WRITE THE DATA TO A GRYO FILE
graph.io(gryo()).writeGraph('/tmp/recipe.gryo')
==>NULL

// LOAD DATA USING GRYO
graph.io(gryo()).readGraph('/tmp/recipe.gryo')
==>NULL

***********************
* END Getting Started *
***********************

***********************
* OLTP vs OLAP        *
***********************

g.V().in().has('name','Julia Child').count()
===>6

 g.V().in().has('name','Julia Child').count().profile()
==>Traversal Metrics
Step                                                               Count  Traversers       Time (ms)    % Dur
=============================================================================================================
DsegGraphStep([],vertex)                                              61          61           6.733    17.52
  query-optimizer                                                                              0.052
  iterator-setup                                                                               0.015
DsegVertexStep(IN,vertex)                                             78          78          19.496    50.72
  query-optimizer-total                                                                        0.328
  iterator-setup-total                                                                         0.313
HasStep([name.eq(Julia Child)])                                        5           5          12.147    31.60
CountGlobalStep                                                        1           1           0.040     0.11
SideEffectCapStep([~metrics])                                          1           1           0.022     0.06
                                            >TOTAL                     -           -          38.441        -
                                            
g.V().in('created').has('name','Julia Child').count()
===>3

g.V().in('created').has('name','Julia Child').count().profile()
==>Traversal Metrics
Step                                                               Count  Traversers       Time (ms)    % Dur
=============================================================================================================
DsegGraphStep([],vertex)                                              61          61           9.750    33.94
  query-optimizer                                                                              0.075
  iterator-setup                                                                               0.028
DsegVertexStep(IN,[created],vertex)                                    8           8          17.056    59.38
  query-optimizer-total                                                                        0.732
  iterator-setup-total                                                                         0.420
HasStep([name.eq(Julia Child)])                                        3           3           1.856     6.46
CountGlobalStep                                                        1           1           0.033     0.12
SideEffectCapStep([~metrics])                                          1           1           0.028     0.10
                                            >TOTAL                     -           -          28.725        -
                                            
g.V().hasLabel('recipe').in().has('name','Julia Child').count()
===>3

g.V().hasLabel('recipe').in().has('name','Julia Child').count().profile()
==>Traversal Metrics
Step                                                               Count  Traversers       Time (ms)    % Dur
=============================================================================================================
DsegGraphStep([~label.eq(recipe)])                                     8           8           7.940    28.76
  query-optimizer                                                                              0.084
  iterator-setup                                                                               0.011
DsegVertexStep(IN,vertex)                                             22          22          10.708    38.78
  query-optimizer-total                                                                        0.093
  iterator-setup-total                                                                         0.061
HasStep([name.eq(Julia Child)])                                        3           3           8.784    31.81
CountGlobalStep                                                        1           1           0.126     0.46
SideEffectCapStep([~metrics])                                          1           1           0.053     0.19
                                            >TOTAL                     -           -          27.613        -
                                            
g.V().has('author', 'name', 'Julia Child').outE('created').count()
===>3

g.V().has('author','name','Julia Child').outE('created').count().profile()
==>Traversal Metrics
Step                                                               Count  Traversers       Time (ms)    % Dur
=============================================================================================================
DsegGraphStep([~label.eq(author), name.eq(Julia...                     1           1           2.248    77.28
  query-optimizer                                                                              0.210
  iterator-setup                                                                               0.013
DsegVertexStep(OUT,[created],edge)                                     3           3           0.618    21.25
  query-optimizer                                                                              0.097
  iterator-setup                                                                               0.008
CountGlobalStep                                                        1           1           0.029     1.00
SideEffectCapStep([~metrics])                                          1           1           0.013     0.47
                                            >TOTAL                     -           -           2.909        -
                                            
***********************
* END OLTP vs OLAP    *
***********************

***********************
* ADDL SCHEMA         *
***********************  

// Create multiple cardinality property key and add data with list (array)
schema.propertyKey('item_mult').Text().multiple().ifNotExists().create()
schema.vertexLabel('fridgeItem_multiple').properties('item_mult').ifNotExists().create()
graph.addVertex(label,'fridgeItem_multiple','name', 'item1','item_mult', ['cheese', 'cheddar cheese'])
graph.addVertex(label, 'fridgeItem_multiple','name', 'item2', 'item_mult',['yogurt','Greek yogurt'], 'item_mult', ['key lime yogurt'])

g.V().hasLabel('fridgeItem_multiple').valueMap()
==>{item_mult=[cheddar cheese, cheese], name=[item1]}
==>{item_mult=[Greek yogurt, key lime yogurt, yogurt], name=[item2]}

g.V().has('fridgeItem_multiple', 'name', 'item2').values('item_mult')
==>Greek yogurt
==>key lime yogurt
==>yogurt

g.V().hasLabel('fridgeItem_multiple').has('item_mult', 'Greek yogurt').valueMap()
==>{item_mult=[Greek yogurt, key lime yogurt, yogurt], name=[item2]}

***********************
* END ADDL SCHEMA     *
***********************

***************************
* CREATE CUSTOM VERTEX ID *
***************************

// CREATE PARTITION KEY
schema.propertyKey('sensor_id').Uuid().create()
schema.vertexLabel('FridgeSensor').partitionKey('sensor_id').create()
graph.addVertex(label, 'FridgeSensor','sensor_id', '60bcae02-f6e5-11e5-9ce9-5e5517507c66')

// CREATE PARTITION AND CLUSTERING KEYS
schema.propertyKey('city_id').Int().create()
schema.vertexLabel('FridgeSensor').partitionKey('city_id').clusteringKey('sensor_id').create()
graph.addVertex(label, 'FridgeSensor','sensor_id', '60bcae02-f6e5-11e5-9ce9-5e5517507c66', 'city_id', 100)

// CREATE COMPOSITE CUSTOM VERTEX ID
schema.vertexLabel('FridgeSensor').partitionKey('city_id').partitionKey('sensor_id').create()

*******************************
* END CREATE CUSTOM VERTEX ID *
*******************************

***********************
* MODIFY SCHEMA       *
***********************  

schema.describe()

// ADD PROPERTY KEY TO VERTEX LABEL
schema.propertyKey('nationality').Text().create()
schema.vertexLabel('author').properties('nationality').add()
==>null

schema.vertexLabel('author').describe()
==>schema.vertexLabel("author").properties("name", "gender", "nationality", "country").create()

g.V().has('author','name','Julia Child').property('nationality','American')

// ADD PROPERTY KEY TO EDGE LABEL
schema.edgeLabel('created').properties('timestamp').add()
schema.edgeLabel('created').describe()

//CREATE EDGE (CONNECTION) BETWEEN TWO VERTICES
schema.propertyKey('expiration_date').Timestamp().create()
schema.vertexLabel('FridgeItem').properties('name','expiration_date','amount').add()
schema.edgeLabel('isA').connection('ingredient','FridgeItem').create()
==>schema.edgeLabel("isA").multiple().create()
schema.edgeLabel("isA").connection("ingredient", "FridgeItem").add()

***********************
* END MODIFY SCHEMA   *
***********************

***********************
* DROP DATA, SCHEMA,  *
* AND GRAPHS          *
*********************** 

// DROP ALL DATA
g.V().drop().iterate()

// DROP PARTICULAR DATA
g.V().hasLabel('author').drop()

// CLEAR ALL SCHEMA AND DATA
schema.clear()

// DROP INDEX
schema.vertexLabel('meal').describe()
schema.vertexLabel('meal').index('byMeal').remove()

// DROP GRAPH
gremlin> :remote config alias reset
gremlin> system.graphs()
==>food
gremlin> system.graph('food').drop()

***********************
* END                 *
* DROP DATA, SCHEMA,  *
* AND GRAPHS          *
*********************** 
 
***********************
* CREATE INDEX        *
***********************

// SECONDARY INDEX
schema.vertexLabel('recipe').index('byRecipe').secondary().by('name').add()

// MATERIALIZED INDEX
schema.vertexLabel('author').index('byAuthor').materialized().by('name').add()

// SEARCH INDEX
schema.vertexLabel('recipe').index('search').search().by('instructions').asText().add()
schema.vertexLabel('recipe').index('search').search().by('instructions').asString().add()

// EDGE INDEX
schema.vertexLabel('reviewer').index('ratedByStars').outE(rated).by('stars').add()

// PROPERTY INDEX
schema().vertexLabel('author').index('byLocation').property('country').by('livedIn').add()

// DISPLAYING INDEXES
schema.describe()
schema().vertexLabel('author').describe()

schema.traversal().V().hasLabel('vertexIndex').valueMap()
==>{name=[byName], type=[Secondary]}
==>{unique=[false], name=[byIngredient], type=[Materialized]}
==>{unique=[false], name=[byReviewer], type=[Materialized]}
==>{unique=[false], name=[byRecipe], type=[Materialized]}
==>{unique=[false], name=[byMeal], type=[Materialized]}

schema.traversal().V().hasLabel('vertexIndex').count()
==>5
                                        
***********************
* END CREATE INDEX    *
***********************

***********************
* USING INDEX         *
***********************

g.V().has('author', 'name', 'Emeril Lagasse').out('created').values('name')

g.V().has('recipe', 'instructions', Search.tokenRegex('Broil.*')).as('a').
  V().has('reviewer','comment', Search.regex('.*y .*').out('rated').
  where(eq('a')).values('name')
  
// EXACT MATCH
g.V().has('author', 'name', 'Julia Child')

***********************
* END USING INDEX     *
***********************

***********************
* USING TRANSACTIONS  *
***********************

// ADD DATA
tomatoPaste = graph.addVertex(label, 'ingredient', 'name', 'tomato paste')
eggplant = graph.addVertex(label, 'ingredient', 'name', 'eggplant')
zucchini = graph.addVertex(label, 'ingredient', 'name', 'zucchini')

RESULTS:
gremlin> tomatoPaste = graph.addVertex(label, 'ingredient', 'name', 'tomato paste')
==>v[{~label=ingredient, member_id=4, community_id=1500639360}]
gremlin> eggplant = graph.addVertex(label, 'ingredient', 'name', 'eggplant')
==>v[{~label=ingredient, member_id=5, community_id=1500639360}]
gremlin> zucchini = graph.addVertex(label, 'ingredient', 'name', 'zucchini')
==>v[{~label=ingredient, member_id=6, community_id=1500639360}]

// LOAD SCRIPT
gremlin> :load /tmp/test.groovy
==>null

// COMMIT TRANSACTION
gremlin> graph.tx().commit()

// RUN SCRIPT 
gremlin> :remote console
gremlin> script = """beef = graph.addVertex(label, 'ingredient', 'name', 'beef')
gremlin> onion = graph.addVertex(label, 'ingredient', 'name', 'onion')"""
==>beef = graph.addVertex(label, 'ingredient', 'name', 'beef')
onion = graph.addVertex(label, 'ingredient', 'name', 'onion')

// RUN SCRIPT
gremlin> :remote console
gremlin> script = new File('/Users/lorinapoland/CLONES/dse-5.0.0/junk.groovy').text
gremlin> :> @script
==>v[{~label=ingredient, member_id=7, community_id=1500639360}]

gremlin> :> graph.tx().commit()

**************************
* END USING TRANSACTIONS *
**************************

**************************
* CREATE GREMLIN GRAPHS  *
**************************

gremlin> system.graph('food').create()
==>null
gremlin> :remote config alias g food.g
==>g=food.g
gremlin> system.graphs()
==>food
==>test

*****************************
* END CREATE GREMLIN GRAPHS *
*****************************

**************************
* CREATE GREMLIN SCHEMA  *
**************************

All the same as Studio except for:
gremlin> :remote config timeout max
gremlin> :load /tmp/RecipeSchema.groovy
gremlin> graph.tx().commit()

*****************************
* END CREATE GREMLIN SCHEMA *
*****************************

***********************
* GREMLIN INSERT DATA *
***********************

juliaChild = graph.addVertex(label,'author', 'name','Julia Child', 'gender','F')
frenchChefCookbook = graph.addVertex(label, 'book', 'name, 'The French Chef Cookbook', 'year' , 1968, 'ISBN', '0-394-40135-2')
juliaChild.addEdge('authored', frenchChefCookbook)
beefBourguignon.addEdge('includes', beef, 'amount','2 lbs')

g.addV('author').property('name','Julia Child').property('gender','F')
g.addV('book').property('name, 'The French Chef Cookbook').property('year' , 1968).property('ISBN', '0-394-40135-2')
//add edge
g.V().has('author','name','Julia Child').as('a').V().has('book','name','The French Chef Cookbook').addE('authored').to('a')
g.V().has('recipe','name','Beef Bourguignon').as('a').
    V().has('ingredient','name','beef').
    addE('includes').from('a').property('amount','2 lbs')

***************************
* END GREMLIN INSERT DATA *
***************************

************************
* GRAPHSON INSERT DATA *
************************

// vertex id and label
{
  "id":{"~label":"author","member_id":35,"community_id":1733329920}, 
label":"author",

// vertex properties
"properties":{"gender":[{
     "id":{
       "local_id":"00000000-0000-8084-0000-000000000000",
       "~type":"gender",
       "out_vertex":{"~label":"author","member_id":35,"community_id":1733329920}},
       "value":"F"
    }],
    "name":[{
        "id":{
        "local_id":"00000000-0000-8083-0000-000000000000",
        "~type":"name",
        "out_vertex":{"~label":"author","member_id":35,"community_id":1733329920}},
        "value":"Simone Beck"}]
}}

// outgoing edges
 "outE":{
    "authored":[{
      "id":{
        "out_vertex":{"~label":"author","member_id":35,"community_id":1733329920},
        "local_id":"72086b3c-0e6c-11e6-b5e4-0febe4822aa4",
        "in_vertex":{"~label":"book","member_id":52,"community_id":1733329920},
        "~type":"authored"
      },
    "inV":{"~label":"book","member_id":52,"community_id":1733329920}}, {
      "id":{
        "out_vertex":{"~label":"author","member_id":35,"community_id":1733329920},
        "local_id":"72086b32-0e6c-11e6-b5e4-0febe4822aa4",
        "in_vertex":{"~label":"book","member_id":54,"community_id":1733329920},
        "~type":"authored"
      },
    "inV":{"~label":"book","member_id":54,"community_id":1733329920}}]},

****************************
* END GRAPHSON INSERT DATA *
****************************

************************
* GRAPHSON LOAD DATA   *
************************

graph.io(graphson()).readGraph('/tmp/recipe.json')
graph.io(graphson()).writeGraph('/tmp/recipe.json')

// lossless graphson
gremlin> f = new FileOutputStream("/tmp/recipe_lossless.json");
mapper = graph.io(graphson()).mapper().embedTypes(true).create(); 
graph.io(graphson()).writer().mapper(mapper).create().writeVertex(f,g.V().next(),BOTH) 

// sample
{
  "@class":"java.util.HashMap",
  "id":{
    "@class":"java.util.HashMap",
    "~label":"meal",
    "member_id":["java.lang.Long",25],
    "community_id":1989847424
  },
  "label":"meal",

****************************
* END GRAPHSON LOAD DATA   *
****************************

***********************
* GRAPHML INSERT DATA *
***********************

<?xml version='1.0' encoding='UTF-8'?>
<graphml xmlns="http://graphml.graphdrawing.org/xmlns" 
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
  xsi:schemaLocation="http://
  graphml.graphdrawing.org/xmlns 
  http://graphml.graphdrawing.org/xmlns/1.1/graphml.xsd">
  
<graph id="graph" edgedefault="directed">

// vertex properties
<key id="labelV" for="node" attr.name="labelV" attr.type="string"/>
<key id="id" for="node" attr.name="id" attr.type="int" />
<key id="aname" for="node" attr.name="aname" attr.type="string"/>
<key id="gender" for="node" attr.name="gender" attr.type="string"/>
<key id="recipeTitle" for="node" attr.name="recipeTitle" attr.type="string" />
<key id="instructions" for="node" attr.name="instructions" attr.type="string" />
<key id="bookTitle" for="node" attr.name="bookTitle" attr.type="string" />
<key id="publishDate" for="node" attr.name="publishDate" attr.type="int" />
<key id="ISBN" for="node" attr.name="ISBN" attr.type="string" />
<key id="iName" for="node" attr.name="iName" attr.type="string" />
<key id="mealTitle" for="node" attr.name="mealTitle" attr.type="string"/>
<key id="mCreateDate" for="node" attr.name="mCreateDate" attr.type="string"/>
<key id="calories" for="node" attr.name="calories" attr.type="int"/>
<key id="revname" for="node" attr.name="revname" attr.type="string"/>

// edge properties
<key id="labelE" for="edge" attr.name="labelE" attr.type="string"/>
<key id="stars" for="edge" attr.name="stars" attr.type="string"/>
<key id="ratedDate" for="edge" attr.name="ratedDate" attr.type="string"/>
<key id="comment" for="edge" attr.name="comment" attr.type="string"/>
<key id="amount" for="edge" attr.name="amount" attr.type="string"/>

// vertex
<node id="{~label=meal, member_id=21, community_id=1579328896}">
  <data key="labelV">meal</data>
  <data key="name">Saturday Feast</data>
  <data key="calories">1000</data>
  <data key="timestamp">2015-11-30T00:00:00Z</data>
</node>

//edge 
<edge id="{
  out_vertex={~label=meal, member_id=22, community_id=1579328896}, 
  local_id=62b12e83-0e6a-11e6-b5e4-0febe4822aa4, 
  in_vertex={~label=book, member_id=27, community_id=1579328896}, ~type=includedIn}" 
  source="{~label=meal, member_id=22, community_id=1579328896}" 
  target="{~label=book, member_id=27, community_id=1579328896}">
  <data key="labelE">includedIn</data>
</edge>

</graph></graphml>
***************************
* END GRAPHML INSERT DATA *
***************************

***********************
* GRAPHML LOAD DATA   *
***********************

graph.io(graphml()).readGraph('/tmp/recipe.gml')
graph.io(graphml()).writeGraph('/tmp/recipe.gml')

***************************
* END GRAPHML LOAD DATA   *
***************************

******************
* GRYO LOAD DATA *
******************

graph.io(gryo()).readGraph('/tmp/recipe.gryo')
graph.io(gryo()).writeGraph('/tmp/recipe.gryo')

**********************
* END GRYO LOAD DATA *
**********************

***********************
* DISCOVER PROPERTIES *
***********************

// count
g.V().count()
==>56

// valueMap
g.V().has('name','Julia Child').valueMap()
==>{gender=[F], name=[Julia Child]}

// values
g.E().hasLabel('rated').values()
==>5
==>Pretty tasty!
==>2014-01-01T00:00:00Z

// schema description
schema.describe()
==>schema.propertyKey("member_id").Smallint().single().create()
schema.propertyKey("instructions").Text().single().create()
schema.propertyKey("amount").Text().single().create()
schema.propertyKey("gender").Text().single().create()
schema.propertyKey("year").Int().single().create()
schema.propertyKey("calories").Int().single().create()
schema.propertyKey("stars").Int().single().create()
schema.propertyKey("community_id").Int().single().create()
schema.propertyKey("ISBN").Text().single().create()
schema.propertyKey("name").Text().single().create()
schema.propertyKey("comment").Text().single().create()
schema.propertyKey("timestamp").Timestamp().single().create()
schema.edgeLabel("authored").multiple().create()
schema.edgeLabel("rated").multiple().properties("timestamp", "stars", "comment").create()
schema.edgeLabel("includedIn").multiple().create()
schema.edgeLabel("created").multiple().properties("year").create()
schema.edgeLabel("includes").multiple().properties("amount").create()
schema.vertexLabel("meal").properties("name", "timestamp", "calories").create()
schema.vertexLabel("ingredient").properties("name").create()
schema.vertexLabel("author").properties("name", "gender").create()
schema.vertexLabel("book").properties("name", "year", "ISBN").create()
schema.vertexLabel("recipe").properties("name", "instructions").create()
schema.vertexLabel("reviewer").properties("name").create()
schema.edgeLabel("authored").connection("author", "book").connection("book", "author").add()
schema.edgeLabel("rated").connection("recipe", "reviewer").connection("reviewer", "recipe").add()
schema.edgeLabel("includedIn").connection("meal", "recipe").connection("meal", "book").connection("book", "recipe").connection("book", "meal").connection("recipe", "book").connection("recipe", "meal").add()
schema.edgeLabel("created").connection("author", "recipe").connection("recipe", "author").add()
schema.edgeLabel("includes").connection("ingredient", "recipe").connection("recipe", "ingredient").add()
gremlin> schema.edgeLabel('includes').describe()
==>schema.edgeLabel("includes").multiple().properties("amount").create()
schema.edgeLabel("includes").connection("ingredient", "recipe").connection("recipe", "ingredient").add()
gremlin> schema.vertexLabel('author').describe()
==>schema.vertexLabel("author").properties("name", "gender").create()

schema.traversal().V().valueMap()
==>{mode=[Development]}
==>{name=[author]}
==>{name=[recipe]}
==>{name=[ingredient]}
==>{name=[book]}
==>{name=[meal]}
==>{name=[reviewer]}
==>{name=[byName], type=[Secondary]}
==>{name=[includedIn], directionality=[Bidirectional], cardinality=[Multiple]}
==>{name=[fridgeItem_single]}
==>{name=[rated], directionality=[Bidirectional], cardinality=[Multiple]}
==>{name=[fridgeItem_multiple]}
==>{dataType=[Timestamp], name=[timestamp], cardinality=[Single]}
==>{dataType=[Text], name=[ISBN], cardinality=[Single]}
==>{dataType=[Text], name=[category], cardinality=[Single]}
==>{name=[byLocation]}
==>{dataType=[Int], name=[year], cardinality=[Single]}
==>{name=[ratedByStars], directionality=[OUT]}
==>{dataType=[Text], name=[gender], cardinality=[Single]}
==>{unique=[false], name=[byIngredient], type=[Materialized]}
==>{dataType=[Text], name=[instructions], cardinality=[Single]}
==>{unique=[false], name=[byReviewer], type=[Materialized]}
==>{unique=[false], name=[byRecipe], type=[Materialized]}
==>{unique=[false], name=[byMeal], type=[Materialized]}
==>{dataType=[Int], name=[stars], cardinality=[Single]}
==>{dataType=[Text], name=[comment], cardinality=[Single]}
==>{dataType=[Int], name=[calories], cardinality=[Single]}
==>{dataType=[Text], name=[blah], cardinality=[Single]}
==>{dataType=[Text], name=[amount], cardinality=[Single]}
==>{name=[created], directionality=[Bidirectional], cardinality=[Multiple]}
==>{name=[includes], directionality=[Bidirectional], cardinality=[Multiple]}
==>{dataType=[Bigint], name=[member_id], cardinality=[Single]}
==>{name=[authored], directionality=[Bidirectional], cardinality=[Multiple]}
==>{dataType=[Text], name=[country], cardinality=[Multiple]}
==>{dataType=[Text], name=[item_mult], cardinality=[Multiple]}
==>{dataType=[Int], name=[community_id], cardinality=[Single]}
==>{dataType=[Text], name=[livedIn], cardinality=[Single]}
==>{dataType=[Text], name=[item_single], cardinality=[Single]}
==>{dataType=[Text], name=[name], cardinality=[Single]}

graph.schema().traversal().V().valueMap(true)
==>{mode=[Development], label=schema, id=0}
==>{name=[recipe], label=vertexLabel, id=22}
==>{name=[ingredient], label=vertexLabel, id=23}
==>{name=[book], label=vertexLabel, id=24}
==>{name=[meal], label=vertexLabel, id=25}
==>{label=incident, id=131}
==>{name=[reviewer], label=vertexLabel, id=26}
==>{name=[byName], label=vertexIndex, type=[Secondary], id=32919}
==>{name=[includedIn], directionality=[Bidirectional], label=edgeLabel, id=32917, cardinality=[Multiple]}
==>{name=[fridgeItem_single], label=vertexLabel, id=28}
==>{name=[rated], directionality=[Bidirectional], label=edgeLabel, id=32918, cardinality=[Multiple]}
==>{name=[fridgeItem_multiple], label=vertexLabel, id=29}
==>{label=incident, id=149}
==>{dataType=[Timestamp], name=[timestamp], label=propertyKey, id=32904, cardinality=[Single]}
==>{dataType=[Text], name=[ISBN], label=propertyKey, id=32905, cardinality=[Single]}
==>{label=incident, id=153}
==>{dataType=[Text], name=[category], label=propertyKey, id=32902, cardinality=[Single]}
==>{name=[byLocation], label=propertyIndex, id=32924}
==>{dataType=[Int], name=[year], label=propertyKey, id=32903, cardinality=[Single]}
==>{name=[ratedByStars], directionality=[OUT], label=edgeIndex, id=32925}
==>{dataType=[Text], name=[gender], label=propertyKey, id=32900, cardinality=[Single]}
==>{unique=[false], name=[byIngredient], label=vertexIndex, type=[Materialized], id=32922}
==>{dataType=[Text], name=[instructions], label=propertyKey, id=32901, cardinality=[Single]}
==>{label=incident, id=157}
==>{unique=[false], name=[byReviewer], label=vertexIndex, type=[Materialized], id=32923}
==>{unique=[false], name=[byRecipe], label=vertexIndex, type=[Materialized], id=32920}
==>{unique=[false], name=[byMeal], label=vertexIndex, type=[Materialized], id=32921}
==>{label=incident, id=96}
==>{dataType=[Int], name=[stars], label=propertyKey, id=32908, cardinality=[Single]}
==>{dataType=[Text], name=[comment], label=propertyKey, id=32909, cardinality=[Single]}
==>{dataType=[Int], name=[calories], label=propertyKey, id=32906, cardinality=[Single]}
==>{dataType=[Text], name=[blah], label=propertyKey, id=32929, cardinality=[Single]}
==>{dataType=[Text], name=[amount], label=propertyKey, id=32907, cardinality=[Single]}
==>{label=incident, id=174}
==>{name=[created], directionality=[Bidirectional], label=edgeLabel, id=32915, cardinality=[Multiple]}
==>{name=[includes], directionality=[Bidirectional], label=edgeLabel, id=32916, cardinality=[Multiple]}
==>{dataType=[Bigint], name=[member_id], label=propertyKey, id=32913, cardinality=[Single]}
==>{name=[authored], directionality=[Bidirectional], label=edgeLabel, id=32914, cardinality=[Multiple]}
==>{dataType=[Text], name=[country], label=propertyKey, id=32911, cardinality=[Multiple]}
==>{dataType=[Text], name=[item_mult], label=propertyKey, id=32933, cardinality=[Multiple]}
==>{dataType=[Int], name=[community_id], label=propertyKey, id=32912, cardinality=[Single]}
==>{dataType=[Text], name=[livedIn], label=propertyKey, id=32910, cardinality=[Single]}
==>{dataType=[Text], name=[item_single], label=propertyKey, id=32932, cardinality=[Single]}
==>{dataType=[Text], name=[name], label=propertyKey, id=32899, cardinality=[Single]}
==>{name=[author], label=vertexLabel, id=21}
==>{label=incident, id=127}

schema.traversal()
==>graphtraversalsource[tinkergraph[vertices:58 edges:106], standard]

// get just the vertex labels
schema.describe().split('\n').grep(~/.*vertexLabel.*/)
==>schema.vertexLabel("meal").properties("name", "timestamp", "calories").create()
==>schema.vertexLabel("ingredient").properties("name").create()
==>schema.vertexLabel("ingredient").index("byIngredient").materialized().by("name").add()
==>schema.vertexLabel("test").partitionKey("tester").clusteringKey("foor").create()
==>schema.vertexLabel("FridgeSensor").create()
==>schema.vertexLabel("author").properties("name", "gender", "nationality").create()
==>schema.vertexLabel("author").index("byName").secondary().by("name").add()
==>schema.vertexLabel("author").index("byAuthor").materialized().by("name").add()
==>schema.vertexLabel("FridgeItem").properties("name", "expiration_date", "amount").create()
==>schema.vertexLabel("book").properties("name", "year", "ISBN").create()
==>schema.vertexLabel("recipe").properties("name", "instructions").create()
==>schema.vertexLabel("recipe").index("byRecipe").materialized().by("name").add()
==>schema.vertexLabel("reviewer").properties("name").create()
==>schema.vertexLabel("reviewer").index("byReviewer").materialized().by("name").add()
==>schema.vertexLabel("reviewer").index("ratedByStars").outE("rated").by("stars").add()

// get graph name
graph
==>dsegraphimpl[testqs]

or 

graph.name()
==>testqs

***************************
* END DISCOVER PROPERTIES *
***************************

***********
* ANATOMY *
***********

g.V().hasLabel('recipe').count()

gremlin>  g.V()
==>v[{~label=ingredient, member_id=18, community_id=1989847424}]
==>v[{~label=ingredient, member_id=19, community_id=1989847424}]
==>v[{~label=ingredient, member_id=16, community_id=1989847424}]
==>v[{~label=ingredient, member_id=17, community_id=1989847424}]
==>v[{~label=ingredient, member_id=22, community_id=1989847424}]
==>v[{~label=ingredient, member_id=13, community_id=1989847424}]
==>v[{~label=meal, member_id=25, community_id=1989847424}]
==>v[{~label=ingredient, member_id=24, community_id=1989847424}]
==>v[{~label=recipe, member_id=14, community_id=1878171264}]
==>v[{~label=recipe, member_id=21, community_id=1878171264}]
==>v[{~label=recipe, member_id=19, community_id=1878171264}]
==>v[{~label=meal, member_id=27, community_id=1989847424}]
==>v[{~label=recipe, member_id=20, community_id=1878171264}]
==>v[{~label=meal, member_id=26, community_id=1989847424}]
==>v[{~label=book, member_id=13, community_id=1878171264}]
==>v[{~label=book, member_id=10, community_id=1878171264}]
==>v[{~label=book, member_id=11, community_id=1878171264}]
==>v[{~label=author, member_id=1, community_id=1878171264}]
==>v[{~label=author, member_id=0, community_id=1878171264}]
==>v[{~label=author, member_id=3, community_id=1878171264}]
==>v[{~label=ingredient, member_id=2, community_id=1989847424}]
==>v[{~label=author, member_id=2, community_id=1878171264}]

gremlin>  g.V().hasLabel('recipe')
==>v[{~label=recipe, member_id=14, community_id=1878171264}]
==>v[{~label=recipe, member_id=21, community_id=1878171264}]
==>v[{~label=recipe, member_id=19, community_id=1878171264}]
==>v[{~label=recipe, member_id=20, community_id=1878171264}]
==>v[{~label=recipe, member_id=17, community_id=1878171264}]
==>v[{~label=recipe, member_id=18, community_id=1878171264}]
==>v[{~label=recipe, member_id=15, community_id=1878171264}]
==>v[{~label=recipe, member_id=16, community_id=1878171264}]

gremlin> g.V().hasLabel('recipe').count()
==>8

g.V().hasLabel('recipe').has('name', 'Beef Bourguignon').inE().values('comment')
gremlin>  g.V().hasLabel('recipe').has('name', 'Beef Bourguignon')
==>v[{~label=recipe, member_id=14, community_id=1878171264}]

gremlin>  g.V().hasLabel('recipe').has('name', 'Beef Bourguignon').inE()
==>e[{out_vertex={~label=reviewer, member_id=4, community_id=857859584}, 
local_id=ca461ec0-0e7e-11e6-b5e4-0febe4822aa4, 
in_vertex={~label=recipe, member_id=14, community_id=1878171264}, 
~type=rated}]
[{~label=reviewer, member_id=4, community_id=857859584}-rated->{~label=recipe, member_id=14, community_id=1878171264}]
==>e[{out_vertex={~label=reviewer, member_id=5, community_id=857859584}, 
local_id=ca5bf0b0-0e7e-11e6-b5e4-0febe4822aa4, 
in_vertex={~label=recipe, member_id=14, community_id=1878171264}, 
~type=rated}]
[{~label=reviewer, member_id=5, community_id=857859584}-rated->{~label=recipe, member_id=14, community_id=1878171264}]
==>e[{out_vertex={~label=reviewer, member_id=6, community_id=857859584}, 
local_id=ca72d410-0e7e-11e6-b5e4-0febe4822aa4, 
in_vertex={~label=recipe, member_id=14, community_id=1878171264}, 
~type=rated}]
[{~label=reviewer, member_id=6, community_id=857859584}-rated->{~label=recipe, member_id=14, community_id=1878171264}]
==>e[{out_vertex={~label=reviewer, member_id=7, community_id=857859584}, 
local_id=ca8a0590-0e7e-11e6-b5e4-0febe4822aa4, 
in_vertex={~label=recipe, member_id=14, community_id=1878171264}, 
~type=rated}]
[{~label=reviewer, member_id=7, community_id=857859584}-rated->{~label=recipe, member_id=14, community_id=1878171264}]
==>e[{out_vertex={~label=author, member_id=0, community_id=1878171264}, 
local_id=524504c0-0e7b-11e6-b5e4-0febe4822aa4, 
in_vertex={~label=recipe, member_id=14, community_id=1878171264}, 
~type=created}]
[{~label=author, member_id=0, community_id=1878171264}-created->{~label=recipe, member_id=14, community_id=1878171264}]

gremlin>  g.V().hasLabel('recipe').has('name', 'Beef Bourguignon').inE('rated').values('comment')
==>Yummy!
==>Pretty tasty!
==>It was okay.

gremlin>  g.V().hasLabel('ingredient').has('name',within('beef','carrots')).in().as('Recipe').
  out().hasLabel('book').as('Book').
  select('Book','Recipe').by('name').
  by('name')
==>[Book:The Art of French Cooking, Vol. 1, Recipe:Beef Bourguignon]
==>[Book:The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution, Recipe:Carrot Soup]

gremlin>  g.V().hasLabel('ingredient').has('name',within('beef','carrots')).
  in().as('Recipe').
  out().hasLabel('book').as('Book').
  select('Book','Recipe').
  by('name').by('name').path()
==>[v[{~label=ingredient, member_id=22, community_id=1878171264}], 
v[{~label=recipe, member_id=14, community_id=1878171264}], 
v[{~label=book, member_id=10, community_id=1878171264}], 
{Book=The Art of French Cooking, Vol. 1, Recipe=Beef Bourguignon}]
==>[v[{~label=ingredient, member_id=21, community_id=1989847424}], 
v[{~label=recipe, member_id=20, community_id=1878171264}], 
v[{~label=book, member_id=13, community_id=1878171264}], 
{Book=The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution, Recipe=Carrot Soup}]

gremlin>  g.V().hasLabel('recipe').has('name', 'Beef Bourguignon').inE('rated').values('comment').profile()
==>Traversal Metrics
Step                                                               Count  Traversers       Time (ms)    % Dur
=============================================================================================================
DsegGraphStep([~label.eq(recipe), name.eq(Beef ...                     1           1           0.979    73.00
  query-optimizer                                                                              0.184
  retrieve-new                                                                                 0.115
  iterator-setup                                                                               0.390
DsegVertexStep(IN,[rated],edge)                                        4           4           0.286    21.37
  query-optimizer                                                                              0.080
  retrieve-new                                                                                 0.014
  iterator-setup                                                                               0.062
DsegPropertiesStep([comment],value)                                    3           3           0.075     5.63
                                            >TOTAL                     -           -           1.342        -
                                            
***************
* END ANATOMY *
***************

**************************
* USING CUSTOM VERTEX ID *
**************************

g.V().hasId(['~label':'FridgeSensor','city_id':100,'sensor_id':'60bcae02-f6e5-11e5-9ce9-5e5517507c66']).valueMap()
g.V(['~label':'FridgeSensor','city_id':100,'sensor_id':'60bcae02-f6e5-11e5-9ce9-5e5517507c66']).valueMap()

******************************
* END USING CUSTOM VERTEX ID *
******************************

***************************
* USING SIMPLE TRAVERSALS *
***************************

*******************************
* END USING SIMPLE TRAVERSALS *
*******************************

******************************
* USING BRANCHING TRAVERSALS *
******************************
g.V().choose(label()).
  option('author', out('created').count()).
  option('reviewer', out('rated').count())
  
**********************************
* END USING BRANCHING TRAVERSALS *
**********************************