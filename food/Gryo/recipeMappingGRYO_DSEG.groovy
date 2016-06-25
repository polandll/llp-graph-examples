/* SAMPLE INPUT
Gryo file is a binary file
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true

// DATA INPUT
// Define the data input source 
// inputfiledir is the directory for the input files 

inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/Gryo/'
recipeInput = Graph.file(inputfiledir + 'recipesDSEG.gryo').gryo()

//Specifies what data source to load using which mapper (as defined inline)
  
load(recipeInput.vertices().withVertexId()).asVertices {
    labelField "~label"
    key "~id", "id"
}

load(recipeInput.edges().withVertexId()).asEdges {
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

