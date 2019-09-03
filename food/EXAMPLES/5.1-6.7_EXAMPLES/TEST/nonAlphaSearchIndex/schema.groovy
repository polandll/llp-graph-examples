system.graph('nonAlphaSearch').create()
:remote config alias g nonAlphaSearch.g
schema.config().option('graph.allow_scan').set('true')

schema.propertyKey('name').Text().create()
schema.propertyKey('age').Int().create()
schema.vertexLabel('person').properties('name','age').create()
schema.vertexLabel('person').index('search').search().by('name').by('age').add()
