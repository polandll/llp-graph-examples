// hold = new File('/Users/lorinapoland/CLONES/graph-examples/food/RecipeSchema.groovy').text; []
// script = [hold, 'RecipeSchema.load(graph).toString()'].join("\n"); []
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


class RecipeSchema {

	public static void createSchema(final DseGraph graph) {

    	  def schema = graph.schema()
    	
    	    // Property Keys
    		def name = schema.buildPropertyKey('name', String.class).add()
    		def gender = schema.buildPropertyKey('gender', String.class).add()
    		def instructions = schema.buildPropertyKey('instructions', String.class).add()
    		def year = schema.buildPropertyKey('year', Integer.class).add()
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
	
	public static Graph load(final DseGraph graph) {
		RecipeSchema.createSchema(graph)
		return graph
	}
}
