// PROPERTIES
schema.propertyKey('name').Text().single().create()
schema.propertyKey('gender').Text().single().create()
schema.propertyKey('calGoal').Text().single().create()
schema.propertyKey('macroGoal').Text().single().create()
schema.propertyKey('servingAmt').Text().single().create()
schema.propertyKey('macro').Text().single().create()
schema.propertyKey('calories').Text().single().create()
schema.propertyKey('since').Text().single().create()

// VERTEX LABELS
schema.vertexLabel('user').properties('name', 'gender', 'calGoal', 'macroGoal').create()
schema.vertexLabel('item').properties('name', 'servingAmt', 'macro', 'calories').create()

// EDGE LABELS
schema.edgeLabel('knows').properties('since').connection('user', 'user').create()

// INDEXES
schema.vertexLabel('user').index('byname').materialized().by('name').add()
schema.vertexLabel('item').index('byname').materialized().by('name').add()
