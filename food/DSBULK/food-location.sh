#!/bin/bash

#**********************************
# food-location.sh
# Use dsbulk to add location data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"
ks="food"

dsbulk load --schema.keyspace $ks --schema.table location -url $repoDataDir/location.csv -delim '|' -header true --schema.allowMissingFields true
