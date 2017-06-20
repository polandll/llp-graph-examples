// Cartesian example

system.graph('cartesianData').ifNotExists().create()
:remote config alias g cartesianData.g
schema.config().option('graph.allow_scan').set('true')
schema.config().option('graph.traversal_sources.g.restrict_lambda').set('false')

//SCHEMA
schema.propertyKey('name').Text().create()
schema.propertyKey('point').Point().withBounds(-3,-3,3,3).create()
schema.vertexLabel('location').properties('name','point').create()
schema.propertyKey('line').Linestring().withBounds(-3,-3,3,3).create()
schema.vertexLabel('lineLocation').properties('name','line').create()
schema.propertyKey('polygon').Polygon().withBounds(-3,-3,3,3).create()
schema.vertexLabel('polyLocation').properties('name','polygon').create()

schema.vertexLabel('polyLocation').index('byname').materialized().by('name').add()
schema.vertexLabel('location').index('byname').materialized().by('name').add()
schema.vertexLabel('lineLocation').index('byname').materialized().by('name').add()
