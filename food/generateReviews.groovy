// reviewer vertices
johnDoe = graph.addVertex(label, 'reviewer', 'id', 400, 'revname','John Doe')
johnSmith = graph.addVertex(label, 'reviewer', 'id', 401, 'revname','John Smith')
janeDoe = graph.addVertex(label, 'reviewer', 'id', 402, 'revname','Jane Doe')
sharonSmith = graph.addVertex(label, 'reviewer', 'id', 403, 'revname','Sharon Smith')
betsyJones = graph.addVertex(label, 'reviewer', 'id', 404, 'revname','Betsy Jones')

// reviewer - recipe edges
johnDoe.addEdge('rated', beefBourguignon, 'ratedDate', '2014-01-01', 'stars', 5, 'comment', 'Pretty tasty!')
johnSmith.addEdge('rated', beefBourguignon, 'ratedDate', '2014-01-23', 'stars', 4)
janeDoe.addEdge('rated', beefBourguignon, 'ratedDate', '2014-02-01', 'stars', 5, 'comment', 'Yummy!')
sharonSmith.addEdge('rated', beefBourguignon, 'ratedDate', '2015-01-01', 'stars', 3, 'comment', 'It was okay.')
johnDoe.addEdge('rated', spicyMeatloaf, 'ratedDate', '2015-12-31', 'stars', 4, 'comment', 'Really spicy - be careful!')
sharonSmith.addEdge('rated', spicyMeatloaf, 'ratedDate','2014-07-23', 'stars', 3, 'comment', 'Too spicy for me. Use less garlic.')
janeDoe.addEdge('rated', carrotSoup, 'ratedDate', '2015-12-30', 'stars', 5, 'comment', 'Loved this soup! Yummy vegetarian!')