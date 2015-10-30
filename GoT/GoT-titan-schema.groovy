/* titan-schema.groovy
 *
 * Helper functions for declaring Titan schema elements
 * (vertex labels, edge labels, property keys) to accommodate
 * TP3 sample data.
 *
 * Sample usage in a gremlin.sh session:
 *
 * gremlin> :load /home/polandll/CLONES/graph-examples/GoT/GoT-titan-schema.groovy
 * ==>true
 * ==>true
 * gremlin> t = TitanFactory.open('conf/titan-cassandra.properties')
 * ==>standardtitangraph[cassandrathrift:[127.0.0.1]]
 * gremlin> defineGoTSchema(t)
 * ==>null
 * gremlin> t.io(IoCore.graphml()).readGraph('/home/polandll/CLONES/graph-examples/GoT/GoT.gml')
 *
 * Do queries to check data before closing the graph
 *
 * gremlin> t.close()
 * ==>null
 * gremlin>
 */

def defineGoTSchema(titanGraph) {
    m = titanGraph.openManagement()
    
    // vertex labels
    human = m.makeVertexLabel("human").make()
    dragon   = m.makeVertexLabel("dragon").make()
    location = m.makeVertexLabel("location").make()
    
    // edge labels
    hatchedBy  = m.makeEdgeLabel("hatchedBy").make()
    killedBy = m.makeEdgeLabel("killedBy").make()
    relatedBy = m.makeEdgeLabel("relatedBy").make()
    
    // creating keys for 'human' vertices 
    hname   = m.makePropertyKey("hname").dataType(String.class).make()
    house  = m.makePropertyKey("house").dataType(String.class).make()
    gender = m.makePropertyKey("gender").dataType(String.class).make()
    origin = m.makePropertyKey("origin").dataType(String.class).make()
                 
    // creating keys for 'dragon' vertices
    dname = m.makePropertyKey('dname').dataType(String.class).make()
    colors = m.makePropertyKey('colors').dataType(String.class).make()

    // creating key for 'location' vertices
    locationId = m.makePropertyKey('locationID').dataType(String.class).make()
    lname = m.makePropertyKey('lname').dataType(String.class).make()
               
    // creating keys for 'relatedBy' edges (from 'human' vertex to 'human' vertex)
    relationship = m.makePropertyKey('relationship').dataType(String.class).make()
    
    // creating keys for 'hatchedBy' edges (from 'human' vertex to 'dragon' vertex)
    hatchDate = m.makePropertyKey('hatchDate').dataType(String.class).make()

 	// creating keys for 'killedBy' edges (from 'human' vertex to 'human' vertex)
	killDate = m.makePropertyKey('killDate').dataType(String.class).make()

	// indexing for Vertex class
	m.buildIndex('byHumanName', Vertex.class).addKey(hname).buildCompositeIndex()
	m.buildIndex('byHouse', Vertex.class).addKey(house).buildCompositeIndex()
	m.buildIndex('byOrigin', Vertex.class).addKey(origin).buildCompositeIndex()
	
	// indexing for Edge Class
	m.buildIndex('byRelatedBy', Edge.class).addKey(relationship).buildCompositeIndex()
    m.buildIndex('byHatchedBy', Edge.class).addKey(hatchDate).buildCompositeIndex()
    m.buildIndex('byKilledBy', Edge.class).addKey(killDate).buildCompositeIndex()
    m.commit()
}
