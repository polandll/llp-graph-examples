/* SAMPLE INPUT
person: Julia Child|F
personEdges: Julia Child|JCMom|1930-01-01|10:00
 */

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: false, load_new: true, load_vertex_threads: 3, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/home/automaton/graph-examples/food/TEST/DateTime/'
personInput = File.csv(inputfiledir + "person.csv").delimiter('|')
personEdgeInput = File.csv(inputfiledir + "personEdges.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(personInput).asVertices {
    label "person"
    key "name"
}

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
