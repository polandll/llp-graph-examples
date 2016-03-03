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
    		def name = schema.buildPropertyKey('name', String.class).add()
    		def gender = schema.buildPropertyKey('gender', String.class).add()
    		def instructions = schema.buildPropertyKey('instructions', String.class).add()
    		def timestamp = schema.buildPropertyKey('timestamp', Timestamp.class).add()
    		def ISBN = schema.buildPropertyKey('ISBN', String.class).add()
    		def calories = schema.buildPropertyKey('calories', Integer.class).add()
                
    		def amount = schema.buildPropertyKey('amount', String.class).add()
    		def stars = schema.buildPropertyKey('stars', Integer.class).add()
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
    		def byRecipe = recipe.buildVertexIndex('byRecipe').materialized().byPropertyKey('recipeTitle').add()
    		def byMeal = meal.buildVertexIndex('byMeal').materialized().byPropertyKey('mealTitle').add()
    		def byIngredient = ingredient.buildVertexIndex('byIngredient').materialized().byPropertyKey('iName').add()
    		def byReviewer = reviewer.buildVertexIndex('byReviewer').materialized().byPropertyKey('revname').add()
	}
	
	public static List parse(final Graph graph, final GraphTraversalSource g, final String dataDirectory, final int batchSize) {

        def counter = 0

        def beforeTime = System.currentTimeMillis();
       
		// Name::Gender
        new File(dataDirectory + '/authors.csv').eachLine { final String line ->

            def components = line.split("::")
            def name = components[0]
            def gender = components[1]
            def authorVertex = graph.addVertex(label, "author",
                "name", name,
                "gender", gender)

            if (++counter % batchSize == 0) graph.tx().commit()
        }
        graph.tx().commit()
        
        def afterAuthor = System.currentTimeMillis();

        //name::timestamp::ISBN::authors
        new File(dataDirectory + '/books.csv').eachLine { final String line ->

            def components = line.split("::")
            def name = components[0]
            def timestamp = components[1]
            def ISBN = components[2]
            def authors = components[3]
            def bookVertex = graph.addVertex(label, "book",
                "name", name,
                "timestamp", timestamp,
                "ISBN", ISBN)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            authors.split('\\|').each { def authName ->
                def authVertex = g.V().has("author", "name", authName).tryNext().orElseGet {graph.addVertex(label, "author", "name", authName)}
                authVertex.addEdge('authored', bookVertex)
                if (++counter % batchSize == 0) graph.tx().commit()
            }
        }
        
        graph.tx().commit()
        def afterBook = System.currentTimeMillis();
   
        //name
        new File(dataDirectory + '/ingredients.csv').eachLine { final String line ->

            def components = line.split("::")
            def name = components[0]
            def ingredientVertex = graph.addVertex(label, "ingredient",
                "name", name)

            if (++counter % batchSize == 0) graph.tx().commit()
        }
        
        graph.tx().commit()
        def afterIngredient = System.currentTimeMillis();
             
        //name::author::[(ingredient,amount)]::instructions
        new File(dataDirectory + '/recipes.csv').eachLine { final String line ->

            def components = line.split("::")
            def name = components[0]
            def author = components[1]
            def ingredient = components[2]
            def instructions = components[3]
            def recipeVertex = graph.addVertex(label, "recipe",
                "name", name,
                "author", author,
                "instructions", instructions)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            // FIX THIS SECTION *****************
            
           // ingredients.split('\\|').each { def ingredName ->
           //     def ingredVertex = g.V().has("ingredient", "name", ingredName).tryNext().orElseGet {graph.addVertex(label, "ingredient", "name", ingredName)}
           //     recipeVertex.addEdge('includes', ingredVertex)
           //     if (++counter % batchSize == 0) graph.tx().commit()
           // }
        }
        
        graph.tx().commit()
        def afterRecipe = System.currentTimeMillis();
        
        //name::timestamp::calories::recipes
        new File(dataDirectory + '/meals.csv').eachLine { final String line ->

            def components = line.split("::")
            def name = components[0]
            def timestamp = components[1].toInstant()
            def calories = components[3].toInteger()
            def recipes = components[4]
            def mealVertex = graph.addVertex(label, "meal",
                "name", name,
                "timestamp", timestamp,
                "calories", calories)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            recipes.split('\\|').each { def recipeName ->
                def recipeVertex = g.V().has("recipe", "name", recipeName).tryNext().orElseGet {graph.addVertex(label, "recipe", "name", recipeName)}
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
