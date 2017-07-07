/** SAMPLE INPUT
See files in inputdir/vertices and inputdir/edges
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source using inputdir is the directory for the 
// input files that is specified in the config file

users = File.csv(inputdir + "vertices/" + "users.csv").delimiter(delimiter)
items = File.csv(inputdir + "vertices/" + "items.csv").delimiter(delimiter)
meals = File.csv(inputdir + "vertices/" + "meals.csv").delimiter(delimiter)
includes = File.csv(inputdir + "edges/" + "includes.csv").delimiter(delimiter)
knows = File.csv(inputdir + "edges/" + "knows.csv").delimiter(delimiter)
ate = File.csv(inputdir + "edges/" + "ate.csv").delimiter(delimiter)

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
