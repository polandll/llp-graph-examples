// SCHEMA
// PROPERTIES
schema.propertyKey('name').Text().single().create()
schema.propertyKey('comment').Text().single().create()
schema.propertyKey('stars').Int().single().create()
schema.propertyKey('timestamp').Timestamp().single().create()
// VERTEX LABELS
schema.vertexLabel('recipe').properties('name').create()
schema.vertexLabel('reviewer').properties('name').create()
// EDGE LABELS
schema.edgeLabel('rated').properties('comment', 'timestamp', 'stars').connection('reviewer', 'recipe').create()
// INDEXES
schema.vertexLabel('reviewer').index('byname').materialized().by('name').add()
schema.vertexLabel('recipe').index('byname').materialized().by('name').add()
