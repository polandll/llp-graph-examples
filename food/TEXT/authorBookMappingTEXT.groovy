// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script

/** SAMPLE INPUT
author: Julia Child::F
book : Simca's Cuisine: 100 Classic French Recipes for Every Occasion::1972::0-394-40152-2
authorBook: Simca's Cuisine: 100 Classic French Recipes for Every Occasion::Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_threads: 3

// DATA INPUT
// Define the data input source 
// inputfilename is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/TEXT/'
authorFile = text name: inputfiledir + "author.dat", delimiter: "|", header: ['name', 'gender']
bookFile = text name: inputfiledir + "book.dat", delimiter: "|", header: ['name', 'year', 'ISBN']
authorBookFile = text name: inputfiledir + "authorBook.dat", delimiter: "|", header: ['bname', 'aname']

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
