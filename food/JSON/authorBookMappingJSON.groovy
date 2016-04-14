// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to JSON before running
// Run runDGL.sh in /Users/lorinapoland/CLONES/dse-graph-loader

/* SAMPLE INPUT
author: {"author_name":"Julia Child","gender":"F"}
book : {"name":"The Art of French Cooking, Vol. 1","year":"1961","ISBN":"none"}
authorBook: {"name":"The Art of French Cooking, Vol. 1","author":"Julia Child"}
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true, load_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/JSON/'
authorInput = File.json(inputfiledir + 'author.json')
bookInput = File.json(inputfiledir + 'book.json')
authorBookInput = File.json(inputfiledir + 'authorBook.json')

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
