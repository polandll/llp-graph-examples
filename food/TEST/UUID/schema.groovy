// SCHEMA
// PROPERTIES
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().create()
schema.propertyKey('year').Int().create()
schema.propertyKey('ISBN').Text().create()
// VERTEX LABELS
schema.vertexLabel('author').properties('name','gender','nickname').ifNotExists().create()
schema.vertexLabel('book').properties('name','year').create()
// EDGE LABELS
schema.edgeLabel('authored').connection('author','book').ifNotExists().create()
// INDEXES
schema.vertexLabel('author').index('byName').secondary().by('name').add()
schema.vertexLabel('book').index('search').search().by('name').asString().by('year').add()

