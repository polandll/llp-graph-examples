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
gremlin> g.V().valueMap()
==>{name=[jones1], location=[POINT (-118.35977 34.171221)]}
==>{name=[smith1], location=[POINT (-115.655068 35.163427)]}
==>{gender=[F], name=[Julia Child]}
==>{gender=[F], name=[Simone Beck]}
==>{gender=[F], name=[Louisette Bertholie]}
==>{gender=[F], name=[Patricia Simon]}
==>{gender=[F], name=[Alice Waters]}
==>{gender=[F], name=[Patricia Curtan]}
==>{gender=[F], name=[Kelsie Kerr]}
==>{gender=[M], name=[Fritz Streiff]}
==>{gender=[M], name=[Emeril Lagasse]}
==>{gender=[M], name=[James Beard]}
==>{gender=[M], name=[Jamie Oliver], nickname=[Jimmy]}
==>{year=[1961], name=[The Art of French Cooking, Vol. 1]}
==>{ISBN=[0-394-40152-2], year=[1972], name=[Simca's Cuisine: 100 Classic French Recipes for Every Occasion]}
==>{ISBN=[0-394-40135-2], year=[1968], name=[The French Chef Cookbook]}
==>{ISBN=[0-307-33679-4], year=[2007], name=[The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution]}
==>{instructions=[Braise the beef. Saute the onions and carrots. Add wine and cook in a dutch oven at 425 degrees for 1 hour.], name=[Beef Bourguignon]}
==>{instructions=[Peel and cut the egglant. Make sure you cut eggplant into lengthwise slices that are about 1-inch wmyIde, 3-inches long, and 3/8-inch thick], name=[Ratatouille]}
==>{instructions=[Take a salad bowl or platter and line it with lettuce leaves, shortly before serving. Drizzle some olive oil on the leaves and dust them with salt.], name=[Salade Nicoise]}
==>{instructions=[Cook the egg noodles according to the package directions and keep warm. Heat 1 1/2 tablespoons of the oliveoil in a large saute pan over medium-high heat.], name=[Wild Mushroom Stroganoff]}
==>{instructions=[Preheat the oven to 375 degrees F. Cook bacon in a large skillet over medium heat until very crisp and fat has rendered, 8-10 minutes.], name=[Spicy Meatloaf]}
==>{instructions=[Saute the shallots, celery, herbs, and seasonings in 3 tablespoons of the butter for 3 minutes. Add the watercress and let it wilt.], name=[Oysters Rockefeller]}
==>{instructions=[In a heavy-bottomed pot, melt the butter. When it starts to foam, add the onions and thyme and cook over medium-low heat until tender, about 10 minutes.], name=[Carrot Soup]}
==>{instructions=[The day before, separate the meat from the ribs, stopping about 1 inch before the end of the bones. Season the pork liberally inside and out with salt and pepper and refrigerate overnight.], name=[Roast Pork Loin]}
