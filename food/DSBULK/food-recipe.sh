#!/bin/bash

#**********************************
# food-recipe.sh
# Use dsbulk to add recipe data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.keyspace food_cql --schema.table recipe -url $repoDataDir/recipe.csv -delim '|' -header true --schema.allowMissingFields true
