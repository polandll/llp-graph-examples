/* SAMPLE INPUT
reviewer: { "name":"Jon Doe", "gender":"M", "badge" : { "value": "Gold Badge","since" : 2012 } }
 */

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: true, load_new: true, load_vertex_threads: 3, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/MISC-graphloader/vertPropJSON/'
reviewerInput = File.json(inputfiledir + "reviewer.json")

//Specifies what data source to load using which mapper (as defined inline)
  
load(reviewerInput).asVertices{
	label "reviewer"
	key "name"
	vertexProperty "badge", {
		value "value"
	}
}
