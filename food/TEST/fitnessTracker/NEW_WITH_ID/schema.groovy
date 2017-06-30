system.graph('ftWithId').ifNotExists().create()
:remote config alias g ftWithId.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// PROPERTIES
schema.propertyKey('userId').Int().single().create()
schema.propertyKey('mealId').Int().single().create()
schema.propertyKey('itemId').Int().single().create()
schema.propertyKey('name').Text().single().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('calGoal').Int().single().create()
schema.propertyKey('macroGoal').Text().single().create()
schema.propertyKey('servingAmt').Text().single().create()
schema.propertyKey('macro').Text().single().create()
schema.propertyKey('calories').Int().single().create()
schema.propertyKey('type').Text().single().create()
schema.propertyKey('mealDate').Date().single().create()
schema.propertyKey('since').Text().single().create()
schema.propertyKey('numServings').Int().single().create()

// VERTEX LABELS
schema.vertexLabel('person').ifNotExists().create()
schema.vertexLabel('person').properties('userId').add()
schema.vertexLabel('person').properties('name').add()
schema.vertexLabel('person').properties('gender').add()
schema.vertexLabel('person').properties('calGoal').add()
schema.vertexLabel('person').properties('macroGoal').add()
schema.vertexLabel('item').properties('itemId','name', 'servingAmt', 'macro', 'calories').ifNotExists().create()
schema.vertexLabel('meal').properties('mealId', 'type', 'mealDate').ifNotExists().create()

// EDGE LABELS
schema.edgeLabel("knows").multiple().properties("since").ifNotExists().create()
schema.edgeLabel("knows").connection("person", "person").add()
schema.edgeLabel("includes").multiple().properties("numServings").ifNotExists().create()
schema.edgeLabel("includes").connection("item", "meal").add()
schema.edgeLabel("ate").multiple().ifNotExists().create()
schema.edgeLabel("ate").connection("person", "meal").add()

// INDEXES
schema.vertexLabel('person').index('byname').materialized().by('name').add()
schema.vertexLabel('item').index('byname').materialized().by('name').add()
schema.vertexLabel('meal').index('bymealId').materialized().by('mealId').add()
schema.vertexLabel('person').index('byUserId').materialized().by('userId').add()
schema.vertexLabel('item').index('byItemId').materialized().by('itemId').add()
