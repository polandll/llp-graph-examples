// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to CSV before running
// Add COMPKEY to the script and graph name
// Run runDGL.sh in /Users/lorinapoland/CLONES/dse-graph-loader

/* SAMPLE INPUT
city_id|sensor_id|item
SantaCruz|100|asparagus
Sacramento|256|ham
Sacramento|257|eggs
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true, load_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/'
fridgeItemInput = File.csv(inputfiledir + "fridgeItem.csv").delimiter('|')


//Specifies what data source to load using which mapper (as defined inline)
  
load(fridgeItemInput).asVertices {
    label "fridgeItem"
    // need a transform to get a composite, like Ben's script
    // key sourceName: schemaName, sourceName2:schemaName2
    key city_id:"city_id", sensor_id:"sensor_id"
}

----------
Text from doc that I yanked until I fix this:
Step in dseGraphLoaderTransforms.dita
Composite keys may be specified in a loading statement.
load(fridgeItemInputFile).asVertices {
    label "fridgeItem"
    key "city_id", "sensor_id"
}
