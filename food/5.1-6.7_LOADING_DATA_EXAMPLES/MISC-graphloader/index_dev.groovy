gremlin> :remote config alias g index_dev.g
==>g=index_dev.g
gremlin> schema.describe()
==>schema.propertyKey("livedIn").Text().single().create()
schema.propertyKey("instructions").Text().single().create()
schema.propertyKey("country").Text().multiple().properties("livedIn").create()
schema.propertyKey("gender").Text().single().create()
schema.propertyKey("year").Int().single().create()
schema.propertyKey("sensor_id").Uuid().single().create()
schema.propertyKey("ISBN").Text().single().create()
schema.propertyKey("nickname").Text().multiple().create()
schema.propertyKey("name").Text().single().create()
schema.propertyKey("location").Point().single().create()
schema.propertyKey("city_id").Int().single().create()
schema.vertexLabel("FridgeSensor").partitionKey("city_id").clusteringKey("sensor_id").properties("location", "name").create()
schema.vertexLabel("FridgeSensor").index("search").search().by("location")..add()
schema.vertexLabel("author").properties("gender", "nickname", "name").create()
schema.vertexLabel("author").index("search").search().by("nickname").asText().by("name").asString().add()
schema.vertexLabel("author").index("byName").secondary().by("name").add()
schema.vertexLabel("book").properties("ISBN", "year", "name").create()
schema.vertexLabel("book").index("search").search().by("year").add()
schema.vertexLabel("recipe").properties("instructions", "name").create()
schema.vertexLabel("recipe").index("search").search().by("instructions").asText().by("name").asString().add()
schema.vertexLabel("recipe").index("byRecipe").materialized().by("name").add()
gremlin> g.V()
==>v[{~label=FridgeSensor, city_id=100, sensor_id=60bcae02-f6e5-11e5-9ce9-5e5517507c66}]
==>v[{~label=FridgeSensor, city_id=100, sensor_id=61deada0-3bb2-4d6d-a606-a44d963f03b5}]
==>v[{~label=author, community_id=1841380352, member_id=0}]
==>v[{~label=author, community_id=1841380352, member_id=1}]
==>v[{~label=author, community_id=1841380352, member_id=2}]
==>v[{~label=author, community_id=1841380352, member_id=3}]
==>v[{~label=author, community_id=1841380352, member_id=4}]
==>v[{~label=author, community_id=1841380352, member_id=5}]
==>v[{~label=author, community_id=1841380352, member_id=6}]
==>v[{~label=author, community_id=1841380352, member_id=7}]
==>v[{~label=author, community_id=1841380352, member_id=8}]
==>v[{~label=author, community_id=1841380352, member_id=9}]
==>v[{~label=author, community_id=1841380352, member_id=22}]
==>v[{~label=book, community_id=1841380352, member_id=10}]
==>v[{~label=book, community_id=1841380352, member_id=11}]
==>v[{~label=book, community_id=1841380352, member_id=12}]
==>v[{~label=book, community_id=1841380352, member_id=13}]
==>v[{~label=recipe, community_id=1841380352, member_id=14}]
==>v[{~label=recipe, community_id=1841380352, member_id=15}]
==>v[{~label=recipe, community_id=1841380352, member_id=16}]
==>v[{~label=recipe, community_id=1841380352, member_id=17}]
==>v[{~label=recipe, community_id=1841380352, member_id=18}]
==>v[{~label=recipe, community_id=1841380352, member_id=19}]
==>v[{~label=recipe, community_id=1841380352, member_id=20}]
==>v[{~label=recipe, community_id=1841380352, member_id=21}]
