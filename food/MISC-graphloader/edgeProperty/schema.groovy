// SCHEMA
schema.propertyKey('name').Text().single().ifNotExists().create()
schema.propertyKey('notes').Text().multiple().ifNotExists().create()
schema.vertexLabel('person').ifNotExists().create()
schema.edgeLabel('knows').multiple().properties('notes').ifNotExists().create()
schema.edgeLabel('knows').connection('person', 'person').add()
