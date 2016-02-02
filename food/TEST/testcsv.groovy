// Collect data together into a map from a data file (eg CSV)


import java.util.Map
import java.util.ArrayList
import java.util.List
 
// The data (normally from a file)
def inputData = """tim, 35, Manchester
sesame street, 40, New York
FireFox, 5, Mountain View"""
 
// Rather than mess about, lets make an injectWithIndex method for Lists
List.metaClass.injectWithIndex = { initial, closure ->
delegate.eachWithIndex { v, idx ->
initial = closure.call( initial, v, idx )
}
initial
}
 
def csvMapping = { headings, data, splitter ->
ret = []
// Split each line into a list of tokens
data.splitEachLine( splitter ) { toks ->
// Then build up our map with injectWithIndex, and add it to our resultant List
ret << toks.injectWithIndex( [:] ) { map, curr, idx ->
map."${headings[ idx ]}" = curr.trim()
map
}
}
ret
}
 
// Make the call, passing the headers, the data we want to read in, and the separator
csvMapping( [ 'name', 'age', 'location' ], inputData, ',' )