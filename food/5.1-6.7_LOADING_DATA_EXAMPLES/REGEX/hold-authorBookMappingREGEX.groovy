// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to REGEX before running
// Run runDGL.sh in /Users/lorinapoland/CLONES/dse-graph-loader

/* SAMPLE INPUT - uses tabs
author:
name:Julia Child	gender:F
book: 
name:Simca's Cuisine: 100 Classic French Recipes for Every Occasion	year:1972	ISBN:0-394-40152-2
authorBook: 
bname:Simca's Cuisine: 100 Classic French Recipes for Every Occasion	aname:Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true, load_vertex_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/REGEX/'
authorInput = File.text(inputfiledir + "authorREGEX.dat").regex("name:(.*)\\tgender:([MF])").header('name', 'gender')
bookInput = File.text(inputfiledir + "bookREGEX.dat").
	regex("name:(.*)\\tyear:([0-9]{4})\\tISBN:([0-9]{1}[-]{1}[0-9]{3}[-]{1}[0-9]{5}[-]{1}[0-9]{0,1})").
	header('name', 'year', 'ISBN')
authorBookInput = File.text(inputfiledir + "authorBookREGEX.dat").regex("bname:(.*)\\taname:(.*)").header('bname', 'aname')

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
