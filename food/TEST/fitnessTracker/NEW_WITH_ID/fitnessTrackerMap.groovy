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
knows = File.csv(inputfiledir + "edges/" + "knows.csv").delimiter('|')
meals_users = File.csv(inputfiledir + "edges/" + "meals_users.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
load(users).asVertices {
    label "person"
    key userId: "userId", name: "name"
}

load(items).asVertices {
    label "item"
    key itemId: "itemId", name: "name"
}

load(meals).asVertices {
    label "meal"
    key mealId: "mealId", mealDate: "mealDate"
    ignore "userId"
    ignore "itemId"
    ignore "numServings"
}

load(meals).asEdges {
    label "includes"
    outV "item", {
	label "item"
        key itemId: "itemId", name: "name"
	exists()
    }
    inV "meal", {
	label "meal"
	key mealId: "mealId", mealDate: "mealDate"
	exists()
    }
    ignore "userId"
}
