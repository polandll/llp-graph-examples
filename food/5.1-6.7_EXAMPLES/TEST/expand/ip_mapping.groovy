config create_schema: false, load_new: true

//Defines the data input source (a file which is specified via command line arguments)
inputfilename='/Users/lorinapoland/CLONES/graph-examples/food/TEST/expand/records-small.dat'
source = File.text(inputfilename)
        .regex("swid:([A-Z0-9-]+)\\sip:([0-9.]+):([a-zA-Z0-9]*):country=([a-zA-Z]+)\\sFS=([0-9]*)\\sLS=([0-9]*)")
        .header('swid', 'ip', 'edgelabel', 'country', 'firstSeen', 'lastSeen')
        .expand {
            return [
                    [swid: it['swid'], ipObj: [ 'id' : it['ip'], country: it['country']], edgelabel: it['edgelabel'], timestamp: it['firstSeen'], source: 'conviva'],
                    [swid: it['swid'], ipObj: [ 'id' : it['ip']], edgelabel: it['edgelabel'], timestamp: it['lastSeen'], source: 'conviva']
            ]
        }

//Defines the mapping from input file to graph
eventMapper = {
    labelField "edgelabel"
    outV "swid", {
        label "SWID"
        key "id"
    }
    inV "ipObj", {
        label "IP"
        key "id"
    }
    ignore 'country'
}

//Specifies what data source to load using which mapper
load(source).asEdges(eventMapper)
