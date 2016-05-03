// Startup steps for:
// - connecting to Gremlin Server
// - dropping a graph
// - creating a graph
// - configuring a graph traversal

// creating a connection (not needed for default now)
:remote connect tinkerpop.server resources/graph/gremlin-console/conf/remote-objects.yaml

// dropping aliases
 :remote config alias reset
 
system.graph('test').drop()
system.graph('test').ifNotExist().create()
:remote config alias g test.g
