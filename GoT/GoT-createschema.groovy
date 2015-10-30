/** 
 * This groovy script (which follows the format written by Daniel Kuppitz for 
 * the MovieLens data set) implements a graph schema (details to follow)
 * for the GoT data set, parses through the GoT.txt file, 
 * and loads this data into the graph schema.
 * 
 * Titan 1.0.0
 *
 */

class GoTSchema{
         /* This method calls other helper methods in order to:
	 * 	1) Create a graph schema for the GoT data set 
	 *
	 * Parameters:
	 * Pre-Condition: A TitanGraph instance graph is ready to have data loaded into it
 	 *                 (all backend storage configuration has been set.)
 	 *
         */   
//	public static void load_data(Graph graph){
 //               println "LOAD: Call to createSchema method."
                createSchema(graph)
		graph.close()
        }
	/**
         * This private helper method will take in a TitanGraph object,
         * and then implement appropriate schema (described later) for it.
         *
	 * Parameters:
	 *	graph : instance of TitanGraph that we develop schema for
         */
        graph = TitanFactory.open('conf/titan-cassandra.properties')

        private static void createSchema(TitanGraph graph){
        	println "CREATESCHEMA: Explicitly defining graph schema."
             	def mgmt = graph.openManagement()
		
		// creating the different vertex types for our graph
		mgmt.makeVertexLabel('human').make()
		mgmt.makeVertexLabel('dragon').make()

		// creating different edge types for our graph
		mgmt.makeEdgeLabel('hatchedBy').make()
		mgmt.makeEdgeLabel('relatedBy').make()
		mgmt.makeEdgeLabel('killedBy').make()
		
		// creating keys for 'human' vertices  
		def humanId = mgmt.makePropertyKey('humanId').dataType(Integer.class).make()
		def hname = mgmt.makePropertyKey('hname')dataType(String.class).make()
		def gender = mgmt.makePropertyKey('gender')dataType(String.class).make()
		def house = mgmt.makePropertyKey('house').dataType(String.class).make()
		def origin = mgmt.makePropertyKey('origin').dataType(String.class).make()
		def done = mgmt.makePropertyKey('done').dataType(Integer.class).make() // this key is for data processing		

  		// creating keys for 'dragon' vertices
		def dragonId = mgmt.makePropertyKey('dragonId').dataType(String.class).make()
		def dname = mgmt.makePropertyKey('dname').dataType(String.class).make()
		def colors = mgmt.makePropertyKey('colors').dataType(String.class).make()

		// creating key for 'location' vertices
		def locationId = mgmt.makePropertyKey('locationID').dataType(String.class).make()
		def lname = mgmt.makePropertyKey('lname').dataType(String.class).make()
		
		// creating keys for 'relatedBy' edges (from 'human' vertex to 'human' vertex)
		def relationship = mgmt.makePropertyKey('relationship').dataType(String.class).make()

		// creating keys for 'hatchedBy' edges (from 'human' vertex to 'dragon' vertex)
		def hatchDate = mgmt.makePropertyKey('hatchDate').dataType(String.class).make()

		// creating keys for 'killedBy' edges (from 'human' vertex to 'human' vertex)
		def killDate = mgmt.makePropertyKey('killedDate').dataType(String.class).make()

		// indexing for Vertex class
		println "CREATESCHEMA: Indexing."
		mgmt.buildIndex('byHumanName', Vertex.class).addKey(hname).buildCompositeIndex()
		mgmt.buildIndex('byHouse', Vertex.class).addKey(house).buildCompositeIndex()
		mgmt.buildIndex('byOrigin', Vertex.class).addKey(origin).buildCompositeIndex()
		mgmt.buildIndex('byDone', Vertex.class).addKey(done).buildCompositeIndex()

		// indexing for Edge class
		mgmt.buildIndex('byRelatedBy', Edge.class).addKey(relationship).buildCompositeIndex()
		mgmt.buildIndex('byHatchedBy', Edge.class).addKey(hatchDate).buildCompositeIndex()
		mgmt.buildIndex('byKilledBy', Edge.class).addKey(killDate).buildCompositeIndex()

		println "CREATESCHEMA: Committing the schema."
		mgmt.commit()
		
        } // closing createSchema method

} // closing GoTSchema class
