system.graph('flatmapTest').ifNotExists().create()
:remote config alias g flatmapTest.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('cuisine').Text().ifNotExists().create()
schema.propertyKey('recipeId').Int().ifNotExists().create()

schema.vertexLabel('recipe').properties('recipeId','name','cuisine').create()
schema.vertexLabel('recipe').index('byname').materialized().by('name').add()
schema.vertexLabel('recipe').index('byId').materialized().by('recipeId').add()
