#!/bin/bash

#**********************************
# food-mealitem-graph.sh
# Use dsbulk to add mealitem data to graph
# Lorina Poland
#**********************************

dsbulkBinDir="/home/automaton/dsbulk-1.3.4/bin"
repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

$dsbulkBinDir/dsbulk load --schema.graph $ks --schema.vertex meal_item -url $repoDataDir/meal_item.csv -delim '|' -header true --schema.allowMissingFields true
