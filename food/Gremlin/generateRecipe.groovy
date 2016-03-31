// Generates all Recipe Toy Graph vertices and edges except Reviews

// Script can be used only in Gremlin Console
// For DataStax Studio, copy and paste entire script into a cell and run
// script = new File('/Users/lorinapoland/CLONES/graph-examples/food/Gremlin/generateRecipe.groovy').text; []
// :> @script

// Add all vertices and edges for Recipe
g.V().drop().iterate()

// author vertices
// already created in the tutorial
Vertex juliaChild = graph.addVertex(label, 'author', 'name','Julia Child', 'gender', 'F')
Vertex simoneBeck = graph.addVertex(label, 'author', 'name', 'Simone Beck', 'gender', 'F')
Vertex louisetteBertholie = graph.addVertex(label, 'author', 'name', 'Louisette Bertholie', 'gender', 'F')
Vertex patriciaSimon = graph.addVertex(label, 'author', 'name', 'Patricia Simon', 'gender', 'F')
Vertex aliceWaters = graph.addVertex(label, 'author', 'name', 'Alice Waters', 'gender', 'F')
Vertex patriciaCurtan = graph.addVertex(label, 'author', 'name', 'Patricia Curtan', 'gender', 'F')
Vertex kelsieKerr = graph.addVertex(label, 'author', 'name', 'Kelsie Kerr', 'gender', 'F')
Vertex fritzStreiff = graph.addVertex(label, 'author', 'name', 'Fritz Streiff', 'gender', 'M')
Vertex emerilLagasse = graph.addVertex(label, 'author', 'name', 'Emeril Lagasse', 'gender', 'M')
Vertex jamesBeard = graph.addVertex(label, 'author', 'name', 'James Beard', 'gender', 'M')

// book vertices
// already created in the tutorial
Vertex artOfFrenchCookingVolOne = graph.addVertex(label, 'book', 'name', 'The Art of French Cooking, Vol. 1', 'year', 1961)
Vertex simcasCuisine = graph.addVertex(label, 'book', 'name', "Simca's Cuisine: 100 Classic French Recipes for Every Occasion", 'year', 1972, 'ISBN', '0-394-40152-2')
Vertex frenchChefCookbook = graph.addVertex(label, 'book', 'name','The French Chef Cookbook', 'year', 1968, 'ISBN', '0-394-40135-2')
Vertex artOfSimpleFood = graph.addVertex(label, 'book', 'name', 'The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution', 'year', 2007, 'ISBN', '0-307-33679-4')

// recipe vertices
Vertex beefBourguignon = graph.addVertex(label, 'recipe', 'name', 'Beef Bourguignon', 'instructions', 'Braise the beef. Saute the onions and carrots. Add wine and cook in a dutch oven at 425 degrees for 1 hour.')
Vertex ratatouille = graph.addVertex(label, 'recipe', 'name', 'Rataouille', 'instructions', 'Peel and cut the egglant. Make sure you cut eggplant into lengthwise slices that are about 1-inch wmyIde, 3-inches long, and 3/8-inch thick')
Vertex saladeNicoise = graph.addVertex(label, 'recipe', 'name', 'Salade Nicoise', 'instructions', 'Take a salad bowl or platter and line it with lettuce leaves, shortly before serving. Drizzle some olive oil on the leaves and dust them with salt.')
Vertex wildMushroomStroganoff = graph.addVertex(label, 'recipe', 'name', 'Wild Mushroom Stroganoff', 'instructions', 'Cook the egg noodles according to the package directions and keep warm. Heat 1 1/2 tablespoons of the oliveoil in a large saute pan over medium-high heat.')
Vertex spicyMeatloaf = graph.addVertex(label, 'recipe', 'name', 'Spicy Meatloaf', 'instructions', 'Preheat the oven to 375 degrees F. Cook bacon in a large skillet over medium heat until very crisp and fat has rendered, 8-10 minutes.')
Vertex oystersRockefeller = graph.addVertex(label, 'recipe', 'name', 'Oysters Rockefeller', 'instructions', 'Saute the shallots, celery, herbs, and seasonings in 3 tablespoons of the butter for 3 minutes. Add the watercress and let it wilt.')
Vertex carrotSoup = graph.addVertex(label, 'recipe', 'name', 'Carrot Soup', 'instructions', 'In a heavy-bottomed pot, melt the butter. When it starts to foam, add the onions and thyme and cook over medium-low heat until tender, about 10 minutes.')
Vertex roastPorkLoin = graph.addVertex(label, 'recipe', 'name', 'Roast Pork Loin', 'instructions', 'The day before, separate the meat from the ribs, stopping about 1 inch before the end of the bones. Season the pork liberally insmyIde and out with salt and pepper and refrigerate overnight.')

// ingredients vertices
Vertex beef = graph.addVertex(label, 'ingredient', 'name', 'beef')
Vertex onion = graph.addVertex(label, 'ingredient', 'name', 'onion')
Vertex mashedGarlic = graph.addVertex(label, 'ingredient', 'name', 'mashed garlic')
Vertex butter = graph.addVertex(label, 'ingredient', 'name', 'butter')
Vertex tomatoPaste = graph.addVertex(label, 'ingredient', 'name', 'tomato paste')
Vertex eggplant = graph.addVertex(label, 'ingredient', 'name', 'eggplant')
Vertex zucchini = graph.addVertex(label, 'ingredient', 'name', 'zucchini')
Vertex oliveOil = graph.addVertex(label, 'ingredient', 'name', 'olive oil')
Vertex yellowOnion = graph.addVertex(label, 'ingredient', 'name', 'yellow onion')
Vertex greenBean = graph.addVertex(label, 'ingredient', 'name', 'green beans')
Vertex tuna = graph.addVertex(label, 'ingredient', 'name', 'tuna')
Vertex tomato = graph.addVertex(label, 'ingredient', 'name', 'tomato')
Vertex hardBoiledEgg = graph.addVertex(label, 'ingredient', 'name', 'hard-boiled egg')
Vertex eggNoodles = graph.addVertex(label, 'ingredient', 'name', 'egg noodles')
Vertex mushroom = graph.addVertex(label, 'ingredient', 'name', 'mushrooms')
Vertex bacon = graph.addVertex(label, 'ingredient', 'name', 'bacon')
Vertex celery = graph.addVertex(label, 'ingredient', 'name', 'celery')
Vertex greenBellPepper = graph.addVertex(label, 'ingredient', 'name', 'green bell pepper')
Vertex groundBeef = graph.addVertex(label, 'ingredient', 'name', 'ground beef')
Vertex porkSausage = graph.addVertex(label, 'ingredient', 'name', 'pork sausage')
Vertex shallot = graph.addVertex(label, 'ingredient', 'name', 'shallots')
Vertex chervil = graph.addVertex(label, 'ingredient', 'name', 'chervil')
Vertex fennel = graph.addVertex(label, 'ingredient', 'name', 'fennel')
Vertex parsley = graph.addVertex(label, 'ingredient', 'name', 'parsley')
Vertex oyster = graph.addVertex(label, 'ingredient', 'name', 'oyster')
Vertex pernod = graph.addVertex(label, 'ingredient', 'name', 'Pernod')
Vertex thyme = graph.addVertex(label, 'ingredient', 'name', 'thyme')
Vertex carrot = graph.addVertex(label, 'ingredient', 'name', 'carrots')
Vertex chickenBroth = graph.addVertex(label, 'ingredient', 'name', 'chicken broth')
Vertex porkLoin = graph.addVertex(label, 'ingredient', 'name', 'pork loin')
Vertex redWine = graph.addVertex(label, 'ingredient', 'name', 'red wine')

// meal vertices
Vertex SaturdayFeast = graph.addVertex(label, 'meal', 'name', 'Saturday Feast', 'timestamp', Instant.parse('2015-11-30T00:00:00.00Z'), 'calories', 1000)
Vertex EverydayDinner = graph.addVertex(label, 'meal', 'name', 'EverydayDinner', 'timestamp', Instant.parse('2016-01-14T00:00:00.00Z'), 'calories', 600)
Vertex JuliaDinner = graph.addVertex(label, 'meal', 'name', 'JuliaDinner', 'timestamp', Instant.parse('2016-01-14T00:00:00.00Z'), 'calories', 900)

// author-book edges
juliaChild.addEdge('authored', artOfFrenchCookingVolOne)
simoneBeck.addEdge('authored', artOfFrenchCookingVolOne)
louisetteBertholie.addEdge('authored', artOfFrenchCookingVolOne)
simoneBeck.addEdge('authored',simcasCuisine)
patriciaSimon.addEdge('authored',simcasCuisine)
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
