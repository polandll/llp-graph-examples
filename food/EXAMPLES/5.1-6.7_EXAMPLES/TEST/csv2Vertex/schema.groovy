system.graph('testCsv2Vertex').ifNotExists().create()
:remote config alias g testCsv2Vertex.g
schema.config().option('graph.allow_scan').set('true')

schema.propertyKey('name').Text().single().create()
schema.propertyKey('gender').Text().single().create()
schema.vertexLabel('chef').properties('name','gender').create()
schema.vertexLabel('chef').index('byName').materialized().by('name').add()
