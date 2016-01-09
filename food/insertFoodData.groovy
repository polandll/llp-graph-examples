
// author vertices
juliaChild = graph.addVertex(id, 1, label, 'author', 'aname', 'Julia Child', 'gender', 'F')
simoneBeck = graph.addVertex(id, 2, label, 'author', 'aname', 'Simone Beck', 'gender', 'F')
louisetteBertholie = graph.addVertex(id, 3, label, 'author', 'aname', 'Louisette Bertholie', 'gender', 'F')
patriciaSimon = graph.addVertex(id, 4, label, 'author', 'aname', 'Patricia Simon', 'gender', 'F')
aliceWaters = graph.addVertex(id, 5, label, 'author', 'aname', 'Alice Waters', 'gender', 'F')
patriciaCurtan = graph.addVertex(id, 6, label, 'author', 'aname', 'Patricia Curtan', 'gender', 'F')
kelsieKerr = graph.addVertex(id, 7, label, 'author', 'aname', 'Kelsie Kerr', 'gender', 'F')
fritzStreiff = graph.addVertex(id, 8, label, 'author', 'aname', 'Fritz Streiff', 'gender', 'M')
emerilLagasse = graph.addVertex(id, 9, label, 'author', 'aname', 'Emeril Lagasse', 'gender', 'M')
jamesBeard = graph.addVertex(id, 10, label, 'author', 'aname', 'James Beard', 'gender', 'M')

// book vertices
artOfFrenchCookingVolOne = graph.addVertex(id, 100, label, 'book', \
'bookTitle', 'The Art of French Cooking, Vol. 1', 'publishDate', 1961)
simcasCuisine = graph.addVertex(id, 101, label, 'book', \
'bookTitle', 'Simca's Cuisine: 100 Classic French Recipes for Every Occasion', 'publishDate',1972, \
'IBSN', '0-394-40152-2')
frenchChefCookbook = graph.addVertex(id, 102, label, 'book', 'bookTitle','The French Chef Cookbook', \
'publishDate','1968, 'ISBN', '0-394-40135-2')
artOfSimpleFood = graph.addVertex(id, 103, label, 'book', \
'bookTitle', 'The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution', \
'publishDate', 2007, 'ISBN', '0-307-33679-4')

// recipe vertices
beefBourguignon = graph.addVertex(id, 200, label, 'recipe', 'recipeTitle', 'Beef Bourguignon', \
'instructions', 'Braise the beef. Saute the onions and carrots. Add wine and cook in a dutch oven at 425 degrees for 1 hour.')
ratatouille = graph.addVertex(id, 201, label, 'recipe', 'recipeTitle', 'Rataouille', \
'instructions', 'Peel and cut the egglant. Make sure you cut eggplant into lengthwise slices that are about 1-inch wide, 3-inches long, and 3/8-inch thick')
saladeNicoise = graph.addVertex(id, 202, label, 'recipe', 'recipeTitle', 'Salade Nicoise', \
'instructions', 'Take a salad bowl or platter and line it with lettuce leaves, shortly before serving. Drizzle some olive oil on the leaves and dust them with salt.')
wildMushroomStroganoff = graph.addVertex(id, 203, label, 'recipe', 'recipeTitle', 'Wild MushroomStroganoff', \
'instructions', 'Cook the egg noodles according to the package directions and keep warm. Heat 1 1/2 tablespoons of the oliveoil in a large saute pan over medium-high heat.')
spicyMeatloaf = graph.addVertex(id, 204, label, 'recipe', 'recipeTitle', 'Spicy Meatloaf', \
'instructions', 'Preheat the oven to 375 degrees F. Cook bacon in a large skillet over medium heat until very crisp and fat has rendered, 8-10 minutes.')
oystersRockefeller = graph.addVertex(id, 205, label, 'recipe', 'recipeTitle', 'Oysters Rockefeller', \
'instructions', 'Saute the shallots, celery, herbs, and seasonings in 3 tablespoons of the butter for 3 minutes. Add the watercress and let it wilt.')
carrotSoup = graph.addVertex(id, 206, label, 'recipe', 'recipeTitle', 'Carrot Soup', \
'instructions', 'In a heavy-bottomed pot, melt the butter. When it starts to foam, add the onions and thyme and cook over medium-low heat until tender, about 10 minutes.')
roastPorkLoin = graph.addVertex(id, 207, label, 'recipe', 'recipeTitle', 'Roast Pork Loin', \
'instructions', 'The day before, separate the meat from the ribs, stopping about 1 inch before the end of the bones. Season the pork liberally inside and out with salt and pepper and refrigerate overnight.')

// ingredients vertices
beef = graph.addVertex(id, 300, label, 'ingredient', 'iName', 'beef')
onion = graph.addVertex(id, 301, label, 'ingredient', 'iName', 'onion')
mashedGarlic = graph.addVertex(id, 302, label, 'ingredient', 'iName', 'mashed garlic')
butter = graph.addVertex(id, 303, label, 'ingredient', 'iName', 'butter')
tomatoPaste = graph.addVertex(id, 304, label, 'ingredient', 'iName', 'tomato paste')
eggplant = graph.addVertex(id, 305, label, 'ingredient', 'iName', 'eggplant')
zucchini = graph.addVertex(id, 306, label, 'ingredient', 'iName', 'zucchini')
oliveOil = graph.addVertex(id, 307, label, 'ingredient', 'iName', 'olive oil')
yellowOnion = graph.addVertex(id, 308, label, 'ingredient', 'iName', 'yellow onion')
greenBean = graph.addVertex(id, 309, label, 'ingredient', 'iName', 'green beans')
tuna = graph.addVertex(id, 310, label, 'ingredient', 'iName', 'tuna')
tomato = graph.addVertex(id, 311, label, 'ingredient', 'iName', 'tomato')
hardBoiledEgg = graph.addVertex(id, 312, label, 'ingredient', 'iName', 'hard-boiled egg')
eggNoodles = graph.addVertex(id, 313, label, 'ingredient', 'iName', 'egg noodles')
mushroom = graph.addVertex(id, 314, label, 'ingredient', 'iName', 'mushrooms')
bacon = graph.addVertex(id, 315, label, 'ingredient', 'iName', 'bacon')
celery = graph.addVertex(id, 316, label, 'ingredient', 'iName', 'celery')
greenBellPepper = graph.addVertex(id, 317, label, 'ingredient', 'iName', 'green bell pepper')
groundBeef = graph.addVertex(id, 318, label, 'ingredient', 'iName', 'ground beef')
porkSausage = graph.addVertex(id, 319, label, 'ingredient', 'iName', 'pork sausage')
shallot = graph.addVertex(id, 320, label, 'ingredient', 'iName', 'shallots')
chervil = graph.addVertex(id, 321, label, 'ingredient', 'iName', 'chervil')
fennel = graph.addVertex(id, 322, label, 'ingredient', 'iName', 'fennel')
parsley = graph.addVertex(id, 323, label, 'ingredient', 'iName', 'parsley')
oyster = graph.addVertex(id, 324, label, 'ingredient', 'iName', 'oyster')
pernod = graph.addVertex(id, 325, label, 'ingredient', 'iName', 'Pernod')
thyme = graph.addVertex(id, 326, label, 'ingredient', 'iName', 'thyme')
carrot = graph.addVertex(id, 327, label, 'ingredient', 'iName', 'carrots')
chickenBroth = graph.addVertex(id, 328, label, 'ingredient', 'iName', 'chicken broth')
porkLoin = graph.addVertex(id, 329, label, 'ingredient', 'iName', 'pork loin')
redWine = = graph.addVertex(id, 330, label, 'ingredient', 'iName', 'red wine')

// author - book edges
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
emerilLagasse.addEdge('created', wildMushroomStroganoff, 2003)
emerilLagasse.addEdge('created', spicyMeatloaf, 2000)
aliceWaters.addEdge('created', carrotSoup, 1995)
aliceWaters.addEdge('created', roastPorkLoin, 1996)
jamesBeard.addEdge('created', oystersRockefeller, 1970)

// recipe - ingredient edges
beefBourguignon.addEdge('includes', beef, 'amount', '2 lbs')
beefBourguignon.addEdge('includes', onion, 'amount', '1 sliced')
beefBourguignon.addEdge('includes', mashedGarlic, 'amount', '2 cloves')
beefBourguignon.addEdge('includes', butter, 'amount, '3.5 Tbsp')
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
wildMushroomStroganoff('includes', eggNoodles, 'amount', '16 ozs wide')
wildMushroomStroganoff('includes', mushroom, 'amount', '2 lbs wild or exotic, cleaned, stemmed, and sliced')
wildMushroomStroganoff('includes', yellowOnion, 'amount', '1 cup thinly sliced')
spicyMeatloaf('includes', bacon, 'amount', '3 ozs diced')
spicyMeatloaf('includes', onion, 'amount', '2 cups finely chopped')
spicyMeatloaf('includes', celery, 'amount', '2 cups finely chopped')
spicyMeatloaf('includes', greenBellPepper, 'amount', '1/4 cup finely chopped')
spicyMeatloaf('includes', porkSausage, 'amount', '3/4 lbs hot')
spicyMeatloaf('includes', groundBeef, 'amount', '1 1/2 lbs chuck')
oystersRockefeller('includes', shallot, 'amount', '1/4 cup chopped')
oystersRockefeller('includes', celery, 'amount', '1/4 cup chopped')
oystersRockefeller('includes', chervil, 'amount', '1 tsp')
oystersRockefeller('includes', fennel, 'amount', '1/3 cup chopped')
oystersRockefeller('includes', parsley, 'amount', '1/3 cup chopped')
oystersRockefeller('includes', oyster, 'amount', '2 dozen on the half shell')
oystersRockefeller('includes', pernod, 'amount', '1/3 cup')
carrotSoup('includes', butter, 'amount', '4 Tbsp')
carrotSoup('includes', onions, 'amount', '2 medium sliced')
carrotSoup('includes', thyme, 'amount', '1 sprig')
carrotSoup('includes', carrot, 'amount', '2 1/2 lbs, peeled and sliced')
carrotSoup('includes', chickenBroth, 'amount', '6 cups')
roastPorkLoin('includes', porkLoin, 'amount', '1 bone-in, 4-rib')
roastPorkLoin('includes', redWine, 'amount', '1/2 cup')
roastPorkLoin('includes', chickenBroth, 'amount', '1 cup')