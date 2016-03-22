// Use /Users/lorinapoland/CLONES/graph-examples/DataLoader/runDGL.sh to run this script
// Set runDGL.sh parameters to CSV before running

/* SAMPLE INPUT
author: 
name: Julia Child gender: F
book : 
name: Simca's Cuisine: 100 Classic French Recipes for Every Occasion year:1972 ISBN: 0-394-40152-2
authorBook: 
bname: Simca's Cuisine: 100 Classic French Recipes for Every Occasion aname: Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_threads: 3

// DATA INPUT
// Define the data source as a database connection
inputDatabase = '~/test'
db = database connection: "jdbc:h2:" + inputDatabase, driver: "H2", user: "sa"

// Define multiple data inputs from the database source via SQL queries
authorInput = db.query "SELECT * FROM AUTHOR";
bookInput = db.query "SELECT * FROM BOOK";
authorBookInput = db.query "SELECT * FROM AUTHORBOOK";

//Specifies what data source to load using which mapper (as defined inline)
load from: authorInput, vertex: {
    label "author"
    key "name"
    isNew
}

load from: bookInput, vertex: {
    label "book"
    key "name"
    isNew
}

load from: authorBookInput, edge: {
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
