#!/bin/bash

#**********************************
# food-location.sh
# Use dsbulk to add location data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.keyspace food_cql --schema.table location -url $repoDataDir/location.csv -delim '|' -header true --schema.allowMissingFields true
