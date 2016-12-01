import org.apache.commons.configuration.BaseConfiguration
import org.apache.commons.configuration.Configuration
import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph

//Configures the data loader to create the schema
config create_schema: true

//Defines the data input source (a file which is specified via command line arguments)
//Configuration config = new BaseConfiguration();
//config.setProperty(Neo4jGraph.CONFIG_DIRECTORY, "/Users/lorinapoland/Documents/Neo4j/default.graphdb/");
//config.<Boolean>setProperty(Neo4jGraph.CONFIG_META_PROPERTIES, true);
//config.<Boolean>setProperty(Neo4jGraph.CONFIG_MULTI_PROPERTIES, true);
//config.setProperty("gremlin.neo4j.conf.cache_type", "none");

//source = com.datastax.dsegraphloader.api.Graph.neo4jFromConfig(config)
source = com.datastax.dsegraphloader.api.Graph.file("/Users/lorinapoland/Documents/Neo4j/default.graphdb/")

//Specifies what data source to load using which mapper

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
