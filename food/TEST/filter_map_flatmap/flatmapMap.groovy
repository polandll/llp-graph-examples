/** SAMPLE INPUT
recipeId|name|cuisine
001|Beef Bourguignon|English::French
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/home/automaton/graph-examples/food/TEST/filter_map_flatmap/'
recipes = File.csv(inputfiledir + "flatmapData.csv").delimiter('|')
def recipesCuisine = recipes.flatMap {
  def recipeId = it["recipeId"];
  def name = it["name"];
  it["cuisine"].split("::").
  collect { it = [ 'recipeId': recipeId, 'name': name, 'cuisine': it ] }
}
//Specifies what data source to load using which mapper (as defined inline)
load(recipesCuisine).asVertices {
    label "recipe"
    key recipeId: "recipeId", cuisine: "cuisine"
}
