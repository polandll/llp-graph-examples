/* SAMPLE INPUT
Gryo file is a binary file
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true

// DATA INPUT
// Define the data input source 
// inputfiledir is the directory for the input files 

inputfiledir = '/tmp/'
recipeInput = Graph.file(inputfiledir + 'multiCard.json').graphson()
  
load(recipeInput.vertices()).asVertices {
    labelField "~label"
    key "~id", "id"
}

load(recipeInput.edges()).asEdges {
    labelField "~label"
    outV "outV", {
        labelField "~label"
        key "~id", "id"
    }
    inV "inV", {
        labelField "~label"
        key "~id", "id"
    }
}
