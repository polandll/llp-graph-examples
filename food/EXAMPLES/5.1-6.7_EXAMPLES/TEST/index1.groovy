gremlin> :remote config alias g index1.g
==>g=index1.g
gremlin> schema.describe()
==>schema.propertyKey("livedIn").Text().single().create()
schema.propertyKey("instructions").Text().single().create()
schema.propertyKey("country").Text().multiple().properties("livedIn").create()
schema.propertyKey("amount").Text().single().create()
schema.propertyKey("gender").Text().single().create()
schema.propertyKey("year").Int().single().create()
schema.propertyKey("calories").Int().single().create()
schema.propertyKey("stars").Int().single().create()
schema.propertyKey("sensor_id").Uuid().single().create()
schema.propertyKey("ISBN").Text().single().create()
schema.propertyKey("nickname").Text().multiple().create()
schema.propertyKey("name").Text().single().create()
schema.propertyKey("location").Point().single().create()
schema.propertyKey("timestamp").Timestamp().single().create()
schema.propertyKey("city_id").Int().single().create()
schema.edgeLabel("authored").multiple().create()
schema.edgeLabel("rated").multiple().properties("stars").create()
schema.edgeLabel("includedIn").multiple().create()
schema.edgeLabel("created").multiple().properties("year").create()
schema.edgeLabel("includes").multiple().properties("amount").create()
schema.vertexLabel("meal").properties("name", "timestamp", "calories").create()
schema.vertexLabel("ingredient").properties("name").create()
schema.vertexLabel("FridgeSensor").partitionKey("city_id").clusteringKey("sensor_id").properties("name", "location").create()
schema.vertexLabel("FridgeSensor").index("search").search().by("location")..add()
schema.vertexLabel("author").properties("name", "gender", "nickname", "country").create()
schema.vertexLabel("author").index("search").search().by("nickname").asText().by("name").asString().add()
schema.vertexLabel("author").index("byName").secondary().by("name").add()
schema.vertexLabel("author").index("byLocation").property("country").by("livedIn").add()
schema.vertexLabel("book").properties("name", "year", "ISBN").create()
schema.vertexLabel("book").index("search").search().by("year").add()
schema.vertexLabel("recipe").properties("name", "instructions").create()
schema.vertexLabel("recipe").index("search").search().by("instructions").asText().by("name").asString().add()
schema.vertexLabel("recipe").index("byRecipe").materialized().by("name").add()
schema.vertexLabel("reviewer").properties("name").create()
schema.vertexLabel("reviewer").index("byReviewer").materialized().by("name").add()
schema.vertexLabel("reviewer").index("ratedByStars").outE("rated").by("stars").add()
schema.edgeLabel("authored").connection("author", "book").add()
schema.edgeLabel("rated").connection("reviewer", "recipe").add()
schema.edgeLabel("includedIn").connection("meal", "book").connection("recipe", "book").connection("recipe", "meal").add()
schema.edgeLabel("created").connection("author", "recipe").add()
schema.edgeLabel("includes").connection("recipe", "ingredient").add()
gremlin> g.V().valueMap()
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
==>{year=[1961], name=[The Art of French Cooking, Vol. 1]}
==>{ISBN=[0-394-40152-2], year=[1972], name=[Simca's Cuisine: 100 Classic French Recipes for Every Occasion]}
==>{ISBN=[0-394-40135-2], year=[1968], name=[The French Chef Cookbook]}
==>{ISBN=[0-307-33679-4], year=[2007], name=[The Art of Simple Food: Notes, Lessons, and Recipes from a Delicious Revolution]}
==>{name=[eggplant]}
==>{name=[zucchini]}
==>{name=[olive oil]}
==>{name=[yellow onion]}
==>{name=[green beans]}
==>{name=[tuna]}
==>{name=[tomato]}
==>{name=[hard-boiled egg]}
==>{name=[egg noodles]}
==>{name=[mushrooms]}
==>{name=[bacon]}
==>{name=[celery]}
==>{name=[green bell pepper]}
==>{name=[ground beef]}
==>{name=[pork sausage]}
==>{name=[shallots]}
==>{name=[chervil]}
==>{name=[fennel]}
==>{name=[parsley]}
==>{name=[oyster]}
==>{name=[Pernod]}
==>{name=[thyme]}
==>{name=[carrots]}
==>{name=[chicken broth]}
==>{name=[pork loin]}
==>{name=[red wine]}
==>{name=[beef]}
==>{name=[onion]}
==>{name=[mashed garlic]}
==>{name=[butter]}
==>{name=[tomato paste]}
==>{name=[JuliaDinner], calories=[900], timestamp=[2016-01-14T00:00:00Z]}
==>{name=[Saturday Feast], calories=[1000], timestamp=[2015-11-30T00:00:00Z]}
==>{name=[EverydayDinner], calories=[600], timestamp=[2016-01-14T00:00:00Z]}
==>{instructions=[Braise the beef. Saute the onions and carrots. Add wine and cook in a dutch oven at 425 degrees for 1 hour.], name=[Beef Bourguignon]}
==>{instructions=[Peel and cut the egglant. Make sure you cut eggplant into lengthwise slices that are about 1-inch wmyIde, 3-inches long, and 3/8-inch thick], name=[Ratatouille]}
==>{instructions=[Take a salad bowl or platter and line it with lettuce leaves, shortly before serving. Drizzle some olive oil on the leaves and dust them with salt.], name=[Salade Nicoise]}
==>{instructions=[Cook the egg noodles according to the package directions and keep warm. Heat 1 1/2 tablespoons of the oliveoil in a large saute pan over medium-high heat.], name=[Wild Mushroom Stroganoff]}
==>{instructions=[Preheat the oven to 375 degrees F. Cook bacon in a large skillet over medium heat until very crisp and fat has rendered, 8-10 minutes.], name=[Spicy Meatloaf]}
==>{instructions=[Saute the shallots, celery, herbs, and seasonings in 3 tablespoons of the butter for 3 minutes. Add the watercress and let it wilt.], name=[Oysters Rockefeller]}
==>{instructions=[In a heavy-bottomed pot, melt the butter. When it starts to foam, add the onions and thyme and cook over medium-low heat until tender, about 10 minutes.], name=[Carrot Soup]}
==>{instructions=[The day before, separate the meat from the ribs, stopping about 1 inch before the end of the bones. Season the pork liberally inside and out with salt and pepper and refrigerate overnight.], name=[Roast Pork Loin]}
gremlin> 

