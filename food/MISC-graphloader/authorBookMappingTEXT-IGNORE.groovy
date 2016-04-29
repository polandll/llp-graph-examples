// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to TEXT before running
// Run runDGL.sh in /Users/lorinapoland/CLONES/dse-graph-loader

/** SAMPLE INPUT
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

//inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/TEXT/'
authorInput = File.text("author.dat").delimiter("|").header('name', 'gender','bname')
//bookInput = File.text("book.dat").delimiter("|").header('name', 'year', 'ISBN')
// authorBookInput = File.text(inputfiledir + "authorBook.dat").delimiter("|").header('bname', 'aname')

//Specifies what data source to load using which mapper (as defined inline)
  
load(authorInput).asVertices {
    label "book"
    key "bname"
    ignore "name"
    ignore "gender"
}

load(authorInput).asVertices {
    label "author"
    key "name"
    outV "book", "authored", {
	label "book"
	key "bname"
   }
}

