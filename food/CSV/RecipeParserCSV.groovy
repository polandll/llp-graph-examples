// script = new File('/Users/lorinapoland/CLONES/graph-examples/food/CSV/RecipeParserCSV.groovy').text; []
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
            
            // **************************
            // ********** PROBLEM HERE
            // **************************
            authors.split('\\|').each { def authId ->
                def authVertex = g.V().has("author", "aname", authId).tryNext().orElseGet {graph.addVertex(label, "author", "aname", authId)}
                bookVertex.addEdge('authored', authVertex)
                if (++counter % batchSize == 0) graph.tx().commit()
            }
        }
        
        graph.tx().commit()
        def afterBook = System.currentTimeMillis();
        
        return [afterAuthor-beforeTime, afterBook-afterAuthor];
    }
         
    public static void createSchema(final DseGraph graph) {
            Schema schema = graph.schema()
    	
    	    // Property Keys
    		PropertyKey id = schema.buildPropertyKey('myId', Integer.class).add()
    		PropertyKey aname = schema.buildPropertyKey('aname', String.class).add()
    		PropertyKey gender = schema.buildPropertyKey('gender', String.class).add()
    		PropertyKey revname = schema.buildPropertyKey('revname', String.class).add()
    		PropertyKey recipeTitle = schema.buildPropertyKey('recipeTitle', String.class).add()
    		PropertyKey instructions = schema.buildPropertyKey('instructions', String.class).add()
    		PropertyKey iName = schema.buildPropertyKey('iName', String.class).add()
    		PropertyKey bookTitle = schema.buildPropertyKey('bookTitle', String.class).add()
    		PropertyKey publishDate = schema.buildPropertyKey('publishDate', Integer.class).add()
    		PropertyKey ISBN = schema.buildPropertyKey('ISBN', String.class).add()
    		PropertyKey mname = schema.buildPropertyKey('mealTitle', String.class).add()
    		PropertyKey mCreateDate = schema.buildPropertyKey('mCreateDate', Instant.class).add()
    		PropertyKey calories = schema.buildPropertyKey('calories', Integer.class).add()
                
    		PropertyKey rCreateDate = schema.buildPropertyKey('rCreateDate', Integer.class).add()
    		PropertyKey amount = schema.buildPropertyKey('amount', String.class).add()
    		PropertyKey stars = schema.buildPropertyKey('stars', Integer.class).add()
    		PropertyKey ratedDate = schema.buildPropertyKey('ratedDate', Instant.class).add()
    		PropertyKey comment = schema.buildPropertyKey('comment', String.class).add()
    		
    		// Vertex Labels
    		VertexLabel author = schema.buildVertexLabel('author').add()
    		VertexLabel recipe = schema.buildVertexLabel('recipe').add()
			VertexLabel ingredient = schema.buildVertexLabel('ingredient').add()
    		VertexLabel book = schema.buildVertexLabel('book').add()
    		VertexLabel meal = schema.buildVertexLabel('meal').add()
    		VertexLabel reviewer = schema.buildVertexLabel('reviewer').add()
                
    		// Edge Labels
    		EdgeLabel authored = schema.buildEdgeLabel('authored').add()
    		EdgeLabel created = schema.buildEdgeLabel('created').add()
    		EdgeLabel includes = schema.buildEdgeLabel('includes').add()
			EdgeLabel includedIn = schema.buildEdgeLabel('includedIn').add()
    		EdgeLabel rated = schema.buildEdgeLabel('rated').add()
                
    		// Indexes	
    		EdgeIndex ratedByStars = reviewer.buildEdgeIndex('ratedByStars', rated).direction(OUT).byPropertyKey('stars').add()
    		VertexIndex byRecipe = recipe.buildVertexIndex('byRecipe', MATERIALIZED).byPropertyKey('recipeTitle').add()
    		VertexIndex byMeal = meal.buildVertexIndex('byMeal', MATERIALIZED).byPropertyKey('mealTitle').add()
    		VertexIndex byIngredient = ingredient.buildVertexIndex('byIngredient', MATERIALIZED).byPropertyKey('iName').add()
    		VertexIndex byReviewer = reviewer.buildVertexIndex('byReviewer', MATERIALIZED).byPropertyKey('revname').add()
	}

    public static List load(final DseGraph graph, final GraphTraversalSource g, final String dataDirectory, final int batchSize) {
        createSchema(graph)
        return parse(graph, g, dataDirectory, batchSize)
    }
}
RecipeParser.load(graph, g, "/Users/lorinapoland/CLONES/graph-examples/food/CSV",250)
