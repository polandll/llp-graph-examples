// SCHEMA
// PROPERTIES
schema.propertyKey('name').Text().create()
schema.propertyKey('dateStart').Date().create()
schema.propertyKey('dateEnd').Date().create()
// VERTEX LABELS
schema.vertexLabel('author').properties('name').create()
schema.vertexLabel('city').properties('name').create()
// EDGE LABELS
schema.edgeLabel('livedIn').multiple().connection('author','city').create()
schema.edgeLabel('livedIn').properties('dateStart', 'dateEnd').add()
// INDEXES
schema.vertexLabel('author').index('byAuthor').materialized().by('name').add()
schema.vertexLabel('city').index('byCity').materialized().by('name').add()
schema.vertexLabel('author').index('byStartDate').outE('livedIn').by('dateStart').add()
