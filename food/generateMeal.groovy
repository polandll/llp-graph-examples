//mName|createDate|calories|[includes]
//Dec 1, 2015 Meal|2015-11-30|1,000 per person|[Beef Bourguignon,Roasted Potatoes,Brussel Sprouts,Creme Brulee]

// meal vertices
SaturdayFeast = graph.addVertex(label, 'meal', 'id', 500, 'mealTitle', 'Saturday Feast', 'mCreateDate', '2015-11-30', 'calories', 1000)
EverydayDinner = graph.addVertex(label, 'meal', 'id', 501, 'mealTitle', 'EverydayDinner', 'mCreateDate', '2016-01-14', 'calories', 600)
JuliaDinner = graph.addVertex(label, 'meal', 'id', 502, 'mealTitle', 'JuliaDinner', 'mCreateDate', '2016-01-14', 'calories', 900)
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