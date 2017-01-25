/* SAMPLE INPUT
kind|name|gender
author|Julia Child|F
*/

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: true, load_new: true, load_vertex_threads: 3, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/DATA/'
peopleInput = File.csv(inputfiledir + "people.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(peopleInput).asVertices{
        labelField "kind"
        key "name"
}
