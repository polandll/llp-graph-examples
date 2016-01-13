// Add all vertices and edges for Recipe

// author vertices
juliaChild = g.addV(label, 'author', 'id', 1, 'aname','Julia Child', 'gender', 'F').next()
simoneBeck = g.addV(label, 'author', 'id', 2, 'aname', 'Simone Beck', 'gender', 'F').next()
louisetteBertholie = g.addV(label, 'author', 'id', 3, 'aname', 'Louisette Bertholie', 'gender', 'F').next()
patriciaSimon = g.addV(label, 'author', 'id', 4, 'aname', 'Patricia Simon', 'gender', 'F').next()
aliceWaters = g.addV(label, 'author', 'id', 5, 'aname', 'Alice Waters', 'gender', 'F').next()
patriciaCurtan = g.addV(label, 'author', 'id', 6, 'aname', 'Patricia Curtan', 'gender', 'F').next()
kelsieKerr = g.addV(label, 'author', 'id', 7, 'aname', 'Kelsie Kerr', 'gender', 'F').next()
fritzStreiff = g.addV(label, 'author', 'id', 8, 'aname', 'Fritz Streiff', 'gender', 'M').next()
emerilLagasse = g.addV(label, 'author', 'id', 9, 'aname', 'Emeril Lagasse', 'gender', 'M').next()
jamesBeard = g.addV(label, 'author', 'id', 10, 'aname', 'James Beard', 'gender', 'M').next()

// book vertices
artOfFrenchCookingVolOne = g.addV(label, 'book', 'id', 100, 'bookTitle', 'The Art of French Cooking, Vol. 1', 'publishDate', 1961).next()
simcasCuisine = g.addV(label, 'book, 'id', 101,'bookTitle', 'Simca's Cuisine: 100 Classic French Recipes for Every Occasion', 'publishDate',1972, 'IBSN', '0-394-40152-2').next()
frenchChefCookbook = g.addV(label, 'book', 'id', 102, 'bookTitle','The French Chef Cookbook', 'publishDate','1968, 'ISBN', '0-394-40135-2').next()
artOfSimpleFood = g.addV(label, 'book', 'id', 103, 'bookTitle', 'The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution', 'publishDate', 2007, 'ISBN', '0-307-33679-4').next()

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