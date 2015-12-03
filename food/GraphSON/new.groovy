/**
 * Copyright DataStax, Inc.
 *
 * Please see the included license file for details.
 */
package com.datastax.bdp.graph.example;

import com.datastax.bdp.graph.api.DseGraph;
import com.datastax.bdp.graph.api.schema.Cardinality;
import com.datastax.bdp.graph.api.schema.EdgeLabel;
import com.datastax.bdp.graph.api.schema.PropertyKey;
import com.datastax.bdp.graph.api.schema.readWrite.RWVertexLabel;
import com.datastax.bdp.graph.impl.DseGraphFactoryImpl;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * This graph is for demonstration purposes only.
 */
public class FoodFactory
{

    public static DseGraph open()
    {
        Map config = new HashMap<>();
        config.put(DseGraph.NAME, "test");
        config.put(Graph.GRAPH, DseGraphFactoryImpl.class.getName());

        DseGraph graph = (DseGraph) GraphFactory.open(config);
        graph.migration("setup", (schema) -> {

            PropertyKey name = schema.buildPropertyKey("name", String.class).add();
            PropertyKey age = schema.buildPropertyKey("age", Integer.class).add();
            PropertyKey time = schema.buildPropertyKey("time", Instant.class).add();
            PropertyKey reason = schema.buildPropertyKey("reason", String.class).add();
            PropertyKey nicknames = schema.buildPropertyKey("nicknames", String.class).cardinality(Cardinality.Multiple).add();

            //TODO Geoshapes
            PropertyKey place = schema.buildPropertyKey("place", String.class).add();


            RWVertexLabel titan = schema.buildVertexLabel("titan").add();
            RWVertexLabel location = schema.buildVertexLabel("location").add();
            RWVertexLabel god = schema.buildVertexLabel("god").add();
            RWVertexLabel demigod = schema.buildVertexLabel("demigod").add();
            RWVertexLabel human = schema.buildVertexLabel("human").add();
            RWVertexLabel monster = schema.buildVertexLabel("monster").add();

            god.buildIndex("god").ofSecondary().byPropertyKey(name).add();
            god.buildIndex("age").ofSecondary().byPropertyKey(age).add();

            EdgeLabel father = schema.buildEdgeLabel("father").add();
            EdgeLabel mother = schema.buildEdgeLabel("mother").add();
            EdgeLabel battled = schema.buildEdgeLabel("battled").add();
            EdgeLabel lives = schema.buildEdgeLabel("lives").add();
            EdgeLabel pet = schema.buildEdgeLabel("pet").add();
            EdgeLabel brother = schema.buildEdgeLabel("brother").add();

            demigod.buildEdgeIndex("battledIndex", battled, Direction.OUT).byPropertyKey(time).add();
            titan.buildIndex("titanName").ofMaterializedView().byPropertyKey(name).add();
        });
        return graph;
    }

    public static DseGraph load()
    {

        return load(open());
    }



    public static DseGraph load(DseGraph graph)
    {
        Vertex saturn = graph.addVertex(T.label, "titan", "name", "saturn", "age", 10000);
        Vertex sky = graph.addVertex(T.label, "location", "name", "sky");
        Vertex sea = graph.addVertex(T.label, "location", "name", "sea");
        Vertex jupiter = graph.addVertex(T.label, "god", "name", "jupiter", "age", 5000);
        Vertex neptune = graph.addVertex(T.label, "god", "name", "neptune", "age", 4500);
        Vertex hercules = graph.addVertex(T.label, "demigod", "name", "hercules", "age", 30);

        Vertex alcmene = graph.addVertex(T.label, "human", "name", "alcmene", "age", 45);
        Vertex pluto = graph.addVertex(T.label, "god", "name", "pluto", "age", 4000);
        Vertex nemean = graph.addVertex(T.label, "monster", "name", "nemean");
        Vertex hydra = graph.addVertex(T.label, "monster", "name", "hydra");
        Vertex cerberus = graph.addVertex(T.label, "monster", "name", "cerberus");
        Vertex tartarus = graph.addVertex(T.label, "location", "name", "tartarus");

        jupiter.addEdge("father", saturn);
        jupiter.addEdge("lives", sky, "reason", "loves fresh breezes");
        jupiter.addEdge("brother", neptune);
        jupiter.addEdge("brother", pluto);

        neptune.addEdge("lives", sea).property("reason", "loves waves");
        neptune.addEdge("brother", jupiter);
        neptune.addEdge("brother", pluto);
        neptune.addEdge("battled", neptune).property("time", Instant.ofEpochMilli(5)); //self-edge

        neptune.property("nicknames","Neppy","time", Instant.ofEpochMilli(22));
        neptune.property("nicknames","Flipper","time", Instant.ofEpochMilli(25));

        hercules.addEdge("father", jupiter);
        hercules.addEdge("mother", alcmene);

        //TODO Geoshapes
        hercules.addEdge("battled", nemean, "time", Instant.ofEpochMilli(1), "place", "38.1f, 23.7f");
        hercules.addEdge("battled", hydra, "time", Instant.ofEpochMilli(2), "place", "37.7f, 23.9f");
        hercules.addEdge("battled", cerberus, "time", Instant.ofEpochMilli(12), "place", "39f, 22f");

        pluto.addEdge("brother", jupiter);
        pluto.addEdge("brother", neptune);
        pluto.addEdge("lives", tartarus, "reason", "no fear of death");
        pluto.addEdge("pet", cerberus);

        cerberus.addEdge("lives", tartarus);
        graph.tx().commit();
        return graph;
    }

    public static DseGraph remove() {
        return remove(open());
    }

    public static DseGraph remove(DseGraph graph) {
        graph.traversal().V().drop().iterate();
        graph.tx().commit();
        return graph;
    }


}
