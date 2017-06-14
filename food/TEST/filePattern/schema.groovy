//system.graph('testFilePatJSON').ifNotExists().create()
//:remote config alias g testFilePatJSON.g
system.graph('testFilePat').ifNotExists().create()
:remote config alias g testFilePat.g
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('id').Text().ifNotExists().create()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('id', 'name', 'gender').ifNotExists().create()
// INDEXES
schema.vertexLabel("person").index("byid").materialized().by("id").add()
