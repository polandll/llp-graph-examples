#/bin/sh

# Cartesian - NO SEARCH INDEX
cat cart-noSearchIndex-schema.groovy | dse gremlin-console
~/dse-graph-loader-5.1.2-SNAPSHOT/graphloader cartMap.groovy -address localhost -graph cartData
cat cart-noSearchIndex-queries.groovy | dse gremlin-console >> cartNoSI.txt

# Cartesian - SEARCH INDEX
cat cart-SearchIndex-schema.groovy | dse gremlin-console
~/dse-graph-loader-5.1.2-SNAPSHOT/graphloader cartMap.groovy -address localhost -graph cartSIData
cat cart-SearchIndex-queries.groovy | dse gremlin-console >> cartSI.txt

# Geo - NO SEARCH INDEX
cat geo-noSearchIndex-schema.groovy | dse gremlin-console
~/dse-graph-loader-5.1.2-SNAPSHOT/graphloader geoMap.groovy -address localhost -graph geoData
cat geo-noSearchIndex-queries.groovy | dse gremlin-console >> geoNoSI.txt

# Geo - SEARCH INDEX
cat geo-SearchIndex-schema.groovy | dse gremlin-console
~/dse-graph-loader-5.1.2-SNAPSHOT/graphloader geoMap.groovy -address localhost -graph geoSIData
cat geo-SearchIndex-queries.groovy | dse gremlin-console >> geoSI.txt

