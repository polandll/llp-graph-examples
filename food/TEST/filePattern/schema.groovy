// CSV
system.graph('testFilePatCSV').ifNotExists().create()
:remote config alias g testFilePatCSV.g
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('id').Text().ifNotExists().create()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('id', 'name', 'gender').ifNotExists().create()
// INDEXES
schema.vertexLabel("person").index("byid").materialized().by("id").add()
schema.vertexLabel("person").index("byname").materialized().by("name").add()

// JSON
system.graph('testFilePatJSON').ifNotExists().create()
:remote config alias g testFilePatJSON.g
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('id').Text().ifNotExists().create()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('id', 'name', 'gender').ifNotExists().create()
// INDEXES
schema.vertexLabel("person").index("byid").materialized().by("id").add()
schema.vertexLabel("person").index("byname").materialized().by("name").add()

// MATCH MULTIPLE FILENAME PATTERNS
system.graph('testFilePatMULT').ifNotExists().create()
:remote config alias g testFilePatMULT.g
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('id').Text().ifNotExists().create()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('id', 'name', 'gender').ifNotExists().create()
// INDEXES
schema.vertexLabel("person").index("byid").materialized().by("id").add()
schema.vertexLabel("person").index("byname").materialized().by("name").add()

// MATCH RANGE PATTERN
system.graph('testFilePatRANGE').ifNotExists().create()
:remote config alias g testFilePatRANGE.g
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('id').Text().ifNotExists().create()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('id', 'name', 'gender').ifNotExists().create()
// INDEXES
schema.vertexLabel("person").index("byid").materialized().by("id").add()
schema.vertexLabel("person").index("byname").materialized().by("name").add()

// QUESTION MARK PATTERN
system.graph('testFilePatQUEST').ifNotExists().create()
:remote config alias g testFilePatQUEST.g
schema.config().option('graph.allow_scan').set('true')

// SCHEMA
// PROPERTIES
schema.propertyKey('id').Text().ifNotExists().create()
schema.propertyKey('name').Text().ifNotExists().create()
schema.propertyKey('gender').Text().ifNotExists().create()
// VERTEX LABELS
schema.vertexLabel('person').properties('id', 'name', 'gender').ifNotExists().create()
// INDEXES
schema.vertexLabel("person").index("byid").materialized().by("id").add()
schema.vertexLabel("person").index("byname").materialized().by("name").add()

