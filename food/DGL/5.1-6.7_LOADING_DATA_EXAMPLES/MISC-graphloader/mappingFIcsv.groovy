/* SAMPLE EDGE DATA
city_id|sensor_id|name
santaCruz|93c4ec9b-68ff-455e-8668-1056ebc3689f|asparagus
*/
inputfiledir="/Users/lorinapoland/CLONES/graph-examples/food/MISC-graphloader/"
fridgeItemInput=inputfiledir + "fridgeItem.csv"
the_edges = File.csv(inputfiledir + "fridgeItemEdges.csv").delimiter('|')

the_edges = the_edges.transform {
    it['FridgeSensor'] = [
            'city_id' : it['city_id'],
            'sensor_id' : it['sensor_id'] ];
    it['ingredient'] = [
            'name' : it['name'] ];
    it
}
load(fridgeItemInput).asVertices {
    label "FridgeSensor"
    // The vertexLabel schema for FridgeSensor includes two keys:
    // partition key: city_id and clustering key: sensor_id 
    key city_id: "city_id", sensor_id: "sensor_id"
}

load(the_edges).asEdges  {
    label "linked"
    outV "ingredient", {
        label "ingredient"
        key "name"
    }
    inV "FridgeSensor", {
        label "FridgeSensor"
        key city_id:"city_id", sensor_id:"sensor_id"
    }
}
