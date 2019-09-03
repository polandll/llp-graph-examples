#!/bin/bash

#**********************************
# food-fridge_sensor-graph.sh
# Use dsbulk to add fridge_sensor data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"
ks="food"

dsbulk load --schema.graph $ks --schema.vertex fridge_sensor -url $repoDataDir/fridge_sensor.csv -delim '|' -header true --schema.allowMissingFields true
