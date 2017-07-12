/** SAMPLE INPUT
See files in inputdir/vertices and inputdir/edges
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: false, load_new: true

// DATA INPUT
// Define the data input source using inputdir is the directory for the 
// input files that is specified in the config file

// *** REPLACE ALL vertices/ files with File.directory? ***
person = File.csv(inputdir + "vertices/" + "person.csv").delimiter(delimiter)
personCountry = File.csv(inputdir + "vertices/" + "personCountry.csv").delimiter(delimiter)
recipe = File.csv(inputdir + "vertices/" + "recipe.csv").delimiter(delimiter)
book = File.csv(inputdir + "vertices/" + "book.csv").delimiter(delimiter)
meal = File.csv(inputdir + "vertices/" + "meal.csv").delimiter(delimiter)
meal_item = File.csv(inputdir + "vertices/" + "meal_item.csv").delimiter(delimiter)
ingredient = File.csv(inputdir + "vertices/" + "ingredient.csv").delimiter(delimiter)
home = File.csv(inputdir + "vertices/" + "home.csv").delimiter(delimiter)
store = File.csv(inputdir + "vertices/" + "store.csv").delimiter(delimiter)
fridge_sensor = File.csv(inputdir + "vertices/" + "fridge_sensor.csv").delimiter(delimiter)
location = File.csv(inputdir + "vertices/" + "location.csv").delimiter(delimiter)
location_cartesian = File.csv(inputdir + "vertices/" + "location_cartesian.csv").delimiter(delimiter)

includes = File.csv(inputdir + "edges/" + "includes.csv").delimiter(delimiter)
knows = File.csv(inputdir + "edges/" + "knows.csv").delimiter(delimiter)
ate = File.csv(inputdir + "edges/" + "ate.csv").delimiter(delimiter)

//Specifies what data source to load using which mapper (as defined inline)
load(person).asVertices {
    label "person"
    key "personId"
}

load(personCountry).asVertices {
    label "person"
    key "personId"
    exists()
}

load(recipe).asVertices {
    label "recipe"
    key "recipeId"
}

load(book).asVertices {
    label "book"
    key "bookId"
}

load(meal).asVertices {
    label "meal"
    key "mealId"
}

load(meal_item).asVertices {
    label "meal_item"
    key "itemId"
}

load(ingredient).asVertices {
    label "ingredient"
    key "ingredId"
}

load(home).asVertices {
    label "home"
    key "homeId"
}

load(store).asVertices {
    label "store"
    key "storeId"
}

load(fridge_sensor).asVertices {
    label "fridge_sensor"
    key cityId: "cityId", sensorId: "sensorId"
}

load(location).asVertices {
    label "location"
    key "locId"
}


load(location_cartesian).asVertices {
    label "location"
    key "locId"
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
