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
                "authorId", authorId,
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
                "bookId", bookId,
                "bookTitle", bookTitle,
                "publishDate", publishDate,
                "ISBN", ISBN)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            // **************************
            // ********** PROBLEM HERE
            // **************************
            authors.split('\\|').each { def authId ->
                def authVertex = g.V().has("author", "aname", authId).tryNext()
                bookVertex.addEdge('authored', authVertex)
                if (++counter % batchSize == 0) graph.tx().commit()
            }
        }
        
        graph.tx().commit()
        def afterBook = System.currentTimeMillis();
        
        return [afterAuthor-beforeTime, afterBook-afterAuthor];
    }
         
    public static void createSchema(final DseGraph graph) {
            //schema = graph.schema()
        graph.migration("setup", { def schema ->
    	
    	    // Property Keys
    		def authorId = schema.buildPropertyKey('authorId', Integer.class).add()
    		def aname = schema.buildPropertyKey('aname', String.class).add()
    		def gender = schema.buildPropertyKey('gender', String.class).add()
    		def revname = schema.buildPropertyKey('revname', String.class).add()
    		def recipeTitle = schema.buildPropertyKey('recipeTitle', String.class).add()
    		def instructions = schema.buildPropertyKey('instructions', String.class).add()
    		def iname = schema.buildPropertyKey('iname', String.class).add()
    		def category = schema.buildPropertyKey('category', String.class).add()
    		def bookTitle = schema.buildPropertyKey('bookTitle', String.class).add()
    		def publishDate = schema.buildPropertyKey('publishDate', Instant.class).add()
    		def mname = schema.buildPropertyKey('mname', String.class).add()
    		def createDate = schema.buildPropertyKey('createDate', Instant.class).add()
                
    		def rCreateDate = schema.buildPropertyKey('rCreateDate', Instant.class).add()
    		def stars = schema.buildPropertyKey('stars', String.class).add()
    		def ratedDate = schema.buildPropertyKey('ratedDate', Instant.class).add()
    		def amount = schema.buildPropertyKey('amount', String.class).add()
    		
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
    		def byMeal = meal.buildVertexIndex('byMeal', MATERIALIZED).byPropertyKey('mname').add()
    		def byIngredient = ingredient.buildVertexIndex('byIngredient', MATERIALIZED).byPropertyKey('iname').add()
    		def byReviewer = reviewer.buildVertexIndex('byReviewer', MATERIALIZED).byPropertyKey('revname').add()
		})
	}

    public static List load(final DseGraph graph, final GraphTraversalSource g, final String dataDirectory, final int batchSize) {
        createSchema(graph)
        return parse(graph, g, dataDirectory, batchSize)
    }
}
RecipeParser.load(graph, g, "/Users/lorinapoland/CLONES/graph-examples/food/CSV",250)
