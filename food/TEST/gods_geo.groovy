gremlin> :remote config alias g gog_geo.g
==>g=gog_geo.g
gremlin> schema.describe()
==>schema.propertyKey("reason").Text().single().create()
schema.propertyKey("polygon").Polygon().single().create()
schema.propertyKey("line").Linestring().single().create()
schema.propertyKey("name").Text().single().create()
schema.propertyKey("time").Timestamp().single().create()
schema.propertyKey("nicknames").Text().multiple().properties("time").create()
schema.propertyKey("place").Point().single().create()
schema.propertyKey("age").Int().single().create()
schema.propertyKey("point").Point().single().create()
schema.edgeLabel("mother").multiple().create()
schema.edgeLabel("lives").multiple().properties("reason").create()
schema.edgeLabel("father").multiple().create()
schema.edgeLabel("battled").multiple().properties("name", "time", "place").create()
schema.edgeLabel("brother").multiple().create()
schema.edgeLabel("pet").multiple().create()
schema.edgeLabel("spouse").single().create()
schema.vertexLabel("demigod").properties("name", "age", "point").create()
schema.vertexLabel("demigod").index("battledIndex").outE("battled").by("time").add()
schema.vertexLabel("titan").properties("name", "age").create()
schema.vertexLabel("titan").index("titan_age_index").materialized().by("name").add()
schema.vertexLabel("titan").index("titan_name_index").materialized().by("name").add()
schema.vertexLabel("location").properties("name").create()
schema.vertexLabel("human").properties("name", "age", "point", "line", "polygon").create()
schema.vertexLabel("human").index("search").search().by("point")..add()
schema.vertexLabel("god").properties("name", "age", "nicknames", "point").create()
schema.vertexLabel("god").index("god_age_index").secondary().by("age").add()
schema.vertexLabel("god").index("god_name_index").secondary().by("name").add()
schema.vertexLabel("monster").properties("name", "point").create()
schema.edgeLabel("mother").connection("demigod", "human").add()
schema.edgeLabel("lives").connection("god", "location").connection("monster", "location").add()
schema.edgeLabel("father").connection("demigod", "god").connection("god", "titan").add()
schema.edgeLabel("battled").connection("demigod", "monster").connection("god", "god").add()
schema.edgeLabel("brother").connection("god", "god").add()
schema.edgeLabel("pet").connection("god", "monster").add()
schema.edgeLabel("spouse").connection("god", "god").add()
gremlin> g.V().valueMap()
==>{name=[hercules], age=[30], point=[POINT (52.2 33.4)]}
==>{name=[jupiter], age=[5000], point=[POINT (52.6 33.6)]}
==>{name=[neptune], nicknames=[Neppy, Flipper], age=[4500], point=[POINT (52.2 33.5)]}
==>{name=[juno], age=[5000], point=[POINT (52.7 33.7)]}
==>{name=[minerva], age=[5000]}
==>{name=[pluto], age=[4000], point=[POINT (52.3 33.4)]}
==>{polygon=[POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))], line=[LINESTRING (30 10, 10 30, 40 40)], name=[alcmene], age=[45], point=[POINT (52.2 33.4)]}
==>{name=[electryon], age=[45]}
==>{name=[andromeda], age=[45]}
==>{name=[sky]}
==>{name=[sea]}
==>{name=[tartarus]}
==>{name=[nemean]}
==>{name=[hydra]}
==>{name=[cerberus], point=[POINT (52.3 33.5)]}
==>{name=[oceanus], age=[10000]}
==>{name=[rhea], age=[10000]}
==>{name=[saturn], age=[10000]}
