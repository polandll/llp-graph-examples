/* SAMPLE INPUT
*/

// CONFIGURATION
// Configures the data loader to create the schema
config dryrun: false, preparation: true, create_schema: false, load_new: true, schema_output: 'loader_output.txt'

// DATA INPUT
// Define the data input source (a file which can be specified via command line arguments)
// inputfiledir is the directory for the input files
inputfiledir = '/home/automaton/graph-examples/food/TEST/geo_dgl/'
ptsInput = File.csv(inputfiledir + "data/vertices/cart_pts.csv").delimiter('|')
linesInput = File.csv(inputfiledir + "data/vertices/cart_lines.csv").delimiter('|')
polysInput = File.csv(inputfiledir + "data/vertices/cart_polys.csv").delimiter('|')

import com.datastax.driver.dse.geometry.Point
ptsInput = ptsInput.transform {
  it['point'] = Point.fromWellKnownText(it['point']);
}

//Specifies what data source to load using which mapper (as defined inline)
  
load(ptsInput).asVertices {
    label "location"
    key "name"
}

load(linesInput).asVertices {
    label "lineLocation"
    key "name"
}

load(polysInput).asVertices {
    label "polyLocation"
    key "name"
}
