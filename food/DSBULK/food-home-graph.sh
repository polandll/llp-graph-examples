#!/bin/bash

#**********************************
# food-home-graph.sh
# Use dsbulk to add home data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.graph food --schema.vertex home -url $repoDataDir/home.csv -delim '|' -header true --schema.allowMissingFields true
