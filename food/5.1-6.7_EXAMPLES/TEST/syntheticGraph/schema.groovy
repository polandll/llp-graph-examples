system.graph("syntheticGraph").ifNotExists().create()
:remote config alias g syntheticGraph.g
schema.config().option('graph.schema_mode').set(com.datastax.bdp.graph.api.model.Schema.Mode.Development)

schema.propertyKey('uid').Bigint().create()
schema.propertyKey('property0').Text().create()
schema.propertyKey('property1').Text().create()
schema.propertyKey('property2').Text().create()
schema.propertyKey('property3').Text().create()
schema.propertyKey('property4').Text().create()
schema.propertyKey('property5').Text().create()
schema.propertyKey('property6').Text().create()
schema.propertyKey('property7').Text().create()
schema.propertyKey('property8').Text().create()
schema.propertyKey('property9').Text().create()
schema.propertyKey('property10').Text().create()
schema.propertyKey('property11').Text().create()
schema.propertyKey('property12').Text().create()
schema.propertyKey('property13').Text().create()
schema.propertyKey('property14').Text().create()
schema.propertyKey('property15').Text().create()
schema.propertyKey('property16').Text().create()
schema.propertyKey('property17').Text().create()
schema.propertyKey('property18').Text().create()
schema.propertyKey('property19').Text().create()
schema.propertyKey('number').Bigint().create()

schema.vertexLabel('entity').partitionKey('uid').properties(
    'property0', 'property1', 'property2', 'property3', 'property4',
    'property5', 'property6', 'property7', 'property8', 'property9',
    'property10', 'property11', 'property12', 'property13', 'property14',
    'property15', 'property16', 'property17', 'property18', 'property19').create()

schema.edgeLabel('entity_has_entity').multiple().properties('number').connection('entity', 'entity').create()
