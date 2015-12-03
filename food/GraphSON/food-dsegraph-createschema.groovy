/* food-titan-createschema.groovy
 *
 * Helper functions for declaring DSE Graph schema elements
 * (vertex labels, edge labels, property keys) to accommodate
 * TP3 sample data.
 *
 * Sample usage in a gremlin.sh session:
 *
 * gremlin> :load /home/polandll/CLONES/graph-examples/food/GraphML/food-dsegraph-schema.groovy
 * ==>true
 * ==>true
 * gremlin> t = GraphFactory.open('config')
 * ==>standardtitangraph[cassandrathrift:[127.0.0.1]]
 * gremlin> defineFoodSchema(t)
 * ==>null
 * gremlin> t.io(IoCore.graphml()).readGraph("/home/polandll/CLONES/graph-examples/food/GraphML/food.gml")
 *
 * Do queries to check data before closing the graph
 * w = traveral(standard())
 * Find all the edges that are labelled "rated" and print the property values
 * w.E().has(label,'rated').values()  
 *
 * gremlin> t.close()
 * ==>null
 * gremlin>
 */

def defineFoodSchema(DSEGraph) {
    m = DSEGraph.openManagement()
    
    // vertex labels
    author = m.buildVertexLabel("author").add()
    recipe = m.buildVertexLabel("recipe").add()
    ingredient = m.buildVertexLabel("ingredient").add()
    book = m.buildVertexLabel("book").add()
    meal = m.buildVertexLabel("meal").add()
    reviewer = m.buildVertexLabel("reviewer").add()
    
    // edge labels
    created  = m.buildEdgeLabel("created").add()
    includes = m.buildEdgeLabel("includes").add()
    includedIn = m.buildEdgeLabel("includedIn").add()
    rated = m.buildEdgeLabel("rated").add()
    
    // creating keys for vertices 
    aname   = m.buildPropertyKey("aname", String.class).add()
    gender = m.buildPropertyKey("gender", String.class).add()
    revname   = m.buildPropertyKey("revname", String.class).add()
    title   = m.buildPropertyKey("title", String.class).add()
    instructions  = m.buildPropertyKey("instructions", String.class).add()
    iname   = m.buildPropertyKey("iname", String.class).add()
    category = m.buildPropertyKey("category", String.class).add()
    bookTitle = m.buildPropertyKey("bookTitle", String.class).add()
    publishDate = m.buildPropertyKey("publishDate", Instant.class).add()
    mname   = m.buildPropertyKey("mname", String.class).add()
    createDate = m.buildPropertyKey("createDate", Instant.class).add()
                 
               
    // creating keys for edges
    rCreateDate = m.buildPropertyKey('rCreateDate', Instant.class).add()
    stars = m.buildPropertyKey('stars', String.class).add()
    ratedDate = m.buildPropertyKey('ratedDate', Instant.class).add()
    amount = m.buildPropertyKey('amount', String.class).add()
    

    // indexing for Vertex class
    m.buildIndex('byRecipe', Vertex.class).ofMaterializedView().byPropertyKey(title).add()
    m.buildIndex('byMeal', Vertex.class).ofMaterializedView().byPropertyKey(mname).add()
    m.buildIndex('byIngredient', Vertex.class).ofMaterializedView().byPropertyKey(iname).add()
    m.buildIndex('byReviewer', Vertex.class).ofMaterializedView().byPropertyKey(revname).add()
	
    // indexing for Edge Class
    m.buildEdgeIndex('rated', rated, Direction.OUT).byPropertyKey(stars).add()
    m.commit()
}
