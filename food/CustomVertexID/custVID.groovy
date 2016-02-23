// Custom vertex ID

graph.schema().buildPropertyKey('product', String.class).add()
graph.schema().buildPropertyKey('feature', Integer.class).add()

graph.schema().buildVertexLabel('Product').
	idComponent('product', IdPropertyKey.Type.Partition).
	idComponent('feature',IdPropertyKey.Type.Partition).
	add()
	

graph.addVertex(label, 'Product', 'product', 'DSE Graph', 'feature', 1)
g.V(['~label': 'Product', 'product':'DSE Graph', 'feature':1]).count()
--------
gremlin> :> system.createGraph('custVID').build()
==>custVID
gremlin> :remote config alias g custVID.g
==>g=custVID.g
gremlin> :> graph.schema().buildPropertyKey('product', String.class).add()
==>v[32767]
gremlin> :> graph.schema().buildPropertyKey('feature', Integer.class).add()
==>v[32768]
gremlin> :> graph.schema().buildVertexLabel('Product').idComponent('product', IdPropertyKey.Type.Partition).idComponent('feature',IdPropertyKey.Type.Partition).add()
==>v[1]
gremlin> :> graph.addVertex(label, 'Product', 'product', 'DSE Graph', 'feature', 1)
==>v[{product=DSE Graph, ~label=Product, feature=1}]
gremlin> :> g.V(['~label': 'Product', 'product':'DSE Graph', 'feature':1]).count()
==>1
gremlin> :> g.V().hasLabel('Product').valueMap()
==>[:]
gremlin> :> g.V().valueMap()
==>[:]
gremlin> :> graph.schema()
==>tinkergraph[vertices:4 edges:5]
gremlin> :> graph.schema().traversal().V().valueMap(true)
==>[mode:[Auto], id:0, label:schema]
==>[id:1, label:vertexLabel, name:[Product]]
==>[id:32768, label:propertyKey, dataType:[Int], name:[feature], cardinality:[Single]]
==>[id:32767, label:propertyKey, dataType:[Text], name:[product], cardinality:[Single]]
gremlin> :> graph.schema().traversal().V().valueMap()
==>[mode:[Auto]]
==>[name:[Product]]
==>[dataType:[Int], name:[feature], cardinality:[Single]]
==>[dataType:[Text], name:[product], cardinality:[Single]]