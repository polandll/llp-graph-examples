#!/bin/bash

#**********************************
# food-person.sh
# Use dsbulk to add person data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

dsbulk load --schema.keyspace $ks --schema.table person -url $repoDataDir/person.csv -delim '|' -header true --schema.allowMissingFields true
