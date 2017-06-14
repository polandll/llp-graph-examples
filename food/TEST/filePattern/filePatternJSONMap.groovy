/* SAMPLE INPUT
{"name":"Julia Child","gender":"F"}
*/

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: true, load_new: true, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files

inputfiledir = '/home/automaton/graph-examples/food/TEST/filePattern/data_json'
personInput = File.directory(inputfiledir).fileMatches("author*.json")

//Specifies what data source to load using which mapper (as defined inline)
  
load(personInput).asVertices {
    label "author"
    key "name"
}
