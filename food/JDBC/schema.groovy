system.graph('jdbcmysql').ifNotExists().create()
:remote config alias g jdbcmysql.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

schema.propertyKey('name').Text().create()
schema.propertyKey('gender').Text().create()
schema.propertyKey('year').Int().create()
schema.propertyKey('isbn').Text().create()

schema.vertexLabel('author').properties('name', 'gender').create()
schema.vertexLabel('book').properties('name','year','isbn').create()

schema.edgeLabel('authored').connection('author','book').ifNotExists().create()
