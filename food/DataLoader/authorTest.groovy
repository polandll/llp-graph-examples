// Update these paths to match the test environment
authorSchemaFilePath = '/Users/lorinapoland/CLONES/graph-examples/food/DataLoader/authorSchema.groovy'
juliaDataFilePath = '/Users/lorinapoland/CLONES/graph-examples/food/DataLoader/julia_author_import.json'
vertexImportScriptFilePath = '/Users/lorinapoland/CLONES/graph-examples/food/DataLoader/loadAuthor.groovy'

// Connect
:remote connect tinkerpop.server resources/graph/gremlin-console/conf/remote-objects.yaml
cluster = Cluster.build().create()
client = cluster.connect()
alias = client.alias("authorTest.g")

// Create graph
:> system.createGraph('authorTest').build()

// Load schema
schemaScript = new File(personSchemaFilePath).text
alias.submit(schemaScript).all().get()

// Read Julia's JSON definition into "juliaDoc"
mapper = new org.apache.tinkerpop.shaded.jackson.databind.ObjectMapper()
juliaData = new File(juliaDataFilePath)
juliaDoc = mapper.readValue(juliaData, Map.class)

// Read graph-data-mapper script into vertexImportScript
vertexImportScript = new File(vertexImportScriptFilePath).text

// Run some queries
alias.submit(vertexImportScript, ["doc": juliaDoc]).all().get()
alias.submit("g.V().has('name', 'julia').valueMap()").all().get()
alias.submit("g.V().has('name', 'julia').bothE()").all().get()

// MY ADDITION TO SHOW THE META-PROPERTIES OF CLEARANCE- LLP
//alias.submit("g.V().properties('clearance').valueMap()").all().get()