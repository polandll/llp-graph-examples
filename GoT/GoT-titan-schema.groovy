/* titan-schema.groovy
 *
 * Helper functions for declaring Titan schema elements
 * (vertex labels, edge labels, property keys) to accommodate
 * TP3 sample data.
 *
 * Sample usage in a gremlin.sh session:
 *
 * gremlin> :load data/GoT-titan-schema.groovy
 * ==>true
 * ==>true
 * gremlin> t = TitanFactory.open('conf/titan-cassandra.properties')
 * ==>standardtitangraph[cassandrathrift:[127.0.0.1]]
 * gremlin> defineGoTSchema(t)
 * ==>null
 * gremlin> t.close()
 * ==>null
 * gremlin>
 */

def defineGoTSchema(TitanGraph graph) {
    graph = TitanGraph.openManagement()
    
    // vertex labels
    human = graph.makeVertexLabel("human").make()
    dragon   = graph.makeVertexLabel("dragon").make()
    location = graph.makeVertexLabel("location").make()
    
    // edge labels
    hatchedBy  = graph.makeEdgeLabel("hatchedBy").make()
    killedBy = graph.makeEdgeLabel("killedBy").make()
    relatedBy = graph.makeEdgeLabel("relatedBy").make()
    
    // creating keys for 'human' vertices 
    hname   = graph.makePropertyKey("hname").dataType(String.class).make()
    house  = graph.makePropertyKey("house").dataType(String.class).make()
    gender = graph.makePropertyKey("gender").dataType(String.class).make()
    origin = graph.makePropertyKey("origin").dataType(String.class).make()
                 
    // creating keys for 'dragon' vertices
    dname = graph.makePropertyKey('dname').dataType(String.class).make()
    colors = graph.makePropertyKey('colors').dataType(String.class).make()

    // creating key for 'location' vertices
    locationId = graph.makePropertyKey('locationID').dataType(String.class).make()
    lname = graph.makePropertyKey('lname').dataType(String.class).make()
               
    // creating keys for 'relatedBy' edges (from 'human' vertex to 'human' vertex)
    relationship = graph.makePropertyKey('relationship').dataType(String.class).make()
    
    // creating keys for 'hatchedBy' edges (from 'human' vertex to 'dragon' vertex)
    hatchDate = graph.makePropertyKey('hatchDate').dataType(String.class).make()

 	// creating keys for 'killedBy' edges (from 'human' vertex to 'human' vertex)
	killDate = graph.makePropertyKey('killedDate').dataType(String.class).make()

	// indexing for Vertex class
	graph.buildIndex('byHumanName', Vertex.class).addKey(hname).buildCompositeIndex()
	graph.buildIndex('byHouse', Vertex.class).addKey(house).buildCompositeIndex()
	graph.buildIndex('byOrigin', Vertex.class).addKey(origin).buildCompositeIndex()
	graph.buildIndex('byDone', Vertex.class).addKey(done).buildCompositeIndex()graph.
    
    // indexing for Vertex class
    graph.buildIndex("humanByName", Vertex.class).addKey(name).indexOnly(human).buildCompositeIndex()
    graph.buildIndex("dragonByName", Vertex.class).addKey(name).indexOnly(dragon).buildCompositeIndex()
    graph.buildIndex('byHouse', Vertex.class).addKey(house).buildCompositeIndex()
	graph.buildIndex('byOrigin', Vertex.class).addKey(origin).buildCompositeIndex()
	
	// indexing for Edge Class
	graph.buildIndex('byRelatedBy', Edge.class).addKey(relationship).buildCompositeIndex()
    graph.buildIndex('byHatchedBy', Edge.class).addKey(hatchDate).buildCompositeIndex()
    graph.buildIndex('byKilledBy', Edge.class).addKey(killDate).buildCompositeIndex()
    graph.commit()
}
