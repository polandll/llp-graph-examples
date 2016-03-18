// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to CSV before running

/* SAMPLE INPUT
author: Julia Child|F
book : Simca's Cuisine: 100 Classic French Recipes for Every Occasion|1972|0-394-40152-2
authorBook: Simca's Cuisine: 100 Classic French Recipes for Every Occasion|Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/'
authorFile = csv name: inputfiledir + "author.csv", delimiter: "|"
bookFile = csv name: inputfiledir + "book.csv", delimiter: "|"
authorBookFile = csv name: inputfiledir + "authorBook.csv", delimiter: "|"

//Specifies what data source to load using which mapper (as defined inline)
load from: authorFile, vertex: {
    label "author"
    key "name"
    isNew
}

load from: bookFile, vertex: {
    label "book"
    key "name"
    isNew
}

load from: authorBookFile, edge: {
    label "authored"
    outV "aname", {
        label "author"
        isKey "name"
        exists
    }
    inV "bname", {
        label "book"
        isKey "name"
        exists
    }
}
