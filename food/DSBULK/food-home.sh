#!/bin/bash

#**********************************
# food-home.sh
# Use dsbulk to add home data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.keyspace food_cql --schema.table home -url $repoDataDir/home.csv -delim '|' -header true --schema.allowMissingFields true
