// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to CSV before running
// Add COMPKEY to the script and graph name
// Run runDGL.sh in /Users/lorinapoland/CLONES/dse-graph-loader

/* SAMPLE INPUT
city_id|sensor_id|fridgeItem
santaCruz|93c4ec9b-68ff-455e-8668-1056ebc3689f|asparagus
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/'
fridgeItemInput = File.csv(inputfiledir + "fridgeItem.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(fridgeItemInput).asVertices {
    label "FridgeSensor"
    key "city_id", "sensor_id"
}
