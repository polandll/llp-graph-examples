// Generates all Recipe Toy Graph vertices and edges except Reviews

// Drop previously created vertices and edges, so that I can add them again
g.V().drop().iterate()
// Add all vertices and edges for Recipe

// author vertices
// already created in the tutorial
Vertex juliaChild = graph.addVertex(label, 'author', 'myId', 1, 'aname','Julia Child', 'gender', 'F')
Vertex simoneBeck = graph.addVertex(label, 'author', 'myId', 2, 'aname', 'Simone Beck', 'gender', 'F')
Vertex louisetteBertholie = graph.addVertex(label, 'author', 'myId', 3, 'aname', 'Louisette Bertholie', 'gender', 'F')
Vertex patriciaSimon = graph.addVertex(label, 'author', 'myId', 4, 'aname', 'Patricia Simon', 'gender', 'F')
Vertex aliceWaters = graph.addVertex(label, 'author', 'myId', 5, 'aname', 'Alice Waters', 'gender', 'F')
Vertex patriciaCurtan = graph.addVertex(label, 'author', 'myId', 6, 'aname', 'Patricia Curtan', 'gender', 'F')
Vertex kelsieKerr = graph.addVertex(label, 'author', 'myId', 7, 'aname', 'Kelsie Kerr', 'gender', 'F')
Vertex fritzStreiff = graph.addVertex(label, 'author', 'myId', 8, 'aname', 'Fritz Streiff', 'gender', 'M')
Vertex emerilLagasse = graph.addVertex(label, 'author', 'myId', 9, 'aname', 'Emeril Lagasse', 'gender', 'M')
Vertex jamesBeard = graph.addVertex(label, 'author', 'myId', 10, 'aname', 'James Beard', 'gender', 'M')

// book vertices
// already created in the tutorial
Vertex artOfFrenchCookingVolOne = graph.addVertex(label, 'book', 'myId', 100, 'bookTitle', 'The Art of French Cooking, Vol. 1', 'publishDate', 1961)
Vertex simcasCuisine = graph.addVertex(label, 'book', 'myId', 101,'bookTitle', "Simca's Cuisine: 100 Classic French Recipes for Every Occasion", 'publishDate', 1972, 'ISBN', '0-394-40152-2')
Vertex frenchChefCookbook = graph.addVertex(label, 'book', 'myId', 102, 'bookTitle','The French Chef Cookbook', 'publishDate',1968, 'ISBN', '0-394-40135-2')
Vertex artOfSimpleFood = graph.addVertex(label, 'book', 'myId', 103, 'bookTitle', 'The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution', 'publishDate', 2007, 'ISBN', '0-307-33679-4')

// recipe vertices
Vertex beefBourguignon = graph.addVertex('myId', 200, label, 'recipe', 'recipeTitle', 'Beef Bourguignon', 'instructions', 'Braise the beef. Saute the onions and carrots. Add wine and cook in a dutch oven at 425 degrees for 1 hour.')
Vertex ratatouille = graph.addVertex('myId', 201, label, 'recipe', 'recipeTitle', 'Rataouille', 'instructions', 'Peel and cut the egglant. Make sure you cut eggplant into lengthwise slices that are about 1-inch wmyIde, 3-inches long, and 3/8-inch thick')
Vertex saladeNicoise = graph.addVertex('myId', 202, label, 'recipe', 'recipeTitle', 'Salade Nicoise', 'instructions', 'Take a salad bowl or platter and line it with lettuce leaves, shortly before serving. Drizzle some olive oil on the leaves and dust them with salt.')
Vertex wildMushroomStroganoff = graph.addVertex('myId', 203, label, 'recipe', 'recipeTitle', 'Wild Mushroom Stroganoff', 'instructions', 'Cook the egg noodles according to the package directions and keep warm. Heat 1 1/2 tablespoons of the oliveoil in a large saute pan over medium-high heat.')
Vertex spicyMeatloaf = graph.addVertex('myId', 204, label, 'recipe', 'recipeTitle', 'Spicy Meatloaf', 'instructions', 'Preheat the oven to 375 degrees F. Cook bacon in a large skillet over medium heat until very crisp and fat has rendered, 8-10 minutes.')
Vertex oystersRockefeller = graph.addVertex('myId', 205, label, 'recipe', 'recipeTitle', 'Oysters Rockefeller', 'instructions', 'Saute the shallots, celery, herbs, and seasonings in 3 tablespoons of the butter for 3 minutes. Add the watercress and let it wilt.')
Vertex carrotSoup = graph.addVertex('myId', 206, label, 'recipe', 'recipeTitle', 'Carrot Soup', 'instructions', 'In a heavy-bottomed pot, melt the butter. When it starts to foam, add the onions and thyme and cook over medium-low heat until tender, about 10 minutes.')
Vertex roastPorkLoin = graph.addVertex('myId', 207, label, 'recipe', 'recipeTitle', 'Roast Pork Loin', 'instructions', 'The day before, separate the meat from the ribs, stopping about 1 inch before the end of the bones. Season the pork liberally insmyIde and out with salt and pepper and refrigerate overnight.')

// ingredients vertices
Vertex beef = graph.addVertex('myId', 300, label, 'ingredient', 'iName', 'beef')
Vertex onion = graph.addVertex('myId', 301, label, 'ingredient', 'iName', 'onion')
Vertex mashedGarlic = graph.addVertex('myId', 302, label, 'ingredient', 'iName', 'mashed garlic')
Vertex butter = graph.addVertex('myId', 303, label, 'ingredient', 'iName', 'butter')
Vertex tomatoPaste = graph.addVertex('myId', 304, label, 'ingredient', 'iName', 'tomato paste')
Vertex eggplant = graph.addVertex('myId', 305, label, 'ingredient', 'iName', 'eggplant')
Vertex zucchini = graph.addVertex('myId', 306, label, 'ingredient', 'iName', 'zucchini')
Vertex oliveOil = graph.addVertex('myId', 307, label, 'ingredient', 'iName', 'olive oil')
Vertex yellowOnion = graph.addVertex('myId', 308, label, 'ingredient', 'iName', 'yellow onion')
Vertex greenBean = graph.addVertex('myId', 309, label, 'ingredient', 'iName', 'green beans')
Vertex tuna = graph.addVertex('myId', 310, label, 'ingredient', 'iName', 'tuna')
Vertex tomato = graph.addVertex('myId', 311, label, 'ingredient', 'iName', 'tomato')
Vertex hardBoiledEgg = graph.addVertex('myId', 312, label, 'ingredient', 'iName', 'hard-boiled egg')
Vertex eggNoodles = graph.addVertex('myId', 313, label, 'ingredient', 'iName', 'egg noodles')
Vertex mushroom = graph.addVertex('myId', 314, label, 'ingredient', 'iName', 'mushrooms')
Vertex bacon = graph.addVertex('myId', 315, label, 'ingredient', 'iName', 'bacon')
Vertex celery = graph.addVertex('myId', 316, label, 'ingredient', 'iName', 'celery')
Vertex greenBellPepper = graph.addVertex('myId', 317, label, 'ingredient', 'iName', 'green bell pepper')
Vertex groundBeef = graph.addVertex('myId', 318, label, 'ingredient', 'iName', 'ground beef')
Vertex porkSausage = graph.addVertex('myId', 319, label, 'ingredient', 'iName', 'pork sausage')
Vertex shallot = graph.addVertex('myId', 320, label, 'ingredient', 'iName', 'shallots')
Vertex chervil = graph.addVertex('myId', 321, label, 'ingredient', 'iName', 'chervil')
Vertex fennel = graph.addVertex('myId', 322, label, 'ingredient', 'iName', 'fennel')
Vertex parsley = graph.addVertex('myId', 323, label, 'ingredient', 'iName', 'parsley')
Vertex oyster = graph.addVertex('myId', 324, label, 'ingredient', 'iName', 'oyster')
Vertex pernod = graph.addVertex('myId', 325, label, 'ingredient', 'iName', 'Pernod')
Vertex thyme = graph.addVertex('myId', 326, label, 'ingredient', 'iName', 'thyme')
Vertex carrot = graph.addVertex('myId', 327, label, 'ingredient', 'iName', 'carrots')
Vertex chickenBroth = graph.addVertex('myId', 328, label, 'ingredient', 'iName', 'chicken broth')
Vertex porkLoin = graph.addVertex('myId', 329, label, 'ingredient', 'iName', 'pork loin')
Vertex redWine = graph.addVertex('myId', 330, label, 'ingredient', 'iName', 'red wine')

// author-book edges
// already created in the tutorial
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
juliaChild.addEdge('created', beefBourguignon, 'rCreateDate', 1961)
juliaChild.addEdge('created', ratatouille, 'rCreateDate', 1965)
juliaChild.addEdge('created', saladeNicoise, 'rCreateDate', 1962)
emerilLagasse.addEdge('created', wildMushroomStroganoff, 'rCreateDate', 2003)
emerilLagasse.addEdge('created', spicyMeatloaf, 'rCreateDate', 2000)
aliceWaters.addEdge('created', carrotSoup, 'rCreateDate', 1995)
aliceWaters.addEdge('created', roastPorkLoin, 'rCreateDate', 1996)
jamesBeard.addEdge('created', oystersRockefeller, 'rCreateDate', 1970)

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

// meal vertices

Vertex SaturdayFeast = graph.addVertex(label, 'meal', 'myId', 500, 'mealTitle', 'Saturday Feast', 'mCreateDate', '2015-11-30', 'calories', 1000)
Vertex EverydayDinner = graph.addVertex(label, 'meal', 'myId', 501, 'mealTitle', 'EverydayDinner', 'mCreateDate', '2016-01-14', 'calories', 600)
Vertex JuliaDinner = graph.addVertex(label, 'meal', 'myId', 502, 'mealTitle', 'JuliaDinner', 'mCreateDate', '2016-01-14', 'calories', 900)

// meal - recipe edges

SaturdayFeast.addEdge('includedIn', beefBourguignon)
SaturdayFeast.addEdge('includedIn', carrotSoup)
SaturdayFeast.addEdge('includedIn', oystersRockefeller)
EverydayDinner.addEdge('includedIn', carrotSoup)
EverydayDinner.addEdge('includedIn', roastPorkLoin)
JuliaDinner.addEdge('includedIn', beefBourguignon)
JuliaDinner.addEdge('includedIn', saladeNicoise)

// meal - book edges

artOfSimpleFood.addEdge('includedIn', EverydayDinner)
simcasCuisine.addEdge('includedIn', SaturdayFeast)
artOfFrenchCookingVolOne.addEdge('includedIn', JuliaDinner)