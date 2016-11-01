config create_schema: true
config load_new: true
reviewerInput = File.text('/Users/lorinapoland/CLONES/graph-examples/food/TEST/review.dat').delimiter("::").header('name','id','comment')


load(reviewerInput).asVertices {
    label "reviewer"
    key "name"
//    isNew()
    exists()
}
