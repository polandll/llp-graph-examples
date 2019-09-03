#/bin/sh

# Cartesian - SEARCH INDEX
cat cart-schema.groovy | dse gremlin-console
~/dse-graph-loader-5.1.2-SNAPSHOT/graphloader cartMap.groovy -address localhost -graph cartSIData
cat cart-queries.groovy | dse gremlin-console >> cartSI.txt

# Geo - SEARCH INDEX
cat geo-schema.groovy | dse gremlin-console
~/dse-graph-loader-5.1.2-SNAPSHOT/graphloader geoMap.groovy -address localhost -graph geoSIData
cat geo-queries.groovy | dse gremlin-console >> geoSI.txt

