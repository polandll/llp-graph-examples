config create_schema: true
config load_new: true
inputfile = File.csv('/Users/lorinapoland/CLONES/graph-examples/food/MISC-graphloader/people.csv').delimiter('|')


load(inputfile).asVertices{
	labelField "kind"
	key "name"
}
