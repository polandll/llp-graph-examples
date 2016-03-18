// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script

/* SAMPLE INPUT
author:
name:Julia Child	gender:F
book: 
name:Simca's Cuisine: 100 Classic French Recipes for Every Occasion	year:1972	ISBN:0-394-40152-2
authorBook: 
bname:Simca's Cuisine: 100 Classic French Recipes for Every Occasion	aname:Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_threads: 3

// DATA INPUT
// Define the data input source 
// inputfiledir is the directory for the input files that is given in the command line
// as the "-filename" option

inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/REGEX/'
authorFile = text name: inputfiledir + "authorREGEX.dat", 
	regex: "name:(.*)\\sgender:([MF])", 
	header: ['name', 'gender']
bookFile = text name: inputfiledir + "bookREGEX.dat", 
	regex: "name:(.*)\\tyear:([0-9]{4})\\tISBN:([0-9]{1}[-]{1}[0-9]{3}[-]{1}[0-9]{5}[-]{1}[0-9]{0,1})", 
	header: ['name', 'year', 'ISBN']
authorBookFile = text name: inputfiledir + "authorBookREGEX.dat", 
	regex: "bname:(.*)\\taname:(.*)", 
	header: ['bname', 'aname']

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

