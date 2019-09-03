/* SAMPLE INPUT
authorCity: 
author|city|dateStart|dateEnd
i
Julia Child|Paris|1961-01-01|1967-02-10
 */

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: false, load_new: true, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
//inputfiledir = '/home/automaton/graph-examples/food/TEST/multiCard/'
//inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/Test/multiCard/'
inputfiledir = '/home/automaton/graph-examples/food/MISC-graphloader/multiCard/'
authorCityInput = File.csv(inputfiledir + "authorCity.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(authorCityInput).asVertices {
    label "author"
    key "author"
    ignore "city"
    ignore "dateStart"
    ignore "dateEnd"
}

load(authorCityInput).asVertices {
    label "city"
    key "city"
    ignore "author"
    ignore "dateStart"
    ignore "dateEnd"
}

load(authorCityInput).asEdges {
    label "livedIn"
    outV "author", {
        label "author"
        key "author"
    }
    inV "city", {
        label "city"
        key "city"
    }
}
