// Startup steps for:
// - connecting to Gremlin Server
// - dropping a graph
// - creating a graph
// - configuring a graph traversal

:remote connect tinkerpop.server resources/graph/gremlin-console/conf/remote-objects.yaml

OLD SCHEMA:
:> system.dropGraph('test')
:> system.createGraph('test').ifNotExist().build()
:remote config alias g test.g

:> system.dropGraph('food')
:> system.createGraph('food').ifNotExist().build()
:remote config alias g food.g

// dropping aliases
 :remote config alias reset
 
 ---------------
 NEW SCHEMA:
system.graph('test').drop()
:> system.graph('test').ifNotExist().create()
:remote config alias g test.g
