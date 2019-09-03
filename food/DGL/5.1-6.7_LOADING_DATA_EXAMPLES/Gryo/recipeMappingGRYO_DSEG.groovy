/* SAMPLE INPUT
Gryo file is a binary file
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true

// DATA INPUT
// Define the data input source 
// inputfiledir is the directory for the input files 

//Need to specify a keymap to show how to identify vertices
vertexKeyMap = VertexKeyMap.with('meal','name').with('ingredient','name').with('author','name').with('book','name')
        .with('recipe','name').build();


inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/Gryo/'
recipeInput = com.datastax.dsegraphloader.api.Graph.file(inputfiledir + 'recipesDSEG.gryo').gryo().setVertexUniqueKeys(vertexKeyMap)

//Specifies what data source to load using which mapper (as defined inline)
  
load(recipeInput.vertices()).asVertices {
    labelField "~label"
    key "name"
}

load(recipeInput.edges()).asEdges {
    labelField "~label"
    outV "outV", {
        labelField "~label"
        key "name"
    }
    inV "inV", {
        labelField "~label"
        key "name"
    }
}

