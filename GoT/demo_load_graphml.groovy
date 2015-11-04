println "Make Titan Cassandra graph"
graph = TitanFactory.open('conf/titan-cassandra.properties')
println "Load GoT script"
:load /home/polandll/CLONES/graph-examples/GoT/GoT-titan-createschema.groovy
println "Run Schema function"
defineGoTSchema(graph)
println "Create a graph traversal object"
g = graph.traversal(standard())
println "Count the number of objects"
g.V().count()
println "Load the GraphML data file"
graph.io(IoCore.graphml()).readGraph('/home/polandll/CLONES/graph-examples/GoT/GoT.gml')

println "Find all relatedBy edges"
g.E().has(label,'relatedBy').values()
println "Find all killedBy edges"
g.E().has(label,'killedBy').values()
println "Find all Daenerys's information"
g.V().has('hname','Daenerys').values()

println "Close the graph"
graph.close()
