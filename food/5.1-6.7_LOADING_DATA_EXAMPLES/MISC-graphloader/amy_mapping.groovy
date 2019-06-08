//Configures the data loader to create the schema
config create_schema: true, load_new: true

//Defines the data input source (a file which is specified via command line arguments)
//source = File.file(inputfilename).type(inputfiletype)
source = File.json('amy2.json')

//Defines the mapping from input file to graph
personMapper = {
    label "person"
    key "name"
    property "social", "ssn"
    vertexProperty "clearance", {
        value "value"
    }
    vertexProperty "skills"
    edge "works", "worksFor", OUT, {
        vertex "org", {
            labelField "label"
            key "name", "name"
        }
        property "time", "time"
    }
    outE "supervisor", {
        vertex "name", {
            label "person"
            key "name"
        }
        property "since", "since"
    }
    outV "knows", {
        label "person"
        key "name"
    }
    outV "record", "achieved", {
        labelField "type"
    }
    //ignore "blablup"
}

//Specifies what data source to load using which mapper
load(source).asVertices(personMapper)

