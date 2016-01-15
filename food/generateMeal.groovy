mName|createDate|calories|[includes]
Dec 1, 2015 Meal|2015-11-30|1,000 per person|[Beef Bourguignon,Roasted Potatoes,Brussel Sprouts,Creme Brulee]

// meal vertices
SaturdayFeast = graph.addVertex(label, 'meal', 'id', 500, 'createDate', '2015-11-30', 'calories', 1000)
EverydayDinner = graph.addVertex(label, 'meal', 'id', 501, 'createDate', '2016-01-14', 'calories', 600)

// meal - recipe edges

SaturdayFeast.addEdge('includedIn', beefBourguignon)
SaturdayFeast.addEdge('includedIn', carrotSoup)
SaturdayFeast.addEdge('includedIn', oystersRockefeller)
EverydayDinner.addEdge('includedIn', carrotSoup)
EverydayDinner.addEdge('includedIn', roastPorkLoin)

// meal - book edges

artOfSimpleFood.addEdge('includedIn', EverydayDinner)
simcasCuisine.addEdge('includedIn', SaturdayFeast)