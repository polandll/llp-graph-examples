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

inputfiledir = '/home/automaton/graph-examples/food/TEST/fitnessTracker/NEW_WITH_ID/data/'
users = File.csv(inputfiledir + "vertices/" + "users.csv").delimiter('|')
items = File.csv(inputfiledir + "vertices/" + "items.csv").delimiter('|')
meals = File.csv(inputfiledir + "vertices/" + "meals.csv").delimiter('|')
includes = File.csv(inputfiledir + "edges/" + "includes.csv").delimiter('|')
knows = File.csv(inputfiledir + "edges/" + "knows.csv").delimiter('|')
ate = File.csv(inputfiledir + "edges/" + "ate.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
load(users).asVertices {
    label "person"
    key "userId"
}

load(items).asVertices {
    label "item"
    key "itemId"
}

load(meals).asVertices {
    label "meal"
    key "mealId"
}

load(includes).asEdges {
    label "includes"
    inV "itemId", {
	label "item"
        key "itemId"
	exists()
    }
    outV "mealId", {
	label "meal"
	key "mealId"
	exists()
    }
}

load(ate).asEdges {
    label "ate"
    inV "mealId", {
        label "meal"
        key "mealId"
        exists()
    }
    outV "userId", {
        label "person"
        key "userId"
        exists()
    }
}

load(knows).asEdges {
    label "knows"
    outV "u1", {
        label "person"
        key "userId"
    exists()
    }
    inV "u2", {
        label "person"
        key "userId"
    exists()
    }
}
