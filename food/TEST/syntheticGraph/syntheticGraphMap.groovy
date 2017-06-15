// 
// This DGL mapping script is used to test internal representation of vertices
// with a lot of edges.
// 
// It creates `n` vertices with `p` properties per vertex and `e` self-edges
// per vertex.
//
// It does not require any input file, as the generated data is 100% fake.
// 

// Mandatory parameters to be passed in the CLI
// -no_vertices 10 -no_properties_per_vertex 20 -no_edges_per_vertex 10000
noVertices = no_vertices.toInteger()
noPropertiesPerVertex = no_properties_per_vertex.toInteger()
noEdgesPerVertex = no_edges_per_vertex.toInteger()

// This produces vertices with no_properties_per_vertex + 1 properties:
// The `uid` which is the identifier, and `property$i` extra properties
vertexInput = Generator.of {
    theVertex = ["uid": it]
    for(int i=0; i<noPropertiesPerVertex; i++) {
        theVertex["property" + i] = "the value for the property " + i
    }
    theVertex
}.count(noVertices)

// This generator works in two parts:
// * The first part (from `Generator.of()` until `.count()`) creates a map with
//   three attribues. `out` and `in` are the incident vertices references, and
//   `edgesToCreate` is a list of integers from `1` to `no_edges_per_vertex`
// * The second part (starting at `flatMap`) splits each entry into multiple
//   edge maps. Each of those maps contain 3 attributes: `out`, `in`, and an
//   integer field.
//   
// This is essentially a way of writing a double for loop:
// `for each vertex v and for i between 1:no_edges_per_vertex, create an edge`
edgeInput = Generator.of {
    theEdge = ["out": it, "in": it, "edgesToCreate": []]
    for(int i=0; i<noEdgesPerVertex; i++) {
        theEdge["edgesToCreate"].add(i)
    }
    theEdge
}.count(noVertices).flatMap {
    outVertex = it["out"]
    inVertex = it["in"]
    it["edgesToCreate"].collect {
        edgeNumber=it
        it = ["out": outVertex, "in": inVertex, "number": edgeNumber]
    }
}

// The rest is the classic DGL `load` call
load(vertexInput).name("entity").asVertices {
    label "entity"
    key "uid"
}

load(edgeInput).name("entity_has_entity").asEdges {
    label "entity_has_entity"
    outV "out", {
        label "entity"
        key "uid"
    }
    inV "in", {
        label "entity"
        key "uid"
    }
}
