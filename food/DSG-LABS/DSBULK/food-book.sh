#!/bin/bash

#**********************************
# food-book.sh
# Use dsbulk to add book data to graph
# Lorina Poland
#**********************************

repoDir="/home/automaton/graph-examples/food/DATA/CQL_CSV"

dsbulk load --schema.keyspace food_cql --schema.table book -url $repoDir/book.csv -delim '|' -header true --schema.allowMissingFields true --schema.nullToUnset true --schema.mapping '0=book_id, 1=name, 2=publish_year 3=isbn'
