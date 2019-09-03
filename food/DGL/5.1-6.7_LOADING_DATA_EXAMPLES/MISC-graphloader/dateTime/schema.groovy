system.graph('testdateTime').create()
:remote config alias g testdateTime.g
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
schema.propertyKey('year').Date().ifNotExists().create()
schema.propertyKey('time').Time().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('name', 'gender').ifNotExists().create()
// EDGE LABELS
schema.edgeLabel('born').multiple().connection('person','person').ifNotExists().create()
schema.edgeLabel('born').properties('year','time').add()
// INDEXES
schema.vertexLabel('person').index('byName').materialized().by('name').add()
