#!/bin/bash

#**********************************
# food-recipe.sh
# Use dsbulk to add recipe data to graph
# Lorina Poland
#**********************************

dsbulkBinDir="/home/automaton/dsbulk-1.3.4/bin"
repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

$dsbulkBinDir/dsbulk load --schema.keyspace $ks --schema.table recipe -url $repoDataDir/recipe.csv -delim '|' -header true --schema.allowMissingFields true
