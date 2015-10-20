/** 
 * This groovy script (which follows the format written by Daniel Kuppitz for 
 * the MovieLens data set) implements a graph schema (details to follow)
 * for the GoT data set, parses through the GoT.txt file, 
 * and loads this data into the graph schema.
 * 
 * Titan 1.0.0
 *
 */

class GoTParser{

	// data fields for class, involved when parsing through each item
	static String[] item_delimiters
	static String[]	review_delimiters
	
	static{
		//BasicConfigurator.configure()
		//Gremlin.load()
		item_delimiters = ['ASIN: ', 'title: ', 'group: ', 'salesrank: ', 'similar: ', 'categories: ', 'downloaded: ']
		review_delimiters = ['customer: ', 'rating: ', 'votes: ', 'helpful: ', 'cutomer: ']
	}

        /**
         * This method calls other helper methods in order to:
	 * 	1) Create a graph schema for the GoT data set 
         *	2) Parse through and load the GoT data set into the graph
	 *
	 * Parameters:
	 * 	pathToData:	The path to the GoT data set		
	 *
	 * Pre-Condition: A TitanGraph instance graph is ready to have data loaded into it
 	 *                 (all backend storage configuration has been set.)
 	 *
 	 * Post-Conditon: The TitanGraph instance graph is closed, with the data already loaded into it.
         */   
	public static void load_data(final String pathToData, Graph graph){
                println "LOAD: Call to createSchema method."
                createSchema(graph)
                println "LOAD: Call to parse method."
        	parse(graph, pathToData)
		graph.close()
        }
	/**
         * This private helper method will take in a TitanGraph object,
         * and then implement appropriate schema (described later) for it.
         *
	 * Parameters:
	 *	graph : instance of TitanGraph that we develop schema for
         */
        private static void createSchema(TitanGraph graph){
        	println "CREATESCHEMA: Explicitly defining graph schema."
             	def mgmt = graph.openManagement()
		//println "CREATESCHEMA: Time to create indices."
		
		// creating the different vertex types for our graph
		mgmt.makeVertexLabel('human').make()
		mgmt.makeVertexLabel('dragon').make()
		//mgmt.makeVertexLabel('category').make()
		//mgmt.makeVertexLabel('group').make()

		// creating different edge types for our graph
		mgmt.makeEdgeLabel('hatchedBy').make()
		mgmt.makeEdgeLabel('relatedBy').make()
		mgmt.makeEdgeLabel('killedBy').make()
		
		// creating keys for 'human' vertices  
		def humanId = mgmt.makePropertyKey('humanId').dataType(Integer.class).make()
		def hname = mgmt.makePropertyKey('name')dataType(String.class).make()
		def gender = mgmt.makePropertyKey('gender')dataType(String.class).make()
		def house = mgmt.makePropertyKey('house').dataType(String.class).make()
		def origin = mgmt.makePropertyKey('origin').dataType(String.class).make()
		def done = mgmt.makePropertyKey('done').dataType(Integer.class).make() // this key is for data processing		

  		// creating keys for 'dragon' vertices
		def dragonId = mgmt.makePropertyKey('dragonId').dataType(String.class).make()
		def dname = mgmt.makePropertyKey('name').dataType(String.class).make()
		def colors = mgmt.makePropertyKey('colors').dataType(String.class).make()

		// creating key for 'location' vertices
		def locationId = mgmt.makePropertyKey('locationID').dataType(String.class).make()
		def lname = mgmt.makePropertyKey('name').dataType(String.class).make()
		
		// creating keys for 'relatedBy' edges (from 'human' vertex to 'human' vertex)
		def relationship = mgmt.makePropertyKey('relationship').dataType(String.class).make()

		// creating keys for 'hatchedBy' edges (from 'human' vertex to 'dragon' vertex)
		def hdate = mgmt.makePropertyKey('hatchDate').dataType(String.class).make()

		// creating keys for 'relatedBy' edges (from 'human' vertex to 'human' vertex)
		def kdate = mgmt.makePropertyKey('killedDate').dataType(String.class).make()

		// indexing for Vertex class
		println "CREATESCHEMA: Indexing."
		mgmt.buildIndex('byName', Vertex.class).addKey(humanId).buildCompositeIndex()
		mgmt.buildIndex('byHouse', Vertex.class).addKey(house).buildCompositeIndex()
		mgmt.buildIndex('byOrigin', Vertex.class).addKey(origin).buildCompositeIndex()
		mgmt.buildIndex('byDone', Vertex.class).addKey(done).buildCompositeIndex()

		// indexing for Edge class
		mgmt.buildIndex('byRelatedBy', Edge.class).addKey(relationship).buildCompositeIndex()
		mgmt.buildIndex('byHatchedBy', Edge.class).addKey(hdate).buildCompositeIndex()
		mgmt.buildIndex('byKilledBy

		println "CREATESCHEMA: Committing the schema."
		mgmt.commit()
		
        } // closing createSchema method


        /**
         * This helper method actually goes through the GoT.txt file, and
	 * and does appropriate string processing, and loading of data into the
	 * instance of the graph schema that was implemented by the createSchema 
	 * helper method.
	 *
         * Due to how 'non-uniform' this data set is, this parse method involves 
	 * quite a bit of  string processing.
	 *
	 * Parameters: 
	 *		g:	instance of TitanGraph to load data into
	 * 		dataDirectory:	directory where Amazon data file is stored
			datafile:	name of GoT data file	
	 * Pre-condition: The graph g has no data in it, but schema has been set up
	 *		  from prior call to createSchema method
	 * Post-conditon: The graph g has data loaded into it, and is available to
	 *		  be queried through the Gremlin shell
         */
        private static void parse(TitanGraph graph, final String pathToData){
	
		println "PARSE: This is the parse method.\n"

		def file = new File(pathToData)

		// split along blocks of items (delimited by 'Id:')
		String[] dataList = file.text.split('\nId:   ') 
			
		int num_items = dataList.size() // overcounts due to header in file
		int processed_items = 0
		String[] curr_item 		// item processing below
		
		// these will be property key-values for 'item' vertices
		int curr_Id
		String curr_ASIN
		String curr_title
		String curr_group
		//String curr_salesrank

		// used when connecting items that were bought concurrently, via an edge
		String curr_similar
		String[] similar_array	// after we split curr_similar

		// used when connecting an item to its category (e.g. Book, DVD), via an edge
		String curr_categories	
		String[] categories_array	// after we split curr_categories
		
		// used when processing customer review data
		String curr_downloaded
		String[] reviews_array
		String curr_review
		String[] curr_review_data
		int num_reviews
		String curr_customer
		int  curr_rating
		int curr_votes
		int curr_helpful

		// these will be used for loading data into the graph instance
		def g = graph.traversal()	// need this for querying
		Vertex human_vertex
		Vertex dragon_vertex
		Vertex location_vertex
		Edge relationship_edge
		Edge hatched_edge
		Edge killed_edge

		//num_items = 900		// this was hard-coded for the purpose of debugging
		// loop through items, perform string-processing
 		for(int x = 0; x < num_items; x++){
			// replace all item_delimiters with 'SHAUNAK'
			for(String y : item_delimiters){
				//println y
				dataList[x] = dataList[x].replace(y, 'SHAUNAK')
			}
			curr_item = dataList[x].split('SHAUNAK') // split along 'SHAUNAK'
			// if the current item has all 8 attributes...
			if(curr_item.size() == 8){
				processed_items = processed_items + 1

				curr_Id = Integer.parseInt(curr_item[0].trim())
				curr_ASIN = curr_item[1].trim()
				curr_title = curr_item[2].trim()
				curr_group = curr_item[3].trim()
				//curr_salesrank = curr_item[4]
				curr_similar = curr_item[5]
				curr_categories = curr_item[6]
				curr_downloaded = curr_item[7]

				//println "\nProcessing: "
				//println curr_title 
				//println processed_items

				// now it is time to start creating Vertex instances

				// if a vertex already exists with this ASIN, get it				
				if(g.V().has('ASIN', curr_ASIN).count().next() > 0){
					//print "THIS ITEM ALREADY EXISTS."
					item_vertex = g.V().has('ASIN', curr_ASIN).next()	
				}
				// else make a new instance
				else{		
					//println "THIS ITEM DOES NOT ALREADY EXIST."
					item_vertex = graph.addVertex(label, 'item')
					item_vertex.property('Id', curr_Id)
					item_vertex.property('ASIN', curr_ASIN)
					item_vertex.property('title', curr_title)
					item_vertex.property('done', 1)
				}
				//println item_vertex.values('ASIN').next()
				//println item_vertex.property('done').value()

				// if item_vertex not done...
				if(item_vertex.value('done').next() == 0){
					//println "This item is NOT done."
					// fill out property fields of this vertex instance
					item_vertex.property('Id', curr_Id)
                                	//item_vertex.property('ASIN', curr_ASIN)
                                	item_vertex.property('title', curr_title)
					item_vertex.property('done', 1)
				}
				//println item_vertex.property('ASIN').value()
 				//println item_vertex.property('done').value()
				//else{
					//println "This item is done."
					//println item_vertex.label
					//println item_vertex.ASIN
					//println item_vertex.title
					//println item_vertex.done
					//return
				//}
				//println "TIME TO PROCESS GROUP"
				//println item_vertex.property('ASIN').value()
				//println item_vertex.property('done').value()
				
				// check if curr_group vertex already exists
				if(g.V().has('group', curr_group).count().next() > 0 ){
					group_vertex = g.V().has('group', curr_group).next()
				}
				else{
					group_vertex = graph.addVertex(label, 'group')
					group_vertex.property('group', curr_group)
				}
				// add an edge from item_vertex to group_vertex
				group_edge = item_vertex.addEdge('hasGroup', group_vertex)
				
				//println "TIME TO PROCESS SIMILAR ITEMS"
				// process through similar items to curr_item
				similar_array = curr_similar.split('  ')
				similar_array[0] = similar_array[0].trim()
				//println similar_array[0]
				//println similar_array[0].getClass()
				def num_similar = Integer.parseInt(similar_array[0])

				// if there is at least 1 other item bought concurrently
				if(num_similar > 0){
					//println "THERE ARE SIMILAR ITEMS."
					// loop through these, see if they have a corresponding vertez
					for(int i = 1; i <= Integer.parseInt(similar_array[0]); i++){
						// the case this has already been initialized
						similar_array[i]  = similar_array[i].trim()
						if (g.V().has('ASIN', similar_array[i]).count().next() > 0){
							//println "THIS SIMILAR ITEM ALREADY EXISTS"
							//println "Found vertex with ASIN: " + similar_array[i]
							similar_vertex = g.V().has('ASIN', similar_array[i]).next()
							//println similar_vertex.label
							//println similar_vertex.ASIN
							//println similar_vertex.done	
							//return			
						}
						// this item does not have a vertex yet, create an instance for it
						else{
							//println "THIS SIMILAR ITEM DOES NOT ALREADY EXIST."
							//println "Creating NEW vertex with ASIN: " + similar_array[i]
							similar_vertex = graph.addVertex(label, 'item')
							similar_vertex.property('ASIN', similar_array[i])
							similar_vertex.property('done', 0)	
						}
						//create an edge from item_vertex to similar_vertex
						similar_edge = item_vertex.addEdge('purchasedWith', similar_vertex)	
					} 
				}

				// process through reviews/ratings for curr_item
				reviews_array = curr_downloaded.split('\n')
				//println reviews_array
				
  				// if there are reviews				
				if(reviews_array.size() > 2){
					// loop through customer reviews given to curr_item
					for(int i = 1; i < reviews_array.size() - 1; i++){
						// replace appropriate delimiters with SHAUNAK
						for(String z : review_delimiters){
							reviews_array[i] = reviews_array[i].replace(z, 'SHAUNAK')	
						}
						// split along new delimiter 'SHAUNAK'
						curr_review_data = reviews_array[i].split('SHAUNAK')
						curr_customer = curr_review_data[1].replaceAll("\\s+", "")
						curr_rating = Integer.parseInt(curr_review_data[2].trim())
						curr_votes = Integer.parseInt(curr_review_data[3].trim())
						curr_helpful = Integer.parseInt(curr_review_data[4].trim())

						// if curr_customer already has a 'customer' vertex made, grab it	
						if(g.V().has('customerId', curr_customer).count().next() > 0){
							//println "THIS CUSTOMER ALREADY EXISTS"
							customer_vertex =  g.V().has('customerId', curr_customer).next()
							//return
						}
						// else create a new instance of 'customer' vertex
						else{
							customer_vertex = graph.addVertex(label, 'customer')
							customer_vertex.property('customerId', curr_customer)
						}
						// create an edge, fill up property values
						rated_edge = customer_vertex.addEdge('rated', item_vertex) 
						rated_edge.property('rating' , curr_rating)
						rated_edge.property('votes', curr_votes)
						rated_edge.property('helpful', curr_helpful)
					}
				}	
				// process through the categories for curr_item
				
				// println "PARSE: Processing categories for: " + curr_title
				//println "TIME TO PROCESS CATEGORIES."
  				// split along newline character
				categories_array = curr_categories.split("\n")
				//println categories_array
				//println "PARSE: For loop.\n"
				for(int i = 1; i < categories_array.size(); i++){
					// get rid of whitespace
					categories_array[i] = categories_array[i].trim()
                                        //println categories_array[i]

					// if a vertex already exists for this category, get it
					if(g.V().has('category', categories_array[i]).count().next() > 0){
						//println "ALREADY FOUND THIS CATEGORY."
						category_vertex = g.V().has('category', categories_array[i]).next()
						//return 
					}
					// else instantiate new 'category' vertex, initialize its category
					else{
						category_vertex = graph.addVertex(label, 'category')
						category_vertex.property('category',  categories_array[i])
					}
					// create a 'hasCategory' edge from item_vertex to category_vertex
					item_vertex.addEdge('hasCategory', category_vertex)
				}
			}
			// monitor progress of parsing and loading of items
			if(processed_items%250 == 0){
				graph.tx().commit()
				println "PARSE: Loaded " + processed_items + " items."
			}

			if(processed_items == 10000){
				println "Processed 10000 items."
				graph.tx().commit()
				graph.close()
				System.exit(0)
			}
		} // done looping through all items 
		//graph.tx().commit()
		// println true_items
	} // closing parse method
} // closing AmazonParser class
