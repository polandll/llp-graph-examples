//system.graph('testJSON').ifNotExists().create()
//:remote config alias g testJSON.g
//schema.clear()
//schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('nickname').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
schema.propertyKey('year').Int().ifNotExists().create()
schema.propertyKey('ISBN').Text().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('author').properties('name','gender','nickname').ifNotExists().create()
schema.vertexLabel('book').properties('name','year', 'ISBN').ifNotExists().create()
// EDGE LABELS
schema.edgeLabel('authored').connection('author','book').ifNotExists().create()
// INDEXES
schema.vertexLabel('book').index('byname').materialized().by('name').add()
schema.vertexLabel('author').index('byname').materialized().by('name').add()
schema.vertexLabel('book').index('search').search().by('name').asString().by('year').add()
