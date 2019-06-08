// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source 
// inputfiledir is the directory for the input files 

vertexKeyMap = VertexKeyMap.with('book', 'name').with('recipe', 'name').with('person','name').build();
recipeInput = Graph.file('recipe.gryo').gryo().dse().setVertexUniqueKeys(vertexKeyMap)
  
load(recipeInput.vertices()).asVertices {
    labelField "~label"
//    key "~id", "id"
    key 'name'
}

load(recipeInput.edges()).asEdges {
    labelField "~label"
    outV "outV", {
        labelField "~label"
//        key "~id", "id"
        key 'name'
    }
    inV "inV", {
        labelField "~label"
//        key "~id", "id"
        key 'name'
    }
}
