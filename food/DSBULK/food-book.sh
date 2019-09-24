#!/bin/bash

#**********************************
# food-book.sh
# Use dsbulk to add book data to graph
# Lorina Poland
#**********************************

dsbulkBinDir="/home/automaton/dsbulk-1.3.4/bin"
repoDataDir="/home/automaton/graph-examples/food/DATA/CSV/vertices"
ks="food"

$dsbulkBinDir/dsbulk load --schema.keyspace $ks --schema.table book -url $repoDataDir/book.csv -delim '|' -header true --schema.allowMissingFields true --schema.mapping '0=book_id, 1=name, 2=publish_year, 3=isbn'
