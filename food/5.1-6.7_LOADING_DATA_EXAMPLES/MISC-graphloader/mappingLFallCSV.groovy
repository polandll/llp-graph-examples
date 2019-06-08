// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true, load_vertex_threads: 3

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/MISC-graphloader/'
authorInput = File.csv(inputfiledir + 'author.csv').delimiter('|')
bookInput = File.csv(inputfiledir + 'book.csv').delimiter('|')
authorBookInput = File.csv(inputfiledir + 'authorBook.csv').delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(authorInput).asVertices {
    label 'author'
    key 'name'
}

load(bookInput).asVertices {
    label 'book'
    key 'name'
}

load(authorBookInput).asEdges {
    label 'authored'
    outV 'aname', {
        labelField 'akind'
        key 'name'
    }
    inV 'bname', {
        labelField 'bkind'
        key 'name'
    }
}
