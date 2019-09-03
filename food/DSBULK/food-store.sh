#!/bin/bash

#**********************************
# food-store.sh
# Use dsbulk to add store data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.keyspace food_cql --schema.table store -url $repoDataDir/store.csv -delim '|' -header true --schema.allowMissingFields true
