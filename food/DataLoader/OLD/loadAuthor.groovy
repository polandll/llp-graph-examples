graph.load().vertex(doc, { com.datastax.bdp.graph.api.importer.VertexImport v ->
    // The documents describe "person" entities
    v.label("author"); 
    // Those "person" entities are uniquely identified by their "name" property
    v.key("name"); 
    //T he "social" field should be mapped to the existing "ssn" property key, by default 
    // "social" would be used as the property key
    //v.property("social", "ssn"); 
    //v.vertexProperty("clearance", { com.datastax.bdp.graph.api.importer.VertexPropertyImport vp ->
        //The "clearance" field describes a vertex-property (with multiple values or meta-properties). 
        // Vertex-properties need to be called out specifically
       // vp.value("value"); //The value of the vertex-property is given in the "value" field
    //});
    // "skills" is also a vertex-property which multiple values
    //v.vertexProperty("skills"); 
    v.edge("authored", "authored", Direction.OUT, { com.datastax.bdp.graph.api.importer.IncidentEdgeImport e ->
        //The "works" field describes a relationship with label "worksFor" in the OUT direction ...
        e.vertex("book", { com.datastax.bdp.graph.api.importer.NeighborVertexImport v2 ->
            // ... to an adjacent vertex described in the nested "org" field
            // ... with the label of that vertex (dynamically) defined in the "label" field
            // ... and which is uniquely identified by the (non-id) property key "name" 
            v2.labelField("label") 
              .key("bookTitle", "bookTitle"); 
        });
        //e.property("time", "time"); //Redundant
    });
    // The "supervisor" field describes a "supervisor" relationship
    v.outE("created", { com.datastax.bdp.graph.api.importer.IncidentEdgeImport e -> 
        e.vertex("recipe", { com.datastax.bdp.graph.api.importer.NeighborVertexImport v2 ->
            // to another vertex which is described in the "name" field
            // and is of label "person"
            // uniquely identified by its name which is stored in the field 
            //(i.e. not a nested document - just the value)
            v2.label("recipe"); 
            v2.isKey("recipeTitle"); 
        });
        //e.property("createDate", "createDate"); //Redundant
    });
    //v.outV("knows", { com.datastax.bdp.graph.api.importer.NeighborVertexImport v2 ->
        // The "knows" field describes a relationship with label "knows" where the edge 
        // is implicit (e.g. no edge properties) and only the neighbor is defined ...
        //v2.label("person") //which are other "person" vertices
         // .isKey("name"); //which are uniquely identified by their "name" property key
    //});
    // The "record" field describes "achieved" relationships to neighboring vertices
    //v.outV("record", "achieved", { com.datastax.bdp.graph.api.importer.VertexImport v2 -> 
        // ... which have label as defined in the "type" field. Not that no unique key is 
        // defined here which means a new vertex is created for each (nested) entry.
       // v2.labelField("type"); 
    //});
    // Ignore the "blablup" field. Otherwise we would create a property for each field 
    // not explicitly mentioned above
    v.ignore("blablup"); 
} as java.util.function.Consumer);
