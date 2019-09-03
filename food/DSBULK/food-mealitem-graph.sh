#!/bin/bash

#**********************************
# food-mealitem-graph.sh
# Use dsbulk to add mealitem data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.graph food --schema.vertex meal_item -url $repoDataDir/meal_item.csv -delim '|' -header true --schema.allowMissingFields true
