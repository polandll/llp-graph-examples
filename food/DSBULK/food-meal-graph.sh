#!/bin/bash

#**********************************
# food-meal-graph.sh
# Use dsbulk to add meal data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"
ks="food"

dsbulk load --schema.graph $ks --schema.vertex meal -url $repoDataDir/meal.csv -delim '|' -header true --schema.allowMissingFields true
