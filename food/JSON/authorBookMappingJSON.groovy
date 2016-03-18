// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script

/* SAMPLE INPUT
author: {"author_name":"Julia Child","gender":"F"}
book : {"name":"The Art of French Cooking, Vol. 1","year":"1961","ISBN":"none"}
authorBook: {"name":"The Art of French Cooking, Vol. 1","author":"Julia Child"}
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_threads: 3

// DATA INPUT
// Define the data input source 
// inputfiledir is the directory for the input files that is given in the command line
// as the "-filename" option

inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/JSON/'
authorFile = json name: inputfiledir + "author.json"
bookFile = json name: inputfiledir + "book.json"
authorBookFile = json name: inputfiledir + "authorBook.json"

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
    outV "author", {
        label "author"
        isKey "name"
        exists
    }
    inV "name", {
        label "book"
        isKey "name"
        exists
    }
}
