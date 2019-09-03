#!/bin/bash

#**********************************
# food-fridge_sensor.sh
# Use dsbulk to add fridge_sensor data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV_JSON/vertices"

dsbulk load --schema.keyspace food_cql --schema.table fridge_sensor -url $repoDataDir/fridge_sensor.csv -delim '|' -header true --schema.allowMissingFields true
