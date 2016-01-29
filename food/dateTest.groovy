johnDoe = graph.addVertex(label, 'reviewer', 'id', 400, 'revname','John Doe')
beefBourguignon = graph.addVertex('id', 200, label, 'recipe', 'recipeTitle', 'Beef Bourguignon', 'instructions', 'Braise the beef. Saute the onions and carrots. Add wine and cook in a dutch oven at 425 degrees for 1 hour.')


// reviewer - recipe edges

//johnDoe.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse(Date(99, 0, 1)'2014-01-01T00:00:00.00Z'), 'stars', 5, 'comment', 'Pretty tasty!')
johnDoe.addEdge('rated', beefBourguignon, 'ratedDate', Instant.parse(Date(99, 0, 1)), 'stars', 5, 'comment', 'Pretty tasty!')
