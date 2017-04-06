// CONFIGURATION
// Configures the data loader to create the schema
config preparation: true, create_schema: false, load_new: true, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files

inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/allRecipe/'
authorInput = File.csv(inputfiledir + "vertices/author.csv").delimiter('|')
bookInput = File.csv(inputfiledir + "vertices/book.csv").delimiter('|')
ingredientInput = File.csv(inputfiledir + "vertices/ingredient.csv").delimiter('|')
mealInput = File.csv(inputfiledir + "vertices/meal.csv").delimiter('|')
recipeInput = File.csv(inputfiledir + "vertices/recipe.csv").delimiter('|')
reviewerInput = File.csv(inputfiledir + "vertices/reviewer.csv").delimiter('|')

authorBookInput = File.csv(inputfiledir + "authorBookAuthored.csv").delimiter('|')
authorRecipeInput = File.csv(inputfiledir + "authorRecipeCreated.csv").delimiter('|')
mealBookInput = File.csv(inputfiledir + "mealBookIncludedIn.csv").delimiter('|')
recipeBookInput = File.csv(inputfiledir + "recipeBookIncludedIn.csv").delimiter('|')
recipeIngredientInput = File.csv(inputfiledir + "recipeIngredientIncludes.csv ").delimiter('|')
recipeMealInput = File.csv(inputfiledir + "recipeMealIncludedIn.csv ").delimiter('|')    
reviewerRecipeInput = File.csv(inputfiledir + "reviewerRecipeRated.csv").delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)
  
load(authorInput).asVertices {
    label "author"
    key "name"
}

load(bookInput).asVertices {
    label "book"
    key "name"
}

load(ingredientInput).asVertices {
    label "ingredient"
    key "name"
}

load(mealInput).asVertices {
    label "meal"
    key "name"
}

load(recipeInput).asVertices {
    label "recipe"
    key "name"
}

load(reviewerInput).asVertices {
    label "reviewer"
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

load(authorRecipeInput).asEdges {
    label "created"
    outV "aname", {
        label "author"
        key "aname"
    }
    inV "rname", {
        label "recipe"
        key "rname"
    }
}

load(mealBookInput).asEdges {
    label "includedIn"
    outV "mname", {
        label "meal"
        key "name"
    }
    inV "bname", {
        label "book"
        key "name"
    }
}

load(recipeBookInput).asEdges {
    label "includedIn"
    outV "rname", {
        label "recipe"
        key "name"
    }
    inV "bname", {
        label "book"
        key "name"
    }
}

load(recipeIngredientInput).asEdges {
    label "includes"
    outV "rname", {
        label "recipe"
        key "name"
    }
    inV "iname", {
        label "ingredient"
        key "name"
    }
}

load(recipeMealInput).asEdges {
    label "includedIn"
    outV "rname", {
        label "recipe"
        key "name"
    }
    inV "mname", {
        label "meal"
        key "name"
    }
}

load(reviewerRecipeInput).asEdges {
    label "rated"
    outV "name", {
        label "reviewer"
        key "name"
    }
    inV "rname", {
        label "recipe"
        key "name"
    }
}
