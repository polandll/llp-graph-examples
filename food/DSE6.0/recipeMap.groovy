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
//dse5.1.2 and earlier: personCountry = File.json(inputdir + "vertices/" + "personCountry.json")
recipe = File.csv(inputdir + "vertices/" + "recipe.csv").delimiter(delimiter)
book = File.csv(inputdir + "vertices/" + "book.csv").delimiter(delimiter)
meal = File.csv(inputdir + "vertices/" + "meal.csv").delimiter(delimiter)
meal_item = File.csv(inputdir + "vertices/" + "meal_item.csv").delimiter(delimiter)
ingredient = File.csv(inputdir + "vertices/" + "ingredient.csv").delimiter(delimiter)
home = File.csv(inputdir + "vertices/" + "home.csv").delimiter(delimiter)
store = File.csv(inputdir + "vertices/" + "store.csv").delimiter(delimiter)
fridgeSensor = File.csv(inputdir + "vertices/" + "fridgeSensor.csv").delimiter(delimiter)
location = File.csv(inputdir + "vertices/" + "location.csv").delimiter(delimiter)
//location_cartesian = File.csv(inputdir + "vertices/" + "location_cartesian.csv").delimiter(delimiter)

// *** REPLACE ALL edges/ files with File.directory? ***
ate = File.csv(inputdir + "edges/" + "ate.csv").delimiter(delimiter)
authored = File.csv(inputdir + "edges/" + "authored.csv").delimiter(delimiter)
contains = File.csv(inputdir + "edges/" + "contains.csv").delimiter(delimiter)
//dse5.1.2 and earlier: contains = File.json(inputdir + "edges/" + "contains.json")
created = File.csv(inputdir + "edges/" + "created.csv").delimiter(delimiter)
includedIn_ingredient_recipe = File.csv(inputdir + "edges/" + "includedIn_ingredient_recipe.csv").delimiter(delimiter)
includedIn_meal_book = File.csv(inputdir + "edges/" + "includedIn_meal_book.csv").delimiter(delimiter)
includedIn_recipe_book = File.csv(inputdir + "edges/" + "includedIn_recipe_book.csv").delimiter(delimiter)
includedIn_recipe_meal = File.csv(inputdir + "edges/" + "includedIn_recipe_meal.csv").delimiter(delimiter)
includes = File.csv(inputdir + "edges/" + "includes.csv").delimiter(delimiter)
isLocatedAt_fridgeSensor = File.csv(inputdir + "edges/" + "isLocatedAt_fridgeSensor.csv").delimiter(delimiter)
//dse5.1.2 and earlier: isLocatedAt_fridgeSensor = File.json(inputdir + "edges/" + "isLocatedAt_fridgeSensor.json")
isLocatedAt_home = File.csv(inputdir + "edges/" + "isLocatedAt_home.csv").delimiter(delimiter)
isLocatedAt_store = File.csv(inputdir + "edges/" + "isLocatedAt_store.csv").delimiter(delimiter)
isStockedWith = File.csv(inputdir + "edges/" + "isStockedWith.csv").delimiter(delimiter)
knows = File.csv(inputdir + "edges/" + "knows.csv").delimiter(delimiter)
reviewed = File.csv(inputdir + "edges/" + "reviewed.csv").delimiter(delimiter)

//Specifies what data source to load using which mapper (as defined inline)
load(person).asVertices {
    label "person"
    key "personId"
    property "CALORIES", "calGoal"
}

personCountry = personCountry.transform {
  country1 = [
    "value": it.remove("value"),
    "startYear": it.remove("startYear"),
    "endYear": it.remove("endYear") ]
  it["country"] = [country1]
  it
}

load(personCountry).asVertices {
    label "person"
    key "personId"
    vertexProperty "country", {
      value "value"
   }
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
    key type: "type", mealId: "mealId"
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

load(fridgeSensor).asVertices {
    label "fridgeSensor"
    key stateId: "stateId", cityId: "cityId", zipcodeId: "zipcodeId", sensorId: "sensorId"
}

load(location).asVertices {
    label "location"
    key "locId"
}

import com.datastax.driver.dse.geometry.Point
location = location.transform {
  it['geoPoint'] = Point.fromWellKnownText(it['geoPoint']);
}

/*
load(location_cartesian).asVertices {
    label "location"
    key "locId"
}

import com.datastax.driver.dse.geometry.Point
location_cartesian = location_cartesian.transform {
  it['geoPoint'] = Point.fromWellKnownText(it['geoPoint']);
}
*/

load(ate).asEdges {
    label "ate"
    outV {
        exists()
        label "person"
        key "personId"
	ignore "type"
	ignore "mealId"
	ignore "mealDate"
    }
    inV {
        exists()
        label "meal"
        key type: "type", mealId: "mealId"
	ignore "personId"
	ignore "mealDate"
    }
    ignore "personId"
    ignore "type"
    ignore "mealId"
}

load(authored).asEdges {
    label "authored"
    outV {
        exists()
        label "person"
        key "personId"
	ignore "bookId"
    }
    inV {
        exists()
        label "book"
        key "bookId"
	ignore "personId"
    }
    ignore "personId"
    ignore "bookId"
}

/* METHOD FOR JSON IN DSE 5.1.2 and previous
load(contains).asEdges {
    label "contains"
    outV "sensor", {
        label "fridgeSensor"
        key cityId: "cityId", sensorId: "sensorId"
        exists()
    }
    inV "ingredId", {
        label "ingredient"
        key "ingredId"
        exists()
    }
}*/

load(contains).asEdges {
    label "contains"
    outV {
        exists()
        label "fridgeSensor"
        key stateId: "stateId" , cityId: "cityId", zipcodeId: "zipcodeId, sensorId: "sensorId"
        ignore "ingredId"
        ignore "expireDate"
    }
    inV {
        exists()
        label "ingredient"
        key "ingredId"
	ignore "stateId"
        ignore "cityId"
	ignore "sensorId"
        ignore "expireDate"
    }
    ignore "stateId"
    ignore "cityId"
    ignore "sensorId"
    ignore "ingredId"
}

load(created).asEdges {
    label "created"
    outV {
        exists()
        label "person"
        key "personId"
	ignore "recipeId"
	ignore "createDate"
    }
    inV {
        exists()
        label "recipe"
        key "recipeId"
	ignore "personId"
	ignore "createDate"
    }
    ignore "personId"
    ignore "recipeId"
}

load(includedIn_ingredient_recipe).asEdges {
    label "includedIn"
    outV {
        exists()
        label "ingredient"
        key "ingredId"
	ignore "recipeId"
	ignore "amount"
    }
    inV {
        exists()
        label "recipe"
        key "recipeId"
	ignore "ingredId"
	ignore "amount"
    }
    ignore "recipeId"
    ignore "ingredId"
}

load(includedIn_meal_book).asEdges {
    label "includedIn"
    outV {
        exists()
        label "meal"
        key type: "type", mealId: "mealId"
 	ignore "bookId"
    }
    inV {
        exists()
        label "book"
        key "bookId"
	ignore "mealId"
	ignore "type"
    }
    ignore "bookId"
    ignore "type"
    ignore "mealId"
}

load(includedIn_recipe_book).asEdges {
    label "includedIn"
    outV {
        exists()
        label "recipe"
        key "recipeId"
	ignore "bookId"
    }
    inV {
        exists()
        label "book"
        key "bookId"
	ignore "recipeId"
    }
    ignore "recipeId"
    ignore "bookId"
}

load(includedIn_recipe_meal).asEdges {
    label "includedIn"
    outV {
        exists()
        label "recipe"
        key "recipeId"
	ignore "type"
	ignore "mealId"
    }
    inV {
        exists()
        label "meal"
        key type: "type", mealId: "mealId"
	ignore "recipeId"
    }
    ignore "type"
    ignore "mealId"
    ignore "recipeId"
}

load(includes).asEdges {
    label "includes"
    outV {
        exists()
        label "meal"
        key type: "type", mealId: "mealId"
	ignore "itemId"
	ignore "numServ"
    }
    inV {
        exists()
        label "meal_item"
        key "itemId"
	ignore "type"
	ignore "mealId"
	ignore "numServ"
    }
    ignore "type"
    ignore "mealId"
    ignore "itemId"
}

/* METHOD FOR JSON IN DSE 5.1.2 and previous
load(isLocatedAt_fridgeSensor).asEdges {
    label "isLocatedAt"
    outV "sensor", {
        label "fridgeSensor"
        key cityId: "cityId", sensorId: "sensorId"
        exists()
    }
    inV "homeId", {
        label "home"
        key "homeId"
        exists()
    }
} */

load(isLocatedAt_fridgeSensor).asEdges {
    label "isLocatedAt"
    outV {
        exists()
        label "fridgeSensor"
        key stateId: "stateId", cityId: "cityId", zipcodeId: "zipcodeId", sensorId: "sensorId"
        ignore "homeId"
    }
    inV {
        exists()
        label "home"
        key "homeId"
        ignore "stateId"
        ignore "cityId"
        ignore "sensorId"
    }
    ignore "stateId"
    ignore "cityId"
    ignore "sensorId"
    ignore "homeId"
}

load(isLocatedAt_home).asEdges {
    label "isLocatedAt"
    outV {
        exists()
        label "home"
        key "homeId"
	ignore "locId"
    }
    inV {
        exists()
        label "location"
        key "locId"
	ignore "homeId"
    }
    ignore "homeId"
    ignore "locId"
}

load(isLocatedAt_store).asEdges {
    label "isLocatedAt"
    outV {
        exists()
        label "store"
        key "storeId"
	ignore "locId"
    }
    inV {
        exists()
        label "location"
        key "locId"
	ignore "storeId"
    }
    ignore "storeId"
    ignore "locId"
}

load(isStockedWith).asEdges {
    label "isStockedWith"
    outV {
        exists()
        label "store"
        key "storeId"
    	ignore "ingredId"
	ignore "expireDate"
    }
    inV {
        exists()
        label "ingredient"
        key "ingredId"
    	ignore "storeId"
	ignore "expireDate"
    }
    ignore "storeId"
    ignore "ingredId"
}

load(knows).asEdges {
    label "knows"
    outV "u1", {
    	exists()
        label "person"
        key "personId"
    }
    inV "u2", {
    	exists()
        label "person"
        key "personId"
    }
    ignore "personId"
}

load(reviewed).asEdges {
    label "reviewed"
    outV {
    	exists()
        label "person"
        key "personId"
	ignore "recipeId"
	ignore "stars"
	ignore "time"
	ignore "year"
	ignore "comment"
    }
    inV {
    	exists()
        label "recipe"
        key "recipeId"
	ignore "personId"
	ignore "stars"
	ignore "time"
	ignore "year"
	ignore "comment"
    }
    ignore "recipeId"
    ignore "personId"
}
