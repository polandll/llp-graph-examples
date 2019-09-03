#!/bin/bash

#**********************************
# food-meal.sh
# Use dsbulk to add meal data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.keyspace food_cql --schema.table meal -url $repoDataDir/meal.csv -delim '|' -header true --schema.allowMissingFields true
