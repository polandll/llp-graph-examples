juliaChild = graph.addVertex(label,'author','id', 1, 'aname','Julia Child', 'gender','F')
artOfFrenchCookingVolOne = graph.addVertex(label, 'book', 'id', 100,'bookTitle', 'The Art of French Cooking, Vol. 1', 'publishDate', 1961)
juliaChild.addEdge('authored', artOfFrenchCookingVolOne)