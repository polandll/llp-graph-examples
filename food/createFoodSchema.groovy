import com.datastax.bdp.graph.api.DseGraph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Direction
import org.apache.tinkerpop.gremlin.structure.Graph

import java.util.Map
import java.util.ArrayList
import java.util.List

import static com.datastax.bdp.graph.api.schema.VertexIndex.Type.SECONDARY

class RecipeFactory {

	public static void createSchema(final DseGraph graph) {
    	graph.migration("setup", { def schema ->
    	
    	    // Property Keys
    		def id = schema.buildPropertyKey('id', Integer.class).add()
    		def aname = schema.buildPropertyKey('aname', String.class).add()
    		def gender = schema.buildPropertyKey('gender', String.class).add()
    		def revname = schema.buildPropertyKey('revname', String.class).add()
    		def recipeTitle = schema.buildPropertyKey('recipeTitle', String.class).add()
    		def instructions = schema.buildPropertyKey('instructions', String.class).add()
    		def iname = schema.buildPropertyKey('iname', String.class).add()
    		def category = schema.buildPropertyKey('category', String.class).add()
    		def bookTitle = schema.buildPropertyKey('bookTitle', String.class).add()
    		def publishDate = schema.buildPropertyKey('publishDate', Date.class).add()
    		def mname = schema.buildPropertyKey('mname', String.class).add()
    		def createDate = schema.buildPropertyKey('createDate', Date.class).add()
                
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
    		def created = schema.buildEdgeLabel('created').add()
    		def includes = schema.buildEdgeLabel('includes').add()
			def includedIn = schema.buildEdgeLabel('includedIn').add()
    		def rated = schema.buildEdgeLabel('rated').add()
                
    		// Indexes
    		// Looks like these have changed - see northwind gist	
    		def ratedByStars = reviewer.buildEdgeIndex('ratedByStars', rated).direction(Direction.OUT).byPropertyKey(stars).add()
    		def byRecipe = recipe.buildIndex('byRecipe').ofMaterializedView().byPropertyKey(recipeTitle).add()
    		def byMeal = meal.buildIndex('byMeal').ofMaterializedView().byPropertyKey(mname).add()
    		def byIngredient = ingredient.buildIndex('byIngredient').ofMaterializedView().byPropertyKey(iname).add()
    		def byReviewer = reviewer.buildIndex('byReviewer').ofMaterializedView().byPropertyKey(revname).add()
		})
	}
	
	// I WOULD NEED A GRYO FILE OF MY DATA FOR THE NEXT TWO METHODS
	//public static Graph load(final GraphTraversalSource g) {
    //    def file = new File('/tmp/recipes.kryo')
    //    if (file.exists() == false) {
    //        def os = file.newOutputStream()
    //        os << new URL("http://github.com/polandll/graph-examples/food/assets/recipes.kryo").openStream()
    //        os.close()
    //    }
    //    RecipeFactory.load(g, file.getAbsolutePath())
    //}

    //public static Graph load(final GraphTraversalSource g, final String pathToGryo) {
    //    RecipeFactory.load(g.getGraph().get(), pathToGryo)
    //}
	
	//public static Graph load(final DseGraph graph, final String pathToGryo) {
	public static Graph load(final DseGraph graph) {
	    RecipeFactory.createSchema(graph)
	    // graph.io(IoCore.gryo()).readGraph(pathToGryo)
	    return graph
	}
	
}