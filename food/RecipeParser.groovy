import com.datastax.bdp.graph.api.DseGraph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Direction
import org.apache.tinkerpop.gremlin.structure.Graph

import java.util.Map
import java.util.ArrayList
import java.util.List

class RecipeParser {
    
    public static List parse(final Graph graph, final GraphTraversalSource g, final String dataDirectory, final int batchSize) {

        def counter = 0

        def beforeTime = System.currentTimeMillis();
       
	// AuthorID::Name::Gender
        new File(dataDirectory + '/author_sql.csv').eachLine { final String line ->

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

		counter = 0
        //bookId::bookTitle::publishDate::ISBN::authors
        new File(dataDirectory + '/books_sql.csv').eachLine { final String line ->

            def components = line.split("::")
            def bookId = components[0].toInteger()
            def bookTitle = components[1]
            def publishDate = components[2]
            def ISBN = components[3]
            def authors = components[4]
            def bookVertex = graph.addVertex(label, "book",
                "bookId", bookId,
                "bookTitle", bookTitle,
                "publishDate", publishDate,
                "ISBN", ISBN)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            authors.split('\\|').each { def authId ->
                def authVertex = g.V().has("author", "authorId", authId.toInteger()).tryNext()
		// ****** NOT WORKING *******
		// message indicates that bookVertex is not "known"
		// I thought this is still in a loop where bookVertex is defined.
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
    		def publishDate = schema.buildPropertyKey('publishDate', String.class).add()
    		def mname = schema.buildPropertyKey('mname', String.class).add()
    		def createDate = schema.buildPropertyKey('createDate', String.class).add()
                
    		def rCreateDate = schema.buildPropertyKey('rCreateDate', String.class).add()
    		def stars = schema.buildPropertyKey('stars', String.class).add()
    		def ratedDate = schema.buildPropertyKey('ratedDate', String.class).add()
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
    		// Looks like these have changed - see northwind gist	
    		//def ratedByStars = reviewer.buildEdgeIndex('ratedByStars', rated).direction(Direction.OUT).byPropertyKey(stars).add()
    		//def byRecipe = recipe.buildIndex('byRecipe').ofMaterializedView().byPropertyKey(recipeTitle).add()
    		//def byMeal = meal.buildIndex('byMeal', MATERIALIZED).ofMaterializedView().byPropertyKey(mname).add()
    		//def byIngredient = ingredient.buildIndex('byIngredient').byPropertyKey('iname').add()
    		//def byReviewer = reviewer.buildIndex('byReviewer', MATERIALIZED).byPropertyKey('revname').add()
		})
	}

    public static List load(final DseGraph graph, final GraphTraversalSource g, final String dataDirectory, final int batchSize) {
        createSchema(graph)
        return parse(graph, g, dataDirectory, batchSize)
    }
}
RecipeParser.load(graph, g, "/Users/lorinapoland/CLONES/graph-examples/food/CSV",250)
