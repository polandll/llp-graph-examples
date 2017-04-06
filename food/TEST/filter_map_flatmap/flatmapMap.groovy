/** SAMPLE INPUT
name|cuisine
Beef Bourguignon|English::French
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/home/automaton/graph-examples/food/TEST/filter_map_flatMap/'
recipes = File.csv(inputfiledir + "flatmapData.csv").delimiter('|')
def recipesCuisine = recipes.flatMap {
  it["cuisine"].split("::").
  collect { it = [ 'name': name, 'cuisine': it ] }
}
//Specifies what data source to load using which mapper (as defined inline)
load(recipesCuisine).asVertices {
    label "recipe"
    key "name"
}

//load(items).asVertices {
//    label "item"
//    key "name"
//}

//load(meals).asEdges {
//    label "includes"
//    outV "item", {
//	label "item"
//	key "name"
//        exists()
//    }
//    inV "mealId", {
//	label "meal"
//	key type: "type", mealDate: "mealDate"
//        key "mealId"
//        exists()
//    }
//    ignore "name"
//    ignore "type"
//    ignore "mealDate"
//}
