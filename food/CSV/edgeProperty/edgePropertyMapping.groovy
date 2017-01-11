// MAPPING SCRIPT

/* SAMPLE INPUT
person: Julia
personKnowsEdge: Julia|James Beard|knew each other in New York
*/

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: true, load_new: true, load_vertex_threads: 3, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/edgeProperty/'
personInput = File.csv(inputfiledir + "person.csv").delimiter('|')
personKnowsEdgesInput = File.csv(inputfiledir + "personKnowsEdges.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(personInput).asVertices {
    label "person"
    key "name"
}

load(personKnowsEdgesInput).asEdges {
    label "knows"
    outV "aname", {
        label "person"
        key "name"
    }
    inV "bname", {
        label "person"
        key "name"
    }
}
