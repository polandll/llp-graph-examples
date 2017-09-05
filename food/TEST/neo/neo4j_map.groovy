import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.structure.Graph

//Configures the data loader to create the schema
config create_schema: true

//Defines the data input source (a file which is specified via command line arguments)
//source = com.datastax.dsegraphloader.api.Graph.file("neo4j/")
source = com.datastax.dsegraphloader.api.Graph.file("/Users/lorinapoland/Documents/Neo4j/default.graphdb/")

//Specifies what data source to load using which mapper
load(source.vertices()).asVertices {
    labelField "~label"
    key "~id", "id"
}

load(source.edges()).asEdges {
    labelField "~label"
    outV "outV", {
        labelField "~label"
        key "~id", "id"
    }
    inV "inV", {
        labelField "~label"
        key "~id", "id"
    }
}
