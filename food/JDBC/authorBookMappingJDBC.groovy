// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to JDBC before running
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
config create_schema: true, load_new: true, load_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputDatabase = '~/test'
db = Database.connection("jdbc:h2:" + inputDatabase).H2().user("sa")

// Define multiple data inputs from the database source via SQL queries
authorInput = db.query "SELECT * FROM AUTHOR";
bookInput = db.query "SELECT * FROM BOOK";
authorBookInput = db.query "SELECT * FROM AUTHORBOOK";

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