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
//config create_schema: false, load_new: true
//config load_new: true, dryrun: false, preparation: true, create_schema: true, schema_output: '/tmp/loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/'
fridgeItemInput = File.csv(inputfiledir + "fridgeItem.csv").delimiter('|')
ingredInput = File.csv(inputfiledir + "ingredients.csv").delimiter('|')
the_edges = File.csv(inputfiledir + "fridgeItemEdges.csv").delimiter('|')

the_edges = the_edges.transform {
    it['FridgeSensor'] = [
            'city_id' : it['city_id'],
            'sensor_id' : it['sensor_id'] ];
    it['ingredient'] = [
	    'name' : it['name'] ];
    it
}

//Specifies what data source to load using which mapper (as defined inline)
  
load(ingredInput).asVertices {
label "ingredient"
    key "name"
}

load(fridgeItemInput).asVertices {
    label "FridgeSensor"
    key "city_id", "sensor_id"
}

load(the_edges).asEdges  {
    label "linked"
    outV "ingredient", {
        label "ingredient"
        key "name"
    }
    inV "FridgeSensor", {
        label "FridgeSensor"
        key "city_id", "sensor_id"
    }
}
