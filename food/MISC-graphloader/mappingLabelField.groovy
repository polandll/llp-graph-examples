config create_schema: true
config load_new: true
inputfile = File.text('people.dat').delimiter("::").header('type','name','gender')


load(inputfile).asVertices{
	labelField "type"
	key "name"
}
