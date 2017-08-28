system.graph('newMultiMetaCSV').ifNotExists().create()
:remote config alias g newMultiMetaCSV.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// PROPERTY KEYS
schema.propertyKey('badge').Text().multiple().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('name').Text().single().create()
schema.propertyKey('since').Int().single().create()

// VERTEX LABELS
schema.vertexLabel('reviewer').properties('name', 'gender', 'badge').create()
schema.propertyKey('badge').properties('since').add()

// INDEXES
schema.vertexLabel('reviewer').index('byname').materialized().by('name').add()
