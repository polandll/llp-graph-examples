gremlin> :remote config alias g repl4.g
==>g=repl4.g
gremlin> schema.describe()
==>schema.propertyKey("livedIn").Text().multiple().create()
schema.propertyKey("instructions").Text().multiple().create()
schema.propertyKey("country").Text().multiple().properties("livedIn").create()
schema.propertyKey("amount").Text().multiple().create()
schema.propertyKey("fridgeItem").Text().multiple().create()
schema.propertyKey("gender").Text().multiple().create()
schema.propertyKey("year").Int().multiple().create()
schema.propertyKey("calories").Int().multiple().create()
schema.propertyKey("stars").Int().multiple().create()
schema.propertyKey("sensor_id").Uuid().multiple().create()
schema.propertyKey("ISBN").Text().multiple().create()
schema.propertyKey("avatar_url").Text().single().create()
schema.propertyKey("public").Boolean().single().create()
schema.propertyKey("nickname").Text().multiple().create()
schema.propertyKey("name").Text().multiple().create()
schema.propertyKey("comment").Text().single().create()
schema.propertyKey("location").Point().multiple().create()
schema.propertyKey("timestamp").Timestamp().multiple().create()
schema.propertyKey("city_id").Text().multiple().create()
schema.edgeLabel("authored").multiple().create()
schema.edgeLabel("rated").multiple().properties("stars", "timestamp", "comment").create()
schema.edgeLabel("includedIn").multiple().create()
schema.edgeLabel("created").multiple().create()
schema.edgeLabel("includes").multiple().create()
schema.vertexLabel("meal").properties("name").create()
schema.vertexLabel("meal").index("byMeal").materialized().by("name").add()
schema.vertexLabel("ingredient").properties("name").create()
schema.vertexLabel("ingredient").index("byIngredient").materialized().by("name").add()
schema.vertexLabel("FridgeSensor").partitionKey("city_id").clusteringKey("sensor_id").properties("location", "fridgeItem").create()
schema.vertexLabel("FridgeSensor").index("search").search().by("location")..add()
schema.vertexLabel("author").properties("gender", "nickname", "country", "name").create()
schema.vertexLabel("author").index("byName").secondary().by("name").add()
schema.vertexLabel("author").index("search").search().by("name").asString().by("nickname").asText().add()
schema.vertexLabel("author").index("byLocation").property("country").by("livedIn").add()
schema.vertexLabel("book").properties("year", "name").create()
schema.vertexLabel("book").index("search").search().by("name").asString().by("year").add()
schema.vertexLabel("recipe").properties("instructions", "name").create()
schema.vertexLabel("recipe").index("byRecipe").materialized().by("name").add()
schema.vertexLabel("recipe").index("search").search().by("instructions").asText().by("name").asString().add()
schema.vertexLabel("reviewer").properties("name").create()
schema.vertexLabel("reviewer").index("byReviewer").materialized().by("name").add()
schema.vertexLabel("reviewer").index("ratedByStars").outE("rated").by("stars").add()
schema.vertexLabel("reviewer").index("ratedByTimestamp").outE("rated").by("timestamp").add()
schema.vertexLabel("reviewer").index("ratedByComments").outE("rated").by("comment").add()

schema.edgeLabel("rated").connection("reviewer", "recipe").add()



gremlin> g.V()
Could not find an index to answer query clause and graph.allow_scan is disabled: ((label = FridgeSensor) | (label = author) | (label = book) | (label = ingredient) | (label = meal) | (label = recipe) | (label = reviewer))
Type ':help' or ':h' for help.
Display stack trace? [yN]n
gremlin>  schema.config().option('graph.allow_scan').set('true')
==>null
gremlin> g.V()
==>v[{~label=ingredient, community_id=799349504, member_id=3}]
==>v[{~label=ingredient, community_id=1860007808, member_id=1}]
==>v[{~label=ingredient, community_id=1455062016, member_id=0}]
==>v[{~label=ingredient, community_id=313964672, member_id=2}]
==>v[{~label=ingredient, community_id=14260992, member_id=0}]
==>v[{~label=ingredient, community_id=1777591040, member_id=1}]
==>v[{~label=ingredient, community_id=1414663680, member_id=0}]
==>v[{~label=ingredient, community_id=1212334336, member_id=0}]
==>v[{~label=ingredient, community_id=1411217536, member_id=0}]
==>v[{~label=ingredient, community_id=723101440, member_id=0}]
==>v[{~label=ingredient, community_id=1880001024, member_id=1}]
==>v[{~label=ingredient, community_id=1455226496, member_id=2}]
==>v[{~label=ingredient, community_id=1564071936, member_id=2}]
==>v[{~label=ingredient, community_id=1065309824, member_id=0}]
==>v[{~label=ingredient, community_id=620521344, member_id=2}]
==>v[{~label=ingredient, community_id=65848704, member_id=1}]
==>v[{~label=ingredient, community_id=2069745536, member_id=0}]
==>v[{~label=ingredient, community_id=596798464, member_id=0}]
==>v[{~label=ingredient, community_id=858149760, member_id=0}]
==>v[{~label=ingredient, community_id=1601471744, member_id=1}]
==>v[{~label=ingredient, community_id=509828864, member_id=2}]
==>v[{~label=ingredient, community_id=1632078976, member_id=1}]
==>v[{~label=ingredient, community_id=1675970688, member_id=0}]
==>v[{~label=ingredient, community_id=1203431936, member_id=1}]
==>v[{~label=ingredient, community_id=2119299712, member_id=1}]
==>v[{~label=ingredient, community_id=161167744, member_id=3}]
==>v[{~label=ingredient, community_id=2013811072, member_id=1}]
==>v[{~label=ingredient, community_id=1200060800, member_id=1}]
==>v[{~label=ingredient, community_id=1621648768, member_id=0}]
==>v[{~label=ingredient, community_id=328266880, member_id=0}]
==>v[{~label=ingredient, community_id=1841837952, member_id=0}]
==>v[{~label=recipe, community_id=1410760064, member_id=0}]
==>v[{~label=recipe, community_id=63233152, member_id=0}]
==>v[{~label=recipe, community_id=1087536384, member_id=0}]
