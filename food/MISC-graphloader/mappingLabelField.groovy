config create_schema: true
config load_new: true
inputfile = File.text('/Users/lorinapoland/CLONES/graph-examples/food/MISC-graphloader/people.dat').delimiter("::").header('type','name','gender')


load(inputfile).asVertices{
	labelField "type"
	key "name"
}
