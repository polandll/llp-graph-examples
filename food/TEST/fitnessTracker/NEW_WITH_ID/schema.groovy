system.graph('ftWithId').ifNotExists().create()
:remote config alias g ftWithId.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// PROPERTIES
schema.propertyKey('userId').Int().ifNotExists().single().create()
schema.propertyKey('mealId').Int().single().ifNotExists().create()
schema.propertyKey('itemId').Int().ifNotExists().single().create()
schema.propertyKey('name').Text().ifNotExists().single().create()
schema.propertyKey('gender').Text().single().ifNotExists().create()
schema.propertyKey('calGoal').Int().single().ifNotExists().create()
schema.propertyKey('macroGoal').Text().single().ifNotExists().create()
schema.propertyKey('servingAmt').Text().single().ifNotExists().create()
schema.propertyKey('macro').Text().single().ifNotExists().create()
schema.propertyKey('calories').Int().single().ifNotExists().create()
schema.propertyKey('type').Text().single().ifNotExists().create()
schema.propertyKey('mealDate').Date().single().ifNotExists().create()
schema.propertyKey('since').Text().single().ifNotExists().create()
schema.propertyKey('numServings').Int().single().ifNotExists().create()

// VERTEX LABELS
schema.vertexLabel('person').properties('userId', 'name', 'gender', 'calGoal', 'macroGoal').ifNotExists().create()
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
