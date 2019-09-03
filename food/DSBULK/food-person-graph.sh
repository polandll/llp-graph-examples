#!/bin/bash

#**********************************
# food-person-graph.sh
# Use dsbulk to add person data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

dsbulk load --schema.graph $ks --schema.vertex person -url $repoDataDir/person.csv -delim '|' -header true --schema.allowMissingFields true
