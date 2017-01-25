// PROPERTY KEYS
schema.propertyKey('ISBN').Text().single().create()
schema.propertyKey('year').Text().single().create()
schema.propertyKey('name').Text().single().create()

// VERTEX LABELS
schema.vertexLabel('author').properties('name').create()
schema.vertexLabel('book').properties('name', 'year', 'ISBN').create()

// EDGE LABELS
schema.edgeLabel('authored').connection('author', 'book').create()

// INDEXES
schema.vertexLabel('author').index('byname').materialized().by('name').add()
schema.vertexLabel('book').index('byname').materialized().by('name').add()
