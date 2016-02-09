// Generates review vertices and edges for Recipe Toy Graph

// script = new File('/Users/lorinapoland/CLONES/graph-examples/food/Gremlin/generateReviews.groovy').text; []
// :> @script

// reviewer vertices
Vertex johnDoe = graph.addVertex(label, 'reviewer', 'id', 400, 'revname','John Doe')
Vertex johnSmith = graph.addVertex(label, 'reviewer', 'id', 401, 'revname','John Smith')
Vertex janeDoe = graph.addVertex(label, 'reviewer', 'id', 402, 'revname','Jane Doe')
Vertex sharonSmith = graph.addVertex(label, 'reviewer', 'id', 403, 'revname','Sharon Smith')
Vertex betsyJones = graph.addVertex(label, 'reviewer', 'myId', 404, 'revname','Betsy Jones')

beefBourguignon = g.V().hasLabel('recipe').has('recipeTitle','Beef Bourguignon').tryNext().orElseGet {graph.addVertex(label, "recipe", "recipeTitle", 'Beef Bourguignon')}
spicyMeatLoaf = g.V().hasLabel('recipe').has('recipeTitle','Spicy Meatloaf').tryNext().orElseGet {graph.addVertex(label, "recipe", "recipeTitle", 'Spicy Meatloaf')}
carrotSoup = g.V().hasLabel('recipe').has('recipeTitle','Carrot Soup').tryNext().orElseGet {graph.addVertex(label, "recipe", "recipeTitle", 'Carrot Soup')}

// reviewer - recipe edges
johnDoe.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2014-01-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Pretty tasty!')
johnSmith.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2014-01-23T00:00:00.00Z'), 'stars', 4)
janeDoe.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2014-02-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Yummy!')
sharonSmith.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2015-01-01T00:00:00.00Z'), 'stars', 3, 'comment', 'It was okay.')
johnDoe.addEdge('rated', spicyMeatLoaf, 'ratedDate', Instant.parse('2015-12-31T00:00:00.00Z'), 'stars', 4, 'comment', 'Really spicy - be careful!')
sharonSmith.addEdge('rated', spicyMeatLoaf, 'ratedDate',Instant.parse('2014-07-23T00:00:00.00Z'), 'stars', 3, 'comment', 'Too spicy for me. Use less garlic.')
janeDoe.addEdge('rated', carrotSoup, 'ratedDate', Instant.parse('2015-12-30T00:00:00.00Z'), 'stars', 5, 'comment', 'Loved this soup! Yummy vegetarian!')

// Traversal to find reviews with stars of 4 or greater and a ratedDate more recent than
// :> g.E().hasLabel('rated').has('stars',gte(4)).has('ratedDate', gte(Instant.parse('2015-12-31T00:00:00.00Z'))).valueMap()