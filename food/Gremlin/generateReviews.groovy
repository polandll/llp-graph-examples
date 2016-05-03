// Generates review vertices and edges for Recipe Toy Graph

// :load /Users/lorinapoland/CLONES/graph-examples/food/Gremlin/generateReviews.groovy

// reviewer vertices
johnDoe = graph.addVertex(label, 'reviewer', 'name','John Doe')
johnSmith = graph.addVertex(label, 'reviewer', 'name', 'John Smith')
janeDoe = graph.addVertex(label, 'reviewer', 'name','Jane Doe')
sharonSmith = graph.addVertex(label, 'reviewer', 'name','Sharon Smith')
betsyJones = graph.addVertex(label, 'reviewer', 'name','Betsy Jones')

beefBourguignon = g.V().has('recipe', 'name','Beef Bourguignon').tryNext().orElseGet {graph.addVertex(label, 'recipe', 'name', 'Beef Bourguignon')}
spicyMeatLoaf = g.V().has('recipe', 'name','Spicy Meatloaf').tryNext().orElseGet {graph.addVertex(label, 'recipe', 'name', 'Spicy Meatloaf')}
carrotSoup = g.V().has('recipe', 'name','Carrot Soup').tryNext().orElseGet {graph.addVertex(label, 'recipe', 'name', 'Carrot Soup')}

// reviewer - recipe edges
johnDoe.addEdge('rated', beefBourguignon, 'timestamp', Instant.parse('2014-01-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Pretty tasty!')
johnSmith.addEdge('rated', beefBourguignon, 'timestamp', Instant.parse('2014-01-23T00:00:00.00Z'), 'stars', 4)
janeDoe.addEdge('rated', beefBourguignon, 'timestamp', Instant.parse('2014-02-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Yummy!')
sharonSmith.addEdge('rated', beefBourguignon, 'timestamp', Instant.parse('2015-01-01T00:00:00.00Z'), 'stars', 3, 'comment', 'It was okay.')
johnDoe.addEdge('rated', spicyMeatLoaf, 'timestamp', Instant.parse('2015-12-31T00:00:00.00Z'), 'stars', 4, 'comment', 'Really spicy - be careful!')
sharonSmith.addEdge('rated', spicyMeatLoaf, 'timestamp',Instant.parse('2014-07-23T00:00:00.00Z'), 'stars', 3, 'comment', 'Too spicy for me. Use less garlic.')
janeDoe.addEdge('rated', carrotSoup, 'timestamp', Instant.parse('2015-12-30T00:00:00.00Z'), 'stars', 5, 'comment', 'Loved this soup! Yummy vegetarian!')

// Traversal to find reviews with stars of 4 or greater and a ratedDate more recent than
// :> g.E().hasLabel('rated').has('stars',gte(4)).has('timestamp', gte(Instant.parse('2015-12-31T00:00:00.00Z'))).valueMap()
