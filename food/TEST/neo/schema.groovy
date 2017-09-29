system.graph('neoTest').ifNotExists().create()
:remote config alias g neoTest.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('born').Int().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('name','born').create()
// EDGE LABELS
schema.edgeLabel('MARRIED_TO').connection('person','person').create()
schema.edgeLabel('SIBLING_OF').connection('person','person').create()
// INDEXES
schema.vertexLabel('person').index('byName').secondary().by('name').add()
