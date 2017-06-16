/* SAMPLE INPUT
protein|fat
1.3|0.4
*/

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: false, load_new: true, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/home/automaton/graph-examples/food/TEST/GEO/5.1/'
dataInput = File.csv(inputfiledir + "proteinCarb.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(dataInput).asVertices {
    label "data"
    key "name"
}
