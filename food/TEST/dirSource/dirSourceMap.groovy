/* SAMPLE INPUT
person: 001|Julia Child|F
 */

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: true, load_new: true, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/home/automaton/graph-examples/food/TEST/dirSource/data'
//personInput = File.csv(inputfiledir + "person.csv").delimiter('|')
personInput = File.directory(inputfiledir).delimiter('|').header('id','name','gender')
//personEdgeInput = File.csv(inputfiledir + "personEdges.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(personInput).asVertices {
    label "person"
    key "id"
}
/*
load(personEdgeInput).asEdges {
    label "born"
    outV "pname1", {
        label "person"
        key "name"
    }
    inV "pname2", {
        label "person"
        key "name"
    }
}
*/
