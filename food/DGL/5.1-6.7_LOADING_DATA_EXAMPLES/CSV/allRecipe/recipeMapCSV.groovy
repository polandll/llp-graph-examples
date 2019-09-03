// CONFIGURATION
// Configures the data loader to create the schema
config preparation: true, create_schema: false, load_new: true, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files

inputfiledir = '/home/automaton/graph-examples/food/CSV/allRecipe/'
authorInput = File.csv(inputfiledir + "vertices/author.csv").delimiter('|')
bookInput = File.csv(inputfiledir + "vertices/book.csv").delimiter('|')
ingredientInput = File.csv(inputfiledir + "vertices/ingredient.csv").delimiter('|')
mealInput = File.csv(inputfiledir + "vertices/meal.csv").delimiter('|')
recipeInput = File.csv(inputfiledir + "vertices/recipe.csv").delimiter('|')
reviewerInput = File.csv(inputfiledir + "vertices/reviewer.csv").delimiter('|')

authorBookInput = File.csv(inputfiledir + "edges/authorBookAuthored.csv").delimiter('|').header('aname','bname')
authorRecipeInput = File.csv(inputfiledir + "edges/authorRecipeCreated.csv").delimiter('|').header('aname','rname')
mealBookInput = File.csv(inputfiledir + "edges/mealBookIncludedIn.csv").delimiter('|').header('mname','bname')
recipeBookInput = File.csv(inputfiledir + "edges/recipeBookIncludedIn.csv").delimiter('|').header('rname','bname')
recipeIngredientInput = File.csv(inputfiledir + "edges/recipeIngredientIncludes.csv").delimiter('|').header('rname','iname','amount')
recipeMealInput = File.csv(inputfiledir + "edges/recipeMealIncludedIn.csv").delimiter('|').header('rname','mname')
reviewerRecipeInput = File.csv(inputfiledir + "edges/reviewerRecipeRated.csv").delimiter('|').header('name','rname','stars','timestamp','comment')

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
        key "name"
    }
    inV "rname", {
        label "recipe"
        key "name"
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
