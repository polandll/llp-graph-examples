config create_schema: false, load_new: true

//Defines the data input source (a file which is specified via command line arguments)
inputfilename='/Users/lorinapoland/CLONES/graph-examples/food/TEST/expand/people.json'
source = File.json(inputfilename)
        .expand {
            return [
		child["name"] = it["name"]
  		child["mother"] = ["name" : it["mother"]]
  		child["father"] = ["name" : it["father"]]

  		mother["name"] = it["name"]
  		mother["spouse"] = ["name" : it["father"]]
            ]
        }

//Defines the mapping from input file to graph
person = {
  label "person"
  key "name"

  outV "mother", {
    label "person"
    key "name"
  }

//Specifies what data source to load using which mapper
load(source).asEdges(person)
