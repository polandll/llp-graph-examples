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
fridge_sensor = File.csv(inputdir + "vertices/" + "fridge_sensor.csv").delimiter(delimiter)
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
isLocatedAt_fridge_sensor = File.csv(inputdir + "edges/" + "isLocatedAt_fridge_sensor.csv").delimiter(delimiter)
//dse5.1.2 and earlier: isLocatedAt_fridge_sensor = File.json(inputdir + "edges/" + "isLocatedAt_fridge_sensor.json")
isLocatedAt_home = File.csv(inputdir + "edges/" + "isLocatedAt_home.csv").delimiter(delimiter)
isLocatedAt_store = File.csv(inputdir + "edges/" + "isLocatedAt_store.csv").delimiter(delimiter)
isStockedWith = File.csv(inputdir + "edges/" + "isStockedWith.csv").delimiter(delimiter)
knows = File.csv(inputdir + "edges/" + "knows.csv").delimiter(delimiter)
reviewed = File.csv(inputdir + "edges/" + "reviewed.csv").delimiter(delimiter)

//Specifies what data source to load using which mapper (as defined inline)
load(person).asVertices {
    label "person"
    key "person_id"
    property "CALORIES", "calGoal"
}

personCountry = personCountry.transform {
  country1 = [
    "value": it.remove("value"),
    "start_year": it.remove("start_year"),
    "end_year": it.remove("end_year") ]
  it["country"] = [country1]
  it
}

load(personCountry).asVertices {
    label "person"
    key "person_id"
    vertexProperty "country", {
      value "value"
   }
   exists()
}

load(recipe).asVertices {
    label "recipe"
    key "recipe_id"
}

load(book).asVertices {
    label "book"
    key "book_id"
}

load(meal).asVertices {
    label "meal"
    key type: "type", meal_id: "meal_id"
}

load(meal_item).asVertices {
    label "meal_item"
    key "item_id"
}

load(ingredient).asVertices {
    label "ingredient"
    key "ingred_id"
}

load(home).asVertices {
    label "home"
    key "home_id"
}

load(store).asVertices {
    label "store"
    key "store_id"
}

load(fridge_sensor).asVertices {
    label "fridge_sensor"
    key state_id: "state_id", city_id: "city_id", sensor_id: "sensor_id"
}

load(location).asVertices {
    label "location"
    key "loc_id"
}

import com.datastax.driver.dse.geometry.Point
location = location.transform {
  it['geoPoint'] = Point.fromWellKnownText(it['geoPoint']);
}

/*
load(location_cartesian).asVertices {
    label "location"
    key "loc_id"
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
        key "person_id"
	ignore "type"
	ignore "meal_id"
	ignore "meal_date"
    }
    inV {
        exists()
        label "meal"
        key type: "type", meal_id: "meal_id"
	ignore "person_id"
	ignore "meal_date"
    }
    ignore "person_id"
    ignore "type"
    ignore "meal_id"
}

load(authored).asEdges {
    label "authored"
    outV {
        exists()
        label "person"
        key "person_id"
	ignore "book_id"
    }
    inV {
        exists()
        label "book"
        key "book_id"
	ignore "person_id"
    }
    ignore "person_id"
    ignore "book_id"
}

/* METHOD FOR JSON IN DSE 5.1.2 and previous
load(contains).asEdges {
    label "contains"
    outV "sensor", {
        label "fridge_sensor"
        key city_id: "city_id", sensor_id: "sensor_id"
        exists()
    }
    inV "ingred_id", {
        label "ingredient"
        key "ingred_id"
        exists()
    }
}*/

load(contains).asEdges {
    label "contains"
    outV {
        exists()
        label "fridge_sensor"
        key state_id: "state_id" , city_id: "city_id", sensor_id: "sensor_id"
        ignore "ingred_id"
        ignore "expire_date"
    }
    inV {
        exists()
        label "ingredient"
        key "ingred_id"
	ignore "state_id"
        ignore "city_id"
	ignore "sensor_id"
        ignore "expire_date"
    }
    ignore "state_id"
    ignore "city_id"
    ignore "sensor_id"
    ignore "ingred_id"
}

load(created).asEdges {
    label "created"
    outV {
        exists()
        label "person"
        key "person_id"
	ignore "recipe_id"
	ignore "create_date"
    }
    inV {
        exists()
        label "recipe"
        key "recipe_id"
	ignore "person_id"
	ignore "create_date"
    }
    ignore "person_id"
    ignore "recipe_id"
}

load(includedIn_ingredient_recipe).asEdges {
    label "includedIn"
    outV {
        exists()
        label "ingredient"
        key "ingred_id"
	ignore "recipe_id"
	ignore "amount"
    }
    inV {
        exists()
        label "recipe"
        key "recipe_id"
	ignore "ingred_id"
	ignore "amount"
    }
    ignore "recipe_id"
    ignore "ingred_id"
}

load(includedIn_meal_book).asEdges {
    label "includedIn"
    outV {
        exists()
        label "meal"
        key type: "type", meal_id: "meal_id"
 	ignore "book_id"
    }
    inV {
        exists()
        label "book"
        key "book_id"
	ignore "meal_id"
	ignore "type"
    }
    ignore "book_id"
    ignore "type"
    ignore "meal_id"
}

load(includedIn_recipe_book).asEdges {
    label "includedIn"
    outV {
        exists()
        label "recipe"
        key "recipe_id"
	ignore "book_id"
    }
    inV {
        exists()
        label "book"
        key "book_id"
	ignore "recipe_id"
    }
    ignore "recipe_id"
    ignore "book_id"
}

load(includedIn_recipe_meal).asEdges {
    label "includedIn"
    outV {
        exists()
        label "recipe"
        key "recipe_id"
	ignore "type"
	ignore "meal_id"
    }
    inV {
        exists()
        label "meal"
        key type: "type", meal_id: "meal_id"
	ignore "recipe_id"
    }
    ignore "type"
    ignore "meal_id"
    ignore "recipe_id"
}

load(includes).asEdges {
    label "includes"
    outV {
        exists()
        label "meal"
        key type: "type", meal_id: "meal_id"
	ignore "item_id"
	ignore "num_serv'g"
    }
    inV {
        exists()
        label "meal_item"
        key "item_id"
	ignore "type"
	ignore "meal_id"
	ignore "num_serv'g"
    }
    ignore "type"
    ignore "meal_id"
    ignore "item_id"
}

/* METHOD FOR JSON IN DSE 5.1.2 and previous
load(isLocatedAt_fridge_sensor).asEdges {
    label "isLocatedAt"
    outV "sensor", {
        label "fridge_sensor"
        key city_id: "city_id", sensor_id: "sensor_id"
        exists()
    }
    inV "home_id", {
        label "home"
        key "home_id"
        exists()
    }
} */

load(isLocatedAt_fridge_sensor).asEdges {
    label "isLocatedAt"
    outV {
        exists()
        label "fridge_sensor"
        key state_id: "state_id", city_id: "city_id", sensor_id: "sensor_id"
        ignore "home_id"
    }
    inV {
        exists()
        label "home"
        key "home_id"
        ignore "state_id"
        ignore "city_id"
        ignore "sensor_id"
    }
    ignore "state_id"
    ignore "city_id"
    ignore "sensor_id"
    ignore "home_id"
}

load(isLocatedAt_home).asEdges {
    label "isLocatedAt"
    outV {
        exists()
        label "home"
        key "home_id"
	ignore "loc_id"
    }
    inV {
        exists()
        label "location"
        key "loc_id"
	ignore "home_id"
    }
    ignore "home_id"
    ignore "loc_id"
}

load(isLocatedAt_store).asEdges {
    label "isLocatedAt"
    outV {
        exists()
        label "store"
        key "store_id"
	ignore "loc_id"
    }
    inV {
        exists()
        label "location"
        key "loc_id"
	ignore "store_id"
    }
    ignore "store_id"
    ignore "loc_id"
}

load(isStockedWith).asEdges {
    label "isStockedWith"
    outV {
        exists()
        label "store"
        key "store_id"
    	ignore "ingred_id"
	ignore "expire_date"
    }
    inV {
        exists()
        label "ingredient"
        key "ingred_id"
    	ignore "store_id"
	ignore "expire_date"
    }
    ignore "store_id"
    ignore "ingred_id"
}

load(knows).asEdges {
    label "knows"
    outV "u1", {
    	exists()
        label "person"
        key "person_id"
    }
    inV "u2", {
    	exists()
        label "person"
        key "person_id"
    }
    ignore "person_id"
}

load(reviewed).asEdges {
    label "reviewed"
    outV {
    	exists()
        label "person"
        key "person_id"
	ignore "recipe_id"
	ignore "stars"
	ignore "time"
	ignore "year"
	ignore "comment"
    }
    inV {
    	exists()
        label "recipe"
        key "recipe_id"
	ignore "person_id"
	ignore "stars"
	ignore "time"
	ignore "year"
	ignore "comment"
    }
    ignore "recipe_id"
    ignore "person_id"
}
