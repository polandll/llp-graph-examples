/* SAMPLE INPUT
author: Julia Child|F
book : Simca's Cuisine: 100 Classic French Recipes for Every Occasion|1972|0-394-40152-2
authorBook: Simca's Cuisine: 100 Classic French Recipes for Every Occasion|Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: true, load_new: true, load_vertex_threads: 3, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/Test/UUID/'
authorInput = File.csv(inputfiledir + "author.csv").delimiter('|')
bookInput = File.csv(inputfiledir + "book.csv").delimiter('|')
authorBookInput = File.csv(inputfiledir + "authorBook.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(authorInput).asVertices {
    label "author"
    key "name"
}

load(bookInput).asVertices {
    label "book"
    key "name"
}

load(authorBookInput).asEdges {
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
