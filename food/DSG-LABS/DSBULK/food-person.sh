#!/bin/bash

#**********************************
# food-person.sh
# Use dsbulk to add person data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CQL_CSV"

dsbulk load --schema.keyspace food_cql --schema.table person -url $repoDataDir/person.csv -delim '|' -header true --schema.allowMissingFields true
