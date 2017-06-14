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

VERSION=dse-graph-loader-5.1.0-SNAPSHOT
LDR=/home/automaton/$VERSION/graphloader
TYPE=CSV
INPUTEXAMPLE='dirSource'
INPUTBASEDIR='/home/automaton/graph-examples/food'
INPUTFILEDIR=$INPUTBASEDIR/MISC-graphloader/$INPUTEXAMPLE
echo $INPUTFILEDIR
SCRIPTNAME=$INPUTEXAMPLE'Map.groovy'
echo $SCRIPTNAME
GRAPHNAME='test'$INPUTEXAMPLE
$LDR $INPUTFILEDIR/$SCRIPTNAME -graph $GRAPHNAME -address localhost
