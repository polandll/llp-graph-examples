/** SAMPLE INPUT
name|gender|age
Jamie Oliver|M|41
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/home/automaton/graph-examples/food/TEST/filter_map_flatmap/'
chefs = File.csv(inputfiledir + "mapData.csv").delimiter('|')
chefInput = chefs.map { it['gender'] = it['gender'].toLowerCase(); it }

//Specifies what data source to load using which mapper (as defined inline)

load(chefInput).asVertices {
    label "chef"
    key "name"
}
