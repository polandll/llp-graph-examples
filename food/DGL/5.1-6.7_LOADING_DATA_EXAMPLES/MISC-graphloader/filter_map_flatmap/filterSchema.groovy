system.graph('filterTest').ifNotExists().create()
:remote config alias g filterTest.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
schema.propertyKey('status').Text().ifNotExists().create()
schema.propertyKey('age').Int().ifNotExists().create()

schema.vertexLabel('chefAlive').properties('name','gender','status','age').create()
schema.vertexLabel('chefAlive').index('byname').materialized().by('name').add()
schema.vertexLabel('chefDeceased').properties('name','gender','status','age').create()
schema.vertexLabel('chefDeceased').index('byname').materialized().by('name').add()
schema.vertexLabel('chefYoung').properties('name','gender','status','age').create()
schema.vertexLabel('chefYoung').index('byname').materialized().by('name').add()
