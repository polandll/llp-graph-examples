// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to CSV before running
// Run runDGL.sh in /Users/lorinapoland/CLONES/dse-graph-loader

/* SAMPLE INPUT
author: Julia Child|F
book : Simca's Cuisine: 100 Classic French Recipes for Every Occasion|1972|0-394-40152-2
authorBook: Simca's Cuisine: 100 Classic French Recipes for Every Occasion|Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true, load_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/'
authorFile = File.csv(inputfiledir + "author.csv").delimiter('|')
bookFile = File.csv(inputfiledir + "book.csv").delimiter('|')
authorBookFile = File.csv(inputfiledir + "authorBook.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(authorFile).asVertices {
    label "author"
    key "name"
}

load(bookFile).asVertices {
    label "book"
    key "name"
}

load(authorBookFile).asEdges {
    label "authored"
    outV "aname", {
        label "author"
        key "name"
    }
    inV "bname", {
        label "book"
        key "name"
    }
}