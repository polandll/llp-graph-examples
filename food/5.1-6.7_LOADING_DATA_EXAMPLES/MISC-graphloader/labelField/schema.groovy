// PROPERTY KEYS
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('name').Text().single().create()

// VERTEX LABELS
schema.vertexLabel('author').properties('gender', 'name').create()
schema.vertexLabel('reviewer').properties('gender', 'name').create()

// INDEXES
schema.vertexLabel('author').index('byname').materialized().by('name').add()
schema.vertexLabel('reviewer').index('byname').materialized().by('name').add()
