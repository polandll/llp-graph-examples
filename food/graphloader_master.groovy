// CONFIGURATION

// Configures the data loader to create the schema
config create_schema: true

// load_new for no existing data 
config load_new: true

// load threads example
config load_threads: 3

// multiple config on single line
config load_new: true, dryrun: true, schema_output: /tmp/loader_output.txt

*********
dseGraphLoaderCSV
********

// SAMPLE INPUT
// A header line:
name|gender
// For the author.csv file: 
Julia Child|F
// For the book.csv file:
Simca's Cuisine: 100 Classic French Recipes for Every Occasion|1972|0-394-40152-2
// For the authorBook.csv file: 
Simca's Cuisine: 100 Classic French Recipes for Every Occasion|Simone Beck

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files

inputfiledir = '/tmp/CSV/'
authorInput = File.csv(inputfiledir + 'author.csv').delimiter('|')
bookInput = File.csv(inputfiledir + 'book.csv').delimiter('|')
authorBookInput = File.csv(inputfiledir + 'authorBook.csv').delimiter('|')

// file must include
// name|gender

// Run command
$ graphloader authorBookMappingCSV.groovy -graph testCSV -address localhost -dryrun true

/* SAMPLE INPUT
author: Julia Child|F
book : Simca's Cuisine: 100 Classic French Recipes for Every Occasion|1972|0-394-40152-2
authorBook: Simca's Cuisine: 100 Classic French Recipes for Every Occasion|Simone Beck
 */

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true, load_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files

inputfiledir = '/tmp/CSV/'
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

******
dseGraphLoaderJSON
*****

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

inputfiledir = '/tmp/JSON/'
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

******
dseGraphLoaderTEXT
*****

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

inputfiledir = '/tmp/CSV/'
authorInput = File.text(inputfiledir + "author.dat").delimiter("|").header('name', 'gender')
bookInput = File.text(inputfiledir + "book.dat").delimiter("|").header('name', 'year', 'ISBN')
authorBookInput = File.text(inputfiledir + "authorBook.dat").delimiter("|").header('bname', 'aname')

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

*****
dseGraphLoaderREGEX
*****

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
inputfiledir = '/tmp/REGEX/'
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

*****
dseGraphLoaderJDBC
*****

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
// Define the data input source (a database connection and SQL statements for data selection)
// inputDatabase is the database nameinputDatabase = '~/test'
db = Database.connection("jdbc:h2:" + inputDatabase).H2().user("sa")

// Define multiple data inputs from the database source via SQL queries
authorInput = db.query "SELECT * FROM AUTHOR";
bookInput = db.query "SELECT * FROM BOOK";
authorBookInput = db.query "SELECT * FROM AUTHORBOOK";

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

******
dseGraphLoaderMappingScriptBody
******

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

// ALTERNATIVE

authorMapper = {
    label "author"
    key "name"}
bookMapper = {
    label "book"
    key "name"
}
authorBookMapper = {
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

load(authorInput).asVertices(authorMapper)
load(bookInput).asVertices(bookMapper)
load(authorBookInput).asEdges(authorBookMapper)

// IGNORE FIELD
// authorInput includes name, gender, and restaurant
// but restaurant is not loaded

load(authorInput).asVertices {
    label "author"
    key "name"
    ignore "restaurant"
}

// ANOTHER IGNORE FIELD
// Sample lines from input file
// Julia Child|F|The French Chef Cookbook
Simone Beck|F|The Art of French Cooking, Vol. 1

//inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/TEXT/'
authorInput = File.text("author.dat").delimiter("|").header('name', 'gender','bname')

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

// labelField to parse input into different vertex labels

// personInput includes type of person, name, gender
// type can be either author or reviewer
// sample data in file people.dat: 
// author::Julia Child::F
// reviewer::Jane Doe::F

personInput = File.text('people.dat').delimiter("::").header('type','name','gender')


load(personInput).asVertices{
        labelField "type"
        key "name"
}

// OUTPUT FOR LAST EXAMPLE

gremlin> g.V().hasLabel('author').valueMap()
==>{gender=[F], name=[Julia Child]}
gremlin> g.V().hasLabel('reviewer').valueMap()
==>{gender=[F], name=[Jane Doe]}

// INSERTING META-PROPERTIES
schema.propertyKey('name').Text().single().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('badge').Text().multiple().create()
schema.propertyKey('since').Int().single().create()
schema.propertyKey('badge').properties('since').add()   // Create the meta-property since on the property badge
schema.vertexLabel('reviewer').properties('name','gender','badge').create()
schema.vertexLabel('reviewer').index('byname').materialized().by('name').add()

// personInput includes name, gender, badge type and date badge was issued
// reviewer.json:
// { "name":"Jane Doe", "gender":"M", "badge" : { "value": "Gold Badge", "since" : 2012 } }

inputfile = File.json('reviewer.json')

load(inputfile).asVertices {
   label "reviewer"
   key "name"
   vertexProperty "badge", {
      "value" "value"
   }
}

gremlin> g.V().valueMap()
==>{badge=[Gold Badge], gender=[M], name=[Jane Doe]}
gremlin> g.V().properties('badge').valueMap()
==>{since=2012}

*****
dseGraphLoaderTransforms
*****

// transform column value
inputfiledir = '/tmp/TEXT/'
authorInput = File.text(inputfiledir + "author.dat").delimiter("|").header('name', 'gender')
authorInput = authorInput.transform { it['gender'] = it['gender'].toLowerCase(); it }

// ALTERNATIVE
inputfiledir = '/tmp/TEXT/'
authorInput = File.text(inputfiledir + "author.dat").delimiter("|").header('name', 'gender')
authorInput = authorInput.transform { it['gender'] = it['gender'].toLowerCase(); it }

*****
dseGraphLoaderReference
****

Option	Data type	Default	Description
-timeout	Integer	120000	Number of milliseconds until a connection times out.
-graph	String	 	The name of the graph to load into. REQUIRED
-address	String	 	The IP address (and port) of the DSE Graph instance to connect to. REQUIRED
-read_threads	Number	1	Number of threads to use for reading data from data input.
-load_threads	Number	1	Number of threads to use for loading data into the graph.
-dryrun	Boolean	false	Whether to only conduct a trial run to verify data integrity and schema consistency.
-preparation	Boolean	true	Whether to do a preparation run to analyze the data and update the schema, if necessary.
-create_schema	Boolean	false	Whether to update or create the schema for missing schema elements.
-schema_output	String	proposed_schema.txt	The name of the file to save the proposed schema in when executing a dry-run. Leave blank to disable.
-load_vertices_only	Boolean	false	Load only vertices and ignore edges and properties.
-batch-size	Number	100	Size of loading batches.
-queue-size	Number	10000	Data retrieval queue size.
-load_new	Boolean	false	Whether the vertices loaded are new and do not yet exist in the graph.
-driver_retry_attempts	Integer	3	Number of retry attempts. If greater than zero, requests will be resubmitted after some recoverable failures.
-driver_retry_delay	milliseconds	1000	Number of milliseconds between driver retries.
-abort_on_num_failures	Integer	100	Number of failures after which loading is aborted.
-filename	String	 	The file to load the vertex data from. REQUIRED if not defined in the mapping script.
-label	String	 	The label of the vertex to be populated with data. If left blank, the name of the input file is used as the vertex label name.
-compress	String	none	The compression of the file. Choices are none, gzip, and xzip.
-reporting_interval	Integer	1	Number of seconds between each progress report written to the log.
-abort_on_prep_errors	Boolean	true	Normally if errors occur in the preparation, or during the vertex insertion phase we abort, setting this to false will force the loader to continue up to the maximum number of allowed failures.
-load_failure_log	String	load_failures.txt	Name and location of the file where failed records will be stored.

-dryrun true

$ graphloader -help

$ graphloader mymapscript.groovy -filename /tmp/recipe/all.dat -graph test -address localhost -dryrun true 

$ graphloader ./scripts/csv2Vertex.groovy -filename MyUsers.csv -graph csvTest -label User -address 127.0.0.1

$ java -Xmx10g -jar dse-graph-loader.jar

// CONFIGURATION
// Configures the data loader to create the schema and set load_threads to 3
config load_new: true, load_threads: 3

