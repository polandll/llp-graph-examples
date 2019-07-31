#!/bin/bash

#**********************************
# food-person.sh
# Use dsbulk to add person data to graph
# Lorina Poland
#**********************************

repoDir="/home/automaton/graph-examples/food/DATA/CQL_CSV"

dsbulk load -url $repoDir/person.csv -k food -t person -h localhost -header true
