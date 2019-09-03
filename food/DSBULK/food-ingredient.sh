#!/bin/bash

#**********************************
# food-ingredient.sh
# Use dsbulk to add ingredient data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"
ks="food"

dsbulk load --schema.keyspace $ks --schema.table ingredient -url $repoDataDir/ingredient.csv -delim '|' -header true --schema.allowMissingFields true
