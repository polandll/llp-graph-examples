#!/bin/bash

#**********************************
# food-fridge_sensor.sh
# Use dsbulk to add fridge_sensor data to graph
# Lorina Poland
#**********************************

dsbulkBinDir="/home/automaton/dsbulk-1.3.4/bin"
repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

$dsbulkBinDir/dsbulk load --schema.keyspace $ks --schema.table fridge_sensor -url $repoDataDir/fridge_sensor.csv -delim '|' -header true --schema.allowMissingFields true
