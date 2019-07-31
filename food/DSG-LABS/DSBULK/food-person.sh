#!/bin/bash

#**********************************
# food-person.sh
# Use dsbulk to add person data to graph
# Lorina Poland
#**********************************

repoDir="/home/automaton/graph-examples/food/DATA/CQL_CSV"

dsbulk load --schema.keyspace food_cql --schema.table person -url $repoDir/person.csv -delim '|' -header true --schema.allowMissingFields true
