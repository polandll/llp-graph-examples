/* titan-schema.groovy
 *
 * Helper functions for declaring Titan schema elements
 * (vertex labels, edge labels, property keys) to accommodate
 * TP3 sample data.
 *
 * Sample usage in a gremlin.sh session:
 *
 * gremlin> :load data/GoT-titan-schema.groovy
 * ==>true
 * ==>true
 * gremlin> t = TitanFactory.open('conf/titan-cassandra.properties')
 * ==>standardtitangraph[cassandrathrift:[127.0.0.1]]
 * gremlin> defineGoTSchema(t)
 * ==>null
 * gremlin> t.close()
 * ==>null
 * gremlin>
 */

def defineGoTSchema(titanGraph) {
    m = titanGraph.openManagement()
    // vertex labels
    human = m.makeVertexLabel("human").make()
    dragon   = m.makeVertexLabel("dragon").make()
    // edge labels
    hatchedBy  = m.makeEdgeLabel("hatchedBy").make()
    killedBy = m.makeEdgeLabel("killedBy").make()
    relatedBy = m.makeEdgeLabel("relatedBy").make()
    // vertex and edge properties
    // blid         = m.makePropertyKey("bulkLoader.vertex.id").dataType(Long.class).make()
    name   = m.makePropertyKey("name").dataType(String.class).make()
    house  = m.makePropertyKey("house").dataType(String.class).make()
    gender = m.makePropertyKey("gender").dataType(String.class).make()
    origin = m.makePropertyKey("origin").dataType(String.class).make()
    //weight       = m.makePropertyKey("weight").dataType(Integer.class).make()
    // global indices
    //m.buildIndex("byBulkLoaderVertexId", Vertex.class).addKey(blid).buildCompositeIndex()
    m.buildIndex("humanByName", Vertex.class).addKey(name).indexOnly(human).buildCompositeIndex()
    m.buildIndex("dragonByName", Vertex.class).addKey(name).indexOnly(dragon).buildCompositeIndex()
    // vertex centric indices
    m.buildEdgeIndex(killedBy, "killedByName", Direction.BOTH, Order.decr, name)
    m.commit()
}
