// hold = new File('/Users/lorinapoland/CLONES/graph-examples/food/Gryo/RecipeCreateSchemaLoadGryo.groovy').text; []
// script = [hold, 'RecipeFactory.load(g).toString()'].join("\n"); []
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

    	Schema schema = graph.schema()
    	
    	    // Property Keys
    		def name = schema.buildPropertyKey('name', String.class).add()
    		def gender = schema.buildPropertyKey('gender', String.class).add()
    		def instructions = schema.buildPropertyKey('instructions', String.class).add()
    		def year = schema.buildPropertyKey('year', Integer.class).add()
    		def ISBN = schema.buildPropertyKey('ISBN', String.class).add()
    		def calories = schema.buildPropertyKey('calories', Integer.class).add()
                
    		def amount = schema.buildPropertyKey('amount', String.class).add()
    		def stars = schema.buildPropertyKey('stars', Integer.class).add()
    		def timestamp = schema.buildPropertyKey('timestamp', Timestamp.class).add()
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
    		def byRecipe = recipe.buildVertexIndex('byRecipe').materialized().byPropertyKey('name').add()
    		def byMeal = meal.buildVertexIndex('byMeal').materialized.byPropertyKey('name').add()
    		def byIngredient = ingredient.buildVertexIndex('byIngredient').materialized().byPropertyKey('name').add()
    		def byReviewer = reviewer.buildVertexIndex('byReviewer').materialized().byPropertyKey('name').add()
	}

	public static Graph load(final GraphTraversalSource g) {
        def file = new File('/Users/lorinapoland/CLONES/graph-examples/food/Gryo/recipe.gryo')
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
	    RecipeFactory.createSchema(graph)
	    graph.io(IoCore.gryo()).readGraph(pathToGryo)
	    return graph
	}	
}
