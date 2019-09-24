#!/bin/bash

#**********************************
# food-location-graph.sh
# Use dsbulk to add location data to graph
# Lorina Poland
#**********************************

dsbulkBinDir="/home/automaton/dsbulk-1.3.4/bin"
repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

$dsbulkBinDir/dsbulk load --schema.graph $ks --schema.vertex location -url $repoDataDir/location.csv -delim '|' -header true --schema.allowMissingFields true
