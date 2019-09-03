#!/bin/bash

#**********************************
# food-mealitem.sh
# Use dsbulk to add mealitem data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

dsbulk load --schema.keyspace $ks --schema.table meal_item -url $repoDataDir/meal_item.csv -delim '|' -header true --schema.allowMissingFields true
