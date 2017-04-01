/** SAMPLE INPUT
See user.csv, knows.csv, meals.csv
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/home/automaton/graph-examples/food/TEST/fitnessTracker/'
users = File.csv(inputfiledir + "users.csv").delimiter('|')
knows = File.csv(inputfiledir + "knows.csv").delimiter('|')
items = File.csv(inputfiledir + "items.csv").delimiter('|')
meals = File.csv(inputfiledir + "meals.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
load(users).asVertices {
    label "user"
    key "name"
}

load(items).asVertices {
    label "item"
    key "item"
}

load(meals).asVertices {
    label "meal"
    // The vertexLabel schema for meal includes two keys:
    // partition key: type and clustering key: mealDate 
    key type: "type", mealDate: "mealDate"
    ignore user, item, amount, macro
}

load(meals).asVertices {
    label "item"
    key "item"
    ignore user, type, mealDate
}

load(knows).asEdges {
    label "knows"
    outV "u1", {
        label "user"
        key "name"
    }
    inV "u2", {
        label "user"
        key "name"
    }
}
