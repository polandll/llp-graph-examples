/** SAMPLE INPUT
name|gender|status|age
Jamie Oliver|M|alive|41
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/home/automaton/graph-examples/food/TEST/filter_map_flatmap/'
chefs = File.csv(inputfiledir + "filterData.csv").delimiter('|')
def chefsYoung = chefs.filter { it["age"].toInteger() <= 41 }
def chefsAlive = chefs.filter { it["status"] == "alive" }
def chefsDeceased = chefs.filter { it["status"] == "deceased" }
 
//Specifies what data source to load using which mapper (as defined inline)
load(chefsAlive).asVertices {
    label "chefAlive"
    key "name"
}

load(chefsDeceased).asVertices {
    label "chefDeceased"
    key "name"
}

load(chefsYoung).asVertices {
    label "chefYoung"
    key "name"
}
