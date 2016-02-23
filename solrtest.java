/**
 * Copyright DataStax, Inc.
 *
 * Please see the included license file for details.
 */
package com.datastax.bdp.graph.index.functional;

import com.datastax.bdp.graph.api.schema.IndexOption;
import com.datastax.bdp.graph.api.schema.VertexIndex;
import com.datastax.bdp.graph.api.schema.VertexLabel;
import com.datastax.bdp.graph.example.GraphOfTheGodsFactory;
import com.datastax.bdp.graph.impl.DseGraphImpl;
import com.datastax.bdp.graph.impl.data.QueryUtils;
import com.datastax.bdp.search.solr.metrics.QueryMetrics;
import com.datastax.bdp.search.solr.ng.SolrTestRunner;
import com.datastax.bdp.test.ng.DseEmbeddedContainer;
import com.datastax.bdp.test.ng.DseTestRunner;
import com.datastax.bdp.test.ng.DseYamlBuilder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.datastax.bdp.graph.api.property.Text.token;
import static com.datastax.bdp.graph.api.property.Text.tokenPrefix;
import static com.datastax.bdp.graph.api.property.Text.tokenRegex;

import static com.datastax.bdp.graph.util.DsegAssert.assertCount;
import static com.datastax.bdp.search.solr.metrics.QueryMetrics.QueryPhase.EXECUTE;
import static org.apache.tinkerpop.gremlin.process.traversal.Order.decr;
import static org.apache.tinkerpop.gremlin.process.traversal.Order.incr;
import static org.junit.Assert.assertEquals;


public class SolrIndexIntegrationTest extends DseTestRunner {

    @BeforeClass
    public static void setupCluster() throws Exception {
        cleanupData();
        startNode(1, DseYamlBuilder.newInstance().withMemoryOnlyTables(), DseEmbeddedContainer.NodeType.SOLR_AND_GRAPH, false);
    }

    @AfterClass
    public static void destroyClusterAfter() throws Exception {
        destroyCluster();
    }

    @Test
    public void testSolrIndex() throws Exception {
        DseTestRunner.executeCodeOnNode(1, DseTestRunner.AbstractContext.class, (c) -> {
            DseGraphImpl graph = (DseGraphImpl)GraphOfTheGodsFactory.load();

            Vertex alcmene = graph.traversal().V().hasLabel("human").has("name", "alcmene").next();
            Vertex hercules = graph.traversal().V().hasLabel("demigod").has("name", "hercules").next();
            Vertex tyler = graph.addVertex(T.label, "human", "name", "mr tyler hobbs", "age", 30, "nick", "thobbs");
            hercules.addEdge("brother", tyler);
            graph.tx().commit();

            // IndexingOptions should only be applied to text fields
            try {
                graph.migration("bad_add_index", (schema) -> {
                    VertexLabel human = schema.vertexLabel("human");
                    human.buildVertexIndex("search").search().byPropertyKey("age", IndexOption.Text.FullText).add();
                });
                Assert.fail("Expected error");
            } catch (IllegalArgumentException exc) {
                // expected error
            }

            // IndexingOptions should only be used with Search indexes
            try {
                graph.migration("bad_add_index", (schema) -> {
                    VertexLabel human = schema.vertexLabel("human");
                    human.buildVertexIndex("search").secondary().byPropertyKey("age", IndexOption.Text.FullText).add();
                });
                Assert.fail("Expected error");
            } catch (IllegalArgumentException exc) {
                // expected error
            }

            // add a search index on age
            graph.migration("add_index", (schema) -> {
                VertexLabel human = schema.vertexLabel("human");
                human.buildVertexIndex("search").search().byPropertyKey("age").add();
            });
            graph.tx().commit();

            String testSolrCoreName = "test.human" + QueryUtils.PROPERTY_TABLE_SUFFIX;

            HttpSolrServer client = SolrTestRunner.getSolrClient(getHostForNode(1), testSolrCoreName);
            client.commit();

            assertCount(1, graph.traversal().V().hasLabel("human").has("age", P.gte(40)));
            assertEquals(1, QueryMetrics.getInstance("test.human_p").getRecordedLatencyCount(EXECUTE));

            assertCount(2, graph.traversal().V().hasLabel("human").has("age", P.gt(20)).order().by("age", decr).toList());
            assertCount(2, graph.traversal().V().hasLabel("human").has("age", P.gt(20)).order().by("age", incr).toList());
            assertEquals(3, QueryMetrics.getInstance(testSolrCoreName).getRecordedLatencyCount(EXECUTE));

            // add an index on name
            graph.migration("update_index", (schema) -> {
                VertexLabel human = schema.vertexLabel("human");
                human.vertexIndex("search").addPropertyKey("name");
                human.vertexIndex("search").addPropertyKey("nick", IndexOption.Text.String);
            });
            graph.tx().commit();
            client.commit();

            assertCount(1, graph.traversal().V().hasLabel("human").has("name", token("alcmene")));
            assertEquals(4, QueryMetrics.getInstance(testSolrCoreName).getRecordedLatencyCount(EXECUTE));

            assertEquals(alcmene, graph.traversal().V().hasLabel("human").has("age", P.gt(20)).order().by("age", decr).by("nick", incr).next());
            assertEquals(5, QueryMetrics.getInstance(testSolrCoreName).getRecordedLatencyCount(EXECUTE));

            assertEquals(alcmene, graph.traversal().V().hasLabel("human").has("age", P.gt(20)).has("name", token("alcmene")).order().by("age", decr).by("nick", incr).next());
            assertEquals(6, QueryMetrics.getInstance(testSolrCoreName).getRecordedLatencyCount(EXECUTE));

            assertCount(1, graph.traversal().V().hasLabel("human").has("name", token("tyler")));
            assertCount(0, graph.traversal().V().hasLabel("human").has("name", token("tyl")));
            assertCount(1, graph.traversal().V().hasLabel("human").has("name", tokenPrefix("tyl")));
            assertCount(1, graph.traversal().V().hasLabel("human").has("name", tokenRegex("[a-z]obbs")));
            assertEquals(10, QueryMetrics.getInstance("test.human_p").getRecordedLatencyCount(EXECUTE));

            assertCount(2, graph.traversal().V().hasLabel("human").has("age", P.within(45, 30)));
            assertEquals(11, QueryMetrics.getInstance("test.human_p").getRecordedLatencyCount(EXECUTE));

            // drop the index on name
            graph.migration("remove_name_index", (schema) -> {
                VertexLabel human = schema.vertexLabel("human");
                human.vertexIndex("search").removePropertyKey("name");
            });
            graph.tx().commit();
            client.commit();

            assertCount(1, graph.traversal().V().hasLabel("human").has("nick", P.eq("thobbs")));
            assertCount(0, graph.traversal().V().hasLabel("human").has("nick", P.eq("thobbzzz")));
            assertEquals(13, QueryMetrics.getInstance("test.human_p").getRecordedLatencyCount(EXECUTE));

            assertCount(2, graph.traversal().V().hasLabel("human").has("age", P.gt(20)).order().by("age", incr).toList());
            assertEquals(14, QueryMetrics.getInstance(testSolrCoreName).getRecordedLatencyCount(EXECUTE));

            // this should not use the index
            assertCount(1, graph.traversal().V().hasLabel("human").has("name", token("tyler")));
            assertEquals(14, QueryMetrics.getInstance(testSolrCoreName).getRecordedLatencyCount(EXECUTE));

            // drop the index on age
            graph.migration("remove_age_index", (schema) -> {
                VertexLabel human = schema.vertexLabel("human");
                human.vertexIndex("search").removePropertyKey("age");
            });
            graph.tx().commit();

            assertCount(2, graph.traversal().V().hasLabel("human").has("age", P.gt(20)).order().by("age", incr).toList());
            assertEquals(14, QueryMetrics.getInstance(testSolrCoreName).getRecordedLatencyCount(EXECUTE));
        }).get();
    }
}