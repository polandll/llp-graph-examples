// SCHEMA
// PROPERTIES
schema.propertyKey('id').Text().ifNotExists().create()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
schema.propertyKey('year').Date().create()
schema.propertyKey('time').Time().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('name', 'gender').ifNotExists().create()
// EDGE LABELS
schema.edgeLabel('born').connection('person','person').ifNotExists().create()
schema.edgeLabel('born').properties('year', 'time').add()
// INDEXES
schema.vertexLabel('person').index('byName').secondary().by('name').add()
