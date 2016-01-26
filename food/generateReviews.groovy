// script = new File('/Users/lorinapoland/CLONES/graph-examples/food/generateReviews.groovy').text; []

// reviewer vertices
johnDoe = graph.addVertex(label, 'reviewer', 'id', 400, 'revname','John Doe')
johnSmith = graph.addVertex(label, 'reviewer', 'id', 401, 'revname','John Smith')
janeDoe = graph.addVertex(label, 'reviewer', 'id', 402, 'revname','Jane Doe')
sharonSmith = graph.addVertex(label, 'reviewer', 'id', 403, 'revname','Sharon Smith')
betsyJones = graph.addVertex(label, 'reviewer', 'id', 404, 'revname','Betsy Jones')

// reviewer - recipe edges
johnDoe.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2014-01-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Pretty tasty!')
johnSmith.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2014-01-23T00:00:00.00Z'), 'stars', 4)
janeDoe.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2014-02-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Yummy!')
sharonSmith.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse('2015-01-01T00:00:00.00Z'), 'stars', 3, 'comment', 'It was okay.')
johnDoe.addEdge('rated', spicyMeatloaf, 'ratedDate', Instant.parse('2015-12-31T00:00:00.00Z'), 'stars', 4, 'comment', 'Really spicy - be careful!')
sharonSmith.addEdge('rated', spicyMeatloaf, 'ratedDate',Instant.parse('2014-07-23T00:00:00.00Z'), 'stars', 3, 'comment', 'Too spicy for me. Use less garlic.')
janeDoe.addEdge('rated', carrotSoup, 'ratedDate', Instant.parse('2015-12-30T00:00:00.00Z'), 'stars', 5, 'comment', 'Loved this soup! Yummy vegetarian!')