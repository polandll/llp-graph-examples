// script = new File('/Users/lorinapoland/CLONES/graph-examples/food/Gryo/RecipeCreateSchemaLoadGryo.groovy').text; []
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

class RecipeFactory {

	public static void createSchema(final DseGraph graph) {
    	graph.migration("setup", { def schema ->
    	//***********
    	// SHOULD THE NEXT LINE BE USED NOW?
    	//************
    	//Schema schema = graph.schema()
    	
    	    // Property Keys
    		def id = schema.buildPropertyKey('id', Integer.class).add()
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
    		def calories = schema.builPropertyKey('calories'), Integer.class).add()
                
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
    		def authored = schema.builEdgeLabel('authored').add()
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
		})
	}

	public static Graph load(final GraphTraversalSource g) {
        def file = new File('//Users/lorinapoland/CLONES/graph-examples/food/Gryo/recipe.gryo')
        if (file.exists() == false) {
            def os = file.newOutputStream()
            os << new URL("http://github.com/polandll/graph-examples/food/Gryo/recipe.gryo").openStream()
            os.close()
        }
        RecipeFactory.load(g, file.getAbsolutePath())
    }

    public static Graph load(final GraphTraversalSource g, final String pathToGryo) {
        RecipeFactory.load(g.getGraph().get(), pathToGryo)
    }
	
	public static Graph load(final DseGraph graph, final String pathToGryo) {
	//public static Graph load(final DseGraph graph) {
	    RecipeFactory.createSchema(graph)
	    graph.io(IoCore.gryo()).readGraph(pathToGryo)
	    return graph
	}	
}
