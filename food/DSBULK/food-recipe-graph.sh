#!/bin/bash

#**********************************
# food-recipe-graph.sh
# Use dsbulk to add recipe data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.graph food --schema.vertex recipe -url $repoDataDir/recipe.csv -delim '|' -header true --schema.allowMissingFields true
