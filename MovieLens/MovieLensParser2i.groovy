import com.datastax.bdp.graph.api.DseGraph

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Direction
import org.apache.tinkerpop.gremlin.structure.Graph

import java.util.Map
import java.util.ArrayList
import java.util.List

class MovieLensParser {

    static Map occupations

    static {
        occupations = [0: "other", 1: "academic/educator", 2: "artist",
                3: "clerical/admin", 4: "college/grad student", 5: "customer service",
                6: "doctor/health care", 7: "executive/managerial", 8: "farmer",
                9: "homemaker", 10: "K-12 student", 11: "lawyer", 12: "programmer",
                13: "retired", 14: "sales/marketing", 15: "scientist", 16: "self-employed",
                17: "technician/engineer", 18: "tradesman/craftsman", 19: "unemployed", 20: "writer"]
    }

    public static List parse(final Graph graph, final GraphTraversalSource g, final String dataDirectory, final int maxRatings, final int batchSize) {

        def counter = 0

        def beforeTime = System.currentTimeMillis();

        // MovieID::Title::Genres
        new File(dataDirectory + '/movies.dat').eachLine { final String line ->

            def components = line.split("::")
            def movieId = components[0].toInteger()
            def movieTitle = components[1]
            def genres = components[2]
            def movieVertex = graph.addVertex(label, "movie",
                "movieId", movieId,
                "name", movieTitle)

            if (++counter % batchSize == 0) graph.tx().commit()
            
            genres.split('\\|').each { def genre ->
                def genreVertex = g.V().has("genre", "name", genre).tryNext().orElseGet {graph.addVertex(label, "genre", "name", genre)}
                movieVertex.addEdge('hasGenre', genreVertex)
                if (++counter % batchSize == 0) graph.tx().commit()
            }
        }

        graph.tx().commit()
        def afterMovie = System.currentTimeMillis();

        // UserID::Gender::Age::Occupation::Zip-code
        new File(dataDirectory + '/users.dat').eachLine { final String line ->

            def components = line.split("::")
            def userId = components[0].toInteger()
            def userGender = components[1]
            def userAge = components[2].toInteger()
            def jobId = components[3].toInteger()
            def userZipcode = components[4]
            def userVertex = graph.addVertex(label, "person",
                "userId", userId,
                "gender", userGender,
                "age", userAge,
                "zipcode", userZipcode)

            if (++counter % batchSize == 0) graph.tx().commit()

            def occupationVertex = g.V().has("occupation", "jobId", jobId).tryNext().orElseGet {graph.addVertex(label, "occupation", "jobId", jobId, "name", occupations.get(jobId))}
            userVertex.addEdge('hasOccupation', occupationVertex)

            if (++counter % batchSize == 0) graph.tx().commit()
        }

        graph.tx().commit()
        def afterUser = System.currentTimeMillis();


        counter = 0
        // UserID::MovieID::Rating::Timestamp
        new File(dataDirectory + '/ratings.dat').eachLine { final String line ->
            if (counter > maxRatings) return
            def components = line.split("::")
            def userId = components[0].toInteger()
            def movieId = components[1].toInteger()
            def stars = components[2].toInteger()
            def time = components[3].toLong()
            def userTraversal = g.V().has("person", "userId", userId)
            def movieTraversal = g.V().has("movie", "movieId", movieId)
            if (userTraversal.hasNext() && movieTraversal.hasNext()) {
                userTraversal.next().addEdge('rated', movieTraversal.next(), 'stars', stars, 'time', time)
                if (++counter % batchSize == 0) {
                    graph.tx().commit()
                }
            }
        }

        graph.tx().commit()
        def afterRec = System.currentTimeMillis();

        return [afterMovie-beforeTime, afterUser-afterMovie, afterRec-afterUser];
    }

    public static void createSchema(final DseGraph graph) {
        graph.migration("setup", { def schema ->
            def movieId = schema.buildPropertyKey("movieId", Integer.class).add()
            def userId = schema.buildPropertyKey("userId", Integer.class).add()
            def jobId = schema.buildPropertyKey("jobId", Integer.class).add()
            def name = schema.buildPropertyKey("name", String.class).add()
            def gender = schema.buildPropertyKey("gender", String.class).add()
            def zipcode = schema.buildPropertyKey("zipcode", String.class).add()
            def age = schema.buildPropertyKey("age", Integer.class).add()
            def stars = schema.buildPropertyKey("stars", Integer.class).add()
            def time = schema.buildPropertyKey("time", Long.class).add()

            def movie = schema.buildVertexLabel("movie").add()
            def genre = schema.buildVertexLabel("genre").add()
                        def person = schema.buildVertexLabel("person").add()
            def occupation = schema.buildVertexLabel("occupation").add()

            movie.buildIndex("movieById").ofSecondary().byPropertyKey(movieId).add()
            person.buildIndex("personById").ofSecondary().byPropertyKey(userId).add()
            genre.buildIndex("genreByName").ofSecondary().byPropertyKey(name).add()
            occupation.buildIndex("occupationById").ofSecondary().byPropertyKey(jobId).add()

            def hasGenre = schema.buildEdgeLabel("hasGenre").add()
            def hasOccupation = schema.buildEdgeLabel("hasOccupation").add()
            def rated = schema.buildEdgeLabel("rated").add()

            person.buildEdgeIndex("ratingsByStars", rated).direction(Direction.OUT).byPropertyKey(stars).add()
        })
    }

    public static List load(final DseGraph graph, final GraphTraversalSource g, final String dataDirectory, final int numRatings, final int batchSize) {
        createSchema(graph)
        return parse(graph, g, dataDirectory, numRatings, batchSize)
    }
}

//MovieLensParser.load(graph, g, "/home/ubuntu/movielens/ml-1m", Integer.MAX_VALUE, 250)
MovieLensParser.load(graph, g, "/home/ubuntu/movielens/ml-1m", 50000, 250)
