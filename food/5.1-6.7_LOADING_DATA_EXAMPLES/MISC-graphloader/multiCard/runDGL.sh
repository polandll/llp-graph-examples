#!/bin/sh
# VERSION defines the graphloader version
# LDR defines the graphloader path
# TYPE defines the input type. Values are: TEXT, CSV, JSON, TEXTXFORM
# INPUTEXAMPLE defines the mapping example
# INPUTBASEDIR defines the main directory of the examples
# INPUTFILEDIR defines the directory of the input files
# SCRIPTNAME defines the name of the mapping script
# GRAPHNAME defines the name of the graph loaded. 
#   It does not have to exist prior to loading.

#VERSION=dse-graph-loader-5.0.5
VERSION=dse-graph-loader-5.1.2-SNAPSHOT
#LDR=/Users/lorinapoland/CLONES/$VERSION/graphloader
LDR=/home/automaton/$VERSION/graphloader
INPUTEXAMPLE='multiCard'
#INPUTBASEDIR='/Users/lorinapoland/CLONES/graph-examples/food/TEST'
##INPUTBASEDIR='/home/automaton/graph-examples/food/TEST'
INPUTFILEDIR=$INPUTBASEDIR/$INPUTEXAMPLE/
SCRIPTNAME=$INPUTEXAMPLE'Map.groovy'
GRAPHNAME=$INPUTEXAMPLE
$LDR $INPUTFILEDIR/$SCRIPTNAME -graph $GRAPHNAME -address localhost
