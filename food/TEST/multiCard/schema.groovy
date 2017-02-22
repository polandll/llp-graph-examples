// SCHEMA
// PROPERTIES
schema.propertyKey('author').Text().single().create()
schema.propertyKey('city').Text().single().create()
schema.propertyKey('dateStart').Text().single().create()
schema.propertyKey('dateEnd').Text().single().create()
// VERTEX LABELS
schema.vertexLabel('author').properties('author').create()
schema.vertexLabel('city').properties('city').create()
// EDGE LABELS
schema.edgeLabel('livedIn').multiple().connection('author','city').create()
schema.edgeLabel('livedIn').properties('dateStart', 'dateEnd').add()
// INDEXES
schema.vertexLabel('author').index('byAuthor').materialized().by('author').add()
schema.vertexLabel('city').index('byCity').materialized().by('city').add()
schema.vertexLabel('author').index('byStartDate').outE('livedIn').by('dateStart').add()
