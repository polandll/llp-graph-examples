#!/bin/bash

#**********************************
# quickstart.sh
# QuickStart code script for public users
# Author: Lorina Poland
#**********************************

repoGremlinDir="/home/automaton/graph-examples/food/DSG-LABS/GREMLIN"

cat $repoGremlinDir/0_create_graph_QS.gremlin | dse gremlin-console;
cat  $repoGremlinDir/remoteQS.gremlin \
 $repoGremlinDir/create_VLs.gremlin \
 $repoGremlinDir/create_ELs.gremlin  | dse gremlin-console;
cqlsh -f $repoGremlinDir/create_table_recipe.cql;
cat $repoGremlinDir/remoteQS.gremlin \
  $repoGremlinDir/insert_3persons.gremlin \
  $repoGremlinDir/insert_3books.gremlin \
  $repoGremlinDir/insert_2edges.gremlin | dse gremlin-console;

