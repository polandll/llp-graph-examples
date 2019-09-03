gremlin> :remote config alias g filter.g
==>g=filter.g
gremlin> schema.describe()
==>schema.propertyKey("name").Text().single().create()
schema.propertyKey("age").Text().single().create()
schema.vertexLabel("person").properties("age", "name").create()
schema.vertexLabel("person").index("byname").materialized().by("name").add()
