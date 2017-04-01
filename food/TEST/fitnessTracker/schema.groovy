system.graph('testfitnessTracker').ifNotExists().create()
:remote config alias g testfitnessTracker.g
schema.clear()
schema.config().option('graph.allow_scan').set('true')

// PROPERTIES
schema.propertyKey('name').Text().single().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('calGoal').Text().single().create()
schema.propertyKey('macroGoal').Text().single().create()
schema.propertyKey('servingAmt').Text().single().create()
schema.propertyKey('macro').Text().single().create()
schema.propertyKey('calories').Text().single().create()
schema.propertyKey('type').Text().single().create()
schema.propertyKey('mealId').Int().single().create()
schema.propertyKey('mealDate').Date().single().create()
schema.propertyKey('since').Text().single().create()
schema.propertyKey('numServings').Int().single().create()

// VERTEX LABELS
schema.vertexLabel('user').properties('name', 'gender', 'calGoal', 'macroGoal').create()
schema.vertexLabel('item').properties('name', 'servingAmt', 'macro', 'calories').create()
schema.vertexLabel('meal').properties('mealId', 'type', 'mealDate').create()

// EDGE LABELS
schema.edgeLabel("knows").multiple().properties("since").create()
schema.edgeLabel("knows").connection("user", "user").add()
schema.edgeLabel("includes").multiple().properties("numServings").create()
schema.edgeLabel("includes").connection("item", "meal").add()
schema.edgeLabel("ate").multiple().create()
schema.edgeLabel("ate").connection("user", "meal").add()


// INDEXES
schema.vertexLabel('user').index('byname').materialized().by('name').add()
schema.vertexLabel('item').index('byname').materialized().by('name').add()
schema.vertexLabel('meal').index('bymealId').materialized().by('mealId').add()
