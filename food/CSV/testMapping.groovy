inputfiledir = '/Users/lorinapoland/CLONES/graph-examples/food/CSV/'
recipeInput = File.csv(inputfiledir +"recipes.csv.gz").gzip().delimiter('|')

//Specifies what data source to load using which mapper (as defined inline)

load(recipeInput).asVertices {
    label "recipe"
    key "name"
    isNew
}
