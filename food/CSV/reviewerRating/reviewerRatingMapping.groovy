// Input reviewers and ratings and link to recipes
// Run runDGL.sh

/* SAMPLE INPUT
reviewer: John Doe
recipe: Beef Bourguignon
reviewerRating :John Doe|Beef Bourguignon|2014-01-01|5|comment
 */

// CONFIGURATION
// Configures the data loader to create the schema
config preparation: true, create_schema: true, load_new: false

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files that is given in the commandline
// as the "-filename" option
inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/reviewerRating/'
// This next file is not required if the reviewers already exist
reviewerInput = File.csv(inputfiledir + "reviewers.csv.gz").gzip().delimiter('|')
// This next file is not required if the recipes already exist
recipeInput = File.csv(inputfiledir +"recipes.csv.gz").gzip().delimiter('|')
// This is the file that is used to create the edges with edge properties
reviewerRatingInput = File.csv(inputfiledir + "reviewerRatings.csv.gz").gzip().delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)

load(reviewerInput).asVertices {
    label "reviewer"
    key "name"
}

load(recipeInput).asVertices {
    label "recipe"
    key "name"
}

load(reviewerRatingInput).asEdges {
    label "rated"
    outV "rev_name", {
        label "reviewer"
        key "name"
    }
    inV "recipe_name", {
        label "recipe"
        key "name"
    }
   // properties are automatically added from the file, using the header line as property keys
}
