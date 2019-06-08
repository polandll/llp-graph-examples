gremlin> :remote config alias g test_type.g
==>g=test_type.g
gremlin> schema.describe()
==>schema.propertyKey("name").Text().single().create()
schema.propertyKey("value").Decimal().single().create()
schema.vertexLabel("type").properties("value", "name").create()
gremlin> g.V()
Could not find an index to answer query clause and graph.allow_scan is disabled: ((label = type))
Type ':help' or ':h' for help.
Display stack trace? [yN]n
gremlin> schema.config().option('graph.allow_scan').set('true')
==>null
gremlin> g.V()
==>v[{~label=type, community_id=1257857792, member_id=0}]
gremlin> g.V().valueMap()
==>{name=[decimal], value=[1.123456789]}
