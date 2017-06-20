// Cartesian example - NO SEARCH INDEX

system.graph('geoData').ifNotExists().create()
:remote config alias g geoData.g
schema.config().option('graph.allow_scan').set('true')
schema.config().option('graph.traversal_sources.g.restrict_lambda').set('false')

//SCHEMA
schema.propertyKey('name').Text().create()
schema.propertyKey('point').Point().withGeoBounds().create()
schema.vertexLabel('location').properties('name','point').create()
schema.propertyKey('line').Linestring().withGeoBounds().create()
schema.vertexLabel('lineLocation').properties('name','line').create()
schema.propertyKey('polygon').Polygon().withGeoBounds().create()
schema.vertexLabel('polyLocation').properties('name','polygon').create()

schema.vertexLabel('location').index('byname').materialized().by('name').add()
schema.vertexLabel('location').index('bypoint').materialized().by('point').add()
schema.vertexLabel('lineLocation').index('byname').materialized().by('name').add()
schema.vertexLabel('lineLocation').index('byline').materialized().by('line').add()
schema.vertexLabel('polyLocation').index('byname').materialized().by('name').add()
schema.vertexLabel('polyLocation').index('bypolygon').materialized().by('polygon').add()
