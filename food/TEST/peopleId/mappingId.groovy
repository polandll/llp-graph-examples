config create_schema: true
config load_new: true
inputfile = File.text('/Users/lorinapoland/CLONES/graph-examples/food/TEST/peopleId.dat').delimiter("|").header('type','name','~id','gender')


load(inputfile).asVertices{
	labelField "type"
	id "~id"
}
