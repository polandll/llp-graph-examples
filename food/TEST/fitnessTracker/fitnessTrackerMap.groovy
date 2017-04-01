/** SAMPLE INPUT
See users.csv, knows.csv, items.csv, meals.csv
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option

inputfiledir = '/home/automaton/graph-examples/food/TEST/fitnessTracker/'
users = File.csv(inputfiledir + "users.csv").delimiter('|')
knows = File.csv(inputfiledir + "knows.csv").delimiter('|')
items = File.csv(inputfiledir + "items.csv").delimiter('|')
meals = File.csv(inputfiledir + "meals.csv").delimiter('|')
meals_users = File.csv(inputfiledir + "meals_users.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
load(users).asVertices {
    label "user"
    key "name"
}

load(items).asVertices {
    label "item"
    key "name"
}

load(meals).asVertices {
    label "meal"
    // The vertexLabel schema for meal includes two keys:
    // partition key: type and clustering key: mealDate 
    //key type: "type", mealDate: "mealDate"
    key "mealId"
    ignore "name"
    ignore "item"
    ignore "numServings"
}

load(meals).asEdges {
    label "includes"
    outV "item", {
	label "item"
	key "name"
        exists()
    }
    inV "mealId", {
	label "meal"
//	key type: "type", mealDate: "mealDate"
        key "mealId"
        exists()
    }
    ignore "name"
    ignore "type"
    ignore "mealDate"
}

load(meals_users).asEdges {
    label "ate"
    outV "name", {
        label "user"
        key "name"
        exists()
    }
    inV "mealId", {
        label "meal"
//      key type: "type", mealDate: "mealDate"
        key "mealId"
        exists()
    }
}

load(knows).asEdges {
    label "knows"
    outV "u1", {
        label "user"
        key "name"
	exists()
    }
    inV "u2", {
        label "user"
        key "name"
	exists()
    }
}
