// hold = new File('/Users/lorinapoland/CLONES/graph-examples/food/CSV/RecipeParserCSV.groovy').text; []
// script = [hold, 'RecipeParser.load(graph, g, "/Users/lorinapoland/CLONES/graph-examples/food/CSV",250).toString()'].join("\n"); []
// :> @script

import com.datastax.bdp.graph.api.DseGraph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Direction
import org.apache.tinkerpop.gremlin.structure.Graph

import java.util.Map
import java.util.ArrayList
import java.util.List

import static com.datastax.bdp.graph.api.schema.VertexIndex.Type.SECONDARY
import static com.datastax.bdp.graph.api.schema.VertexIndex.Type.MATERIALIZED

class RecipeParser {
    
        public static void createSchema(final DseGraph graph) {
    	  def schema = graph.schema()
    	
    	    // Property Keys
    		def id = schema.buildPropertyKey('myId', Integer.class).add()
    		def aname = schema.buildPropertyKey('aname', String.class).add()
    		def gender = schema.buildPropertyKey('gender', String.class).add()
    		def revname = schema.buildPropertyKey('revname', String.class).add()
    		def recipeTitle = schema.buildPropertyKey('recipeTitle', String.class).add()
    		def instructions = schema.buildPropertyKey('instructions', String.class).add()
    		def iName = schema.buildPropertyKey('iName', String.class).add()
    		def bookTitle = schema.buildPropertyKey('bookTitle', String.class).add()
    		def publishDate = schema.buildPropertyKey('publishDate', Integer.class).add()
    		def ISBN = schema.buildPropertyKey('ISBN', String.class).add()
    		def mname = schema.buildPropertyKey('mealTitle', String.class).add()
    		def mCreateDate = schema.buildPropertyKey('mCreateDate', Instant.class).add()
    		def calories = schema.buildPropertyKey('calories', Integer.class).add()
                
    		def rCreateDate = schema.buildPropertyKey('rCreateDate', Integer.class).add()
    		def amount = schema.buildPropertyKey('amount', String.class).add()
    		def stars = schema.buildPropertyKey('stars', Integer.class).add()
    		def ratedDate = schema.buildPropertyKey('ratedDate', Instant.class).add()
    		def comment = schema.buildPropertyKey('comment', String.class).add()
    		
    		// Vertex Labels
    		def author = schema.buildVertexLabel('author').add()
    		def recipe = schema.buildVertexLabel('recipe').add()
			def ingredient = schema.buildVertexLabel('ingredient').add()
    		def book = schema.buildVertexLabel('book').add()
    		def meal = schema.buildVertexLabel('meal').add()
    		def reviewer = schema.buildVertexLabel('reviewer').add()
                
    		// Edge Labels
    		def authored = schema.buildEdgeLabel('authored').add()
    		def created = schema.buildEdgeLabel('created').add()
    		def includes = schema.buildEdgeLabel('includes').add()
			def includedIn = schema.buildEdgeLabel('includedIn').add()
    		def rated = schema.buildEdgeLabel('rated').add()
                
    		// Indexes	
    		def ratedByStars = reviewer.buildEdgeIndex('ratedByStars', rated).direction(OUT).byPropertyKey('stars').add()
    		def byRecipe = recipe.buildVertexIndex('byRecipe', MATERIALIZED).byPropertyKey('recipeTitle').add()
    		def byMeal = meal.buildVertexIndex('byMeal', MATERIALIZED).byPropertyKey('mealTitle').add()
    		def byIngredient = ingredient.buildVertexIndex('byIngredient', MATERIALIZED).byPropertyKey('iName').add()
    		def byReviewer = reviewer.buildVertexIndex('byReviewer', MATERIALIZED).byPropertyKey('revname').add()
	}
	
	public static List parse(final Graph graph, final GraphTraversalSource g, final String dataDirectory, final int batchSize) {

        def counter = 0

        def beforeTime = System.currentTimeMillis();
       
		// AuthorID::Name::Gender
        new File(dataDirectory + '/authors.csv').eachLine { final String line ->

            def components = line.split("::")
            def authorId = components[0].toInteger()
            def aname = components[1]
            def gender = components[2]
            def authorVertex = graph.addVertex(label, "author",
                "myId", authorId,
                "aname", aname,
                "gender", gender)

            if (++counter % batchSize == 0) graph.tx().commit()
        }
        graph.tx().commit()
        
        def afterAuthor = System.currentTimeMillis();

        //bookId::bookTitle::publishDate::ISBN::authors
        new File(dataDirectory + '/books.csv').eachLine { final String line ->

            def components = line.split("::")
            def bookId = components[0].toInteger()
            def bookTitle = components[1]
            def publishDate = components[2].toInteger()
            def ISBN = components[3]
            def authors = components[4]
            def bookVertex = graph.addVertex(label, "book",
                "myId", bookId,
                "bookTitle", bookTitle,
                "publishDate", publishDate,
                "ISBN", ISBN)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            authors.split('\\|').each { def authName ->
                def authVertex = g.V().has("author", "aname", authName).tryNext().orElseGet {graph.addVertex(label, "author", "aname", authName)}
                authVertex.addEdge('authored', bookVertex)
                if (++counter % batchSize == 0) graph.tx().commit()
            }
        }
        
        graph.tx().commit()
        def afterBook = System.currentTimeMillis();
   
        //ingredientId::iName
        new File(dataDirectory + '/ingredients.csv').eachLine { final String line ->

            def components = line.split("::")
            def ingredientId = components[0].toInteger()
            def iName = components[1]
            def ingredientVertex = graph.addVertex(label, "ingredient",
                "myId", ingredientId,
                "iName", iName)

            if (++counter % batchSize == 0) graph.tx().commit()
        }
        
        graph.tx().commit()
        def afterIngredient = System.currentTimeMillis();
             
        //recipeId::recipeTitle::author::[(ingredient,amount)]::instructions
        new File(dataDirectory + '/recipes.csv').eachLine { final String line ->

            def components = line.split("::")
            def recipeId = components[0].toInteger()
            def recipeTitle = components[1]
            def author = components[2]
            def ingredient = components[3]
            def instructions = components[4]
            def recipeVertex = graph.addVertex(label, "recipe",
                "myId", recipeId,
                "recipeTitle", recipeTitle,
                "author", author,
                "instructions", instructions)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            // FIX THIS SECTION *****************
            
           // ingredients.split('\\|').each { def ingredName ->
           //     def ingredVertex = g.V().has("ingredient", "iName", ingredName).tryNext().orElseGet {graph.addVertex(label, "ingredient", "iName", ingredName)}
           //     recipeVertex.addEdge('includes', ingredVertex)
           //     if (++counter % batchSize == 0) graph.tx().commit()
           // }
        }
        
        graph.tx().commit()
        def afterRecipe = System.currentTimeMillis();
        
        //mealId::mealTitle::mCreateDate::calories::recipes
        new File(dataDirectory + '/meals.csv').eachLine { final String line ->

            def components = line.split("::")
            def mealId = components[0].toInteger()
            def mealTitle = components[1]
            def mCreateDate = components[2].toInstant()
            def calories = components[3].toInteger()
            def recipes = components[4]
            def mealVertex = graph.addVertex(label, "meal",
                "myId", mealId,
                "mealTitle", mealTitle,
                "mCreateDate", mCreateDate,
                "calories", calories)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            recipes.split('\\|').each { def recipeName ->
                def recipeVertex = g.V().has("recipe", "recipeTitle", recipeName).tryNext().orElseGet {graph.addVertex(label, "recipe", "recipeTitle", recipeName)}
                mealVertex.addEdge('includes', recipeVertex)
                if (++counter % batchSize == 0) graph.tx().commit()
            }
        }
        
        graph.tx().commit()
        def afterMeal = System.currentTimeMillis();
        
        return [afterAuthor-beforeTime, afterBook-afterAuthor];
    }

    public static List load(final DseGraph graph, final GraphTraversalSource g, final String dataDirectory, final int batchSize) {
        createSchema(graph)
        //return parse(graph, g, dataDirectory, batchSize)
        parse(graph, g, dataDirectory, batchSize)
    }
}
RecipeParser.load(graph, g, "/Users/lorinapoland/CLONES/graph-examples/food/CSV",250)
