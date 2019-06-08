/* SAMPLE INPUT
name|gender|value|since
Jane Doe|F|Gold Badge|2011
Jane Doe|F|Silver Badge|2005
Jon Doe|M|Gold Badge|2012
 */

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: false, load_new: true, load_vertex_threads: 3, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/home/automaton/graph-examples/food/MISC-graphloader/vertPropJSON/'
//inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/MISC-graphloader/vertPropJSON/'
reviewerInput = File.csv(inputfiledir + "reviewerMultiMeta.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
reviewerInput = reviewerInput.transform {
  badge1 = [
    "value": it.remove("value"),
    "since": it.remove("since") ]
  it["badge"] = [badge1]
  it
}

load(reviewerInput).asVertices{
	label "reviewer"
	key "name"
	vertexProperty "badge", {
		value "value"
	}
}
