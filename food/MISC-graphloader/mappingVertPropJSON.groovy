config create_schema: true
config load_new: true
inputfile = File.json('reviewer.json')

load(inputfile).asVertices{
	label "reviewer"
	key "name"
	vertexProperty "badge", {
		"value" "value"
	}
}
