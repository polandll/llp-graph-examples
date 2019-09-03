gremlin> schema.describe()
==>schema.propertyKey("boundary").Polygon().single().create()
schema.propertyKey("gender").Text().single().create()
schema.propertyKey("name").Text().single().create()
schema.propertyKey("coordinates").Point().single().create()
schema.propertyKey("linestring").Linestring().single().create()
schema.propertyKey("point").Point().single().create()
schema.vertexLabel("plot").properties("boundary").create()
schema.vertexLabel("author").properties("point", "name", "gender").create()
schema.vertexLabel("road_element").properties("linestring").create()
schema.vertexLabel("place").properties("name", "coordinates").create()
schema.vertexLabel("place").index("search").search().by("name").asText().by("coordinates")..add()
gremlin> g.V()
==>v[{~label=author, community_id=1591675264, member_id=0}]
==>v[{~label=place, community_id=1375286144, member_id=1}]
==>v[{~label=place, community_id=1182054400, member_id=512}]
==>v[{~label=place, community_id=1376350720, member_id=1}]
==>v[{~label=plot, community_id=412097536, member_id=0}]
==>v[{~label=plot, community_id=612592640, member_id=0}]
==>v[{~label=road_element, community_id=1160133760, member_id=0}]
==>v[{~label=road_element, community_id=1148127872, member_id=0}]
