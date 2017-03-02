// CONFIGURATION
config create_schema: true, load_new: false, load_vertex_threads: 0, load_edge_threads: 0

// Graph Schema

/*
schema.propertyKey('field1').Text().ifNotExists().create()
schema.propertyKey('field2').Text().ifNotExists().create()
schema.vertexLabel('node1').properties('field1','field2').ifNotExists().create()
schema.vertexLabel('node2').properties('field1','field2').ifNotExists().create()
schema.vertexLabel('node3').properties('field1','field2').ifNotExists().create()
schema.vertexLabel('node4').properties('field1','field2').ifNotExists().create()
schema.vertexLabel('node1').index('node1Idx').materialized().by('field1').ifNotExists().add()
schema.vertexLabel('node2').index('node1Idx').materialized().by('field1').ifNotExists().add()
schema.vertexLabel('node3').index('node1Idx').materialized().by('field1').ifNotExists().add()
schema.vertexLabel('node4').index('node1Idx').materialized().by('field1').ifNotExists().add()
*/


// DATA INPUT
inputfiledirVertex = 'files/'

// tab-delimted data without header line
dataInput1 = File.csv(inputfiledirVertex + "data.txt").delimiter("\t").header('field1','field2')
dataInput2 = File.text(inputfiledirVertex + "data.txt").delimiter("\t").header('field1','field2')

// tab-delimted data with header line
dataInput3 = File.csv(inputfiledirVertex + "data-with-header.txt").delimiter("\t").header('field1','field2')
dataInput4 = File.csv(inputfiledirVertex + "data-with-header.txt").delimiter("\t")

// (1) CSV with header clause => data is loaded correctly
load(dataInput1).asVertices {
    label "node1"
    key "field1"
}

/* 
// (2) TEXT with tab delimiter => does not work 
load(dataInput2).asVertices {
    label "node2"
    key "field1"
}
*/

// (3) CSV with header clause & header line => header line is loaded as data
load(dataInput3).asVertices {
    label "node3"
    key "field1"
}

// (4) CSV with no header clause & header line in data => data is loaded correctly (header line is not loaded as data)
load(dataInput4).asVertices {
    label "node4"
    key "field1"
}
