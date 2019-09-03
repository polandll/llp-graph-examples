#!/bin/bash

#**********************************
# food-book-graph.sh
# Use dsbulk to add book data to graph
# Lorina Poland
#**********************************

repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

dsbulk load --schema.graph $ks --schema.vertex book -url $repoDataDir/book.csv -delim '|' -header true --schema.allowMissingFields true --schema.mapping '0=book_id, 1=name, 2=publish_year, 3=isbn'
