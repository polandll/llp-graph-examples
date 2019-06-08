// script = new File('/Users/lorinapoland/CLONES/graph-examples/food/test3.groovy').text; []
// :> @script

// 3 separate lines
Vertex juliaChild = graph.addVertex(label,'author','myId', 1, 'aname','Julia Child', 'gender','F')
Vertex artOfFrenchCookingVolOne = graph.addVertex(label, 'book', 'myId', 100,'bookTitle', 'The Art of French Cooking, Vol. 1', 'publishDate', 1961)
juliaChild.addEdge('authored', artOfFrenchCookingVolOne)

// 3 lines mooshed together with semi-colons in between
// Vertex juliaChild = graph.addVertex(label,'author','id', 1, 'aname','Julia Child', 'gender','F');Vertex artOfFrenchCookingVolOne = graph.addVertex(label, 'book', 'id', 100,'bookTitle', 'The Art of French Cooking, Vol. 1', 'publishDate', 1961);juliaChild.addEdge('authored', artOfFrenchCookingVolOne)