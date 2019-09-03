// reviewer vertices
johnDoe = graph.addVertex(label, 'person', 'personId', 11, 'name','John Doe')
johnSmith = graph.addVertex(label, 'person', 'personId', 12, 'name', 'John Smith')
janeDoe = graph.addVertex(label, 'person', 'personId', 13, 'name','Jane Doe')
sharonSmith = graph.addVertex(label, 'person', 'personId', 14, 'name','Sharon Smith')
betsyJones = graph.addVertex(label, 'person', 'personId', 15, 'name','Betsy Jones')

beefBourguignon = g.V().has('recipe', 'recipeId', 2001, 'name','Beef Bourguignon').tryNext().orElseGet {graph.addVertex(label, 'recipe', 'recipeId', 2001, 'name', 'Beef Bourguignon')}
spicyMeatLoaf = g.V().has('recipe', 'recipeId', 2005, 'name','Spicy Meatloaf').tryNext().orElseGet {graph.addVertex(label, 'recipe', 'recipeId', 2005, 'name', 'Spicy Meatloaf')}
carrotSoup = g.V().has('recipe', 'recipeId', 2007, 'name','Carrot Soup').tryNext().orElseGet {graph.addVertex(label, 'recipe', 'recipedId', 2007, 'name', 'Carrot Soup')}

// reviewer - recipe edges
johnDoe.addEdge('reviewed', beefBourguignon, 'timestamp', Instant.parse('2014-01-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Pretty tasty!')
johnSmith.addEdge('reviewed', beefBourguignon, 'timestamp', Instant.parse('2014-01-23T00:00:00.00Z'), 'stars', 4)
janeDoe.addEdge('reviewed', beefBourguignon, 'timestamp', Instant.parse('2014-02-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Yummy!')
sharonSmith.addEdge('reviewed', beefBourguignon, 'timestamp', Instant.parse('2015-01-01T00:00:00.00Z'), 'stars', 3, 'comment', 'It was okay.')
johnDoe.addEdge('reviewed', spicyMeatLoaf, 'timestamp', Instant.parse('2015-12-31T00:00:00.00Z'), 'stars', 4, 'comment', 'Really spicy - be careful!')
sharonSmith.addEdge('reviewed', spicyMeatLoaf, 'timestamp',Instant.parse('2014-07-23T00:00:00.00Z'), 'stars', 3, 'comment', 'Too spicy for me. Use less garlic.')
janeDoe.addEdge('reviewed', carrotSoup, 'timestamp', Instant.parse('2015-12-30T00:00:00.00Z'), 'stars', 5, 'comment', 'Loved this soup! Yummy vegetarian!')

