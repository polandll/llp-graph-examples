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
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source (a database connection and SQL statements for data selection)
// inputDatabase is the database name
//inputDatabase = 'localhost/jdbcmysql'
db = Database.connection('jdbc:mysql://localhost/jdbcmysql').user('root').password('1234').MySQL()

// Define multiple data inputs from the database source via SQL queries
authorInput = db.query "select * from author";
bookInput = db.query "select * from book";
authorBookInput = db.query "select * from authorbook";

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
