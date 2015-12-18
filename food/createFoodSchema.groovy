import com.datastax.bdp.graph.api.DseGraph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Direction
import org.apache.tinkerpop.gremlin.structure.Graph

import java.util.Map
import java.util.ArrayList
import java.util.List

class RecipeParser {

	public static void createSchema(final DseGraph graph) {
    	graph.migration("setup", { def schema ->
    		def author = schema.buildVertexLabel('author').add()
    		def recipe = schema.buildVertexLabel('recipe').add()
			def ingredient = schema.buildVertexLabel('ingredient').add()
    		def book = schema.buildVertexLabel('book').add()
    		def meal = schema.buildVertexLabel('meal').add()
    		def reviewer = schema.buildVertexLabel('reviewer').add()
                
    		def created = schema.buildEdgeLabel('created').add()
    		def includes = schema.buildEdgeLabel('includes').add()
			def includedIn = schema.buildEdgeLabel('includedIn').add()
    		def rated = schema.buildEdgeLabel('rated').add()
                
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
    			
    		def ratedByStars = reviewer.buildEdgeIndex('ratedByStars', rated).direction(Direction.OUT).byPropertyKey(stars).add()
    		def byRecipe = recipe.buildIndex('byRecipe').ofMaterializedView().byPropertyKey(recipeTitle).add()
    		def byMeal = meal.buildIndex('byMeal').ofMaterializedView().byPropertyKey(mname).add()
    		def byIngredient = ingredient.buildIndex('byIngredient').ofMaterializedView().byPropertyKey(iname).add()
    		def byReviewer = reviewer.buildIndex('byReviewer').ofMaterializedView().byPropertyKey(revname).add()
		})
	}
    //public static List load(final DseGraph graph, final GraphTraversalSource g, final String dataDirectory, final int numRatings, final int batchSize) {
    //    createSchema(graph)
    //    return parse(graph, g, dataDirectory, numRatings, batchSize)
    //}
}

RecipeParser.createSchema(graph)