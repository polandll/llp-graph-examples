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

VERSION=dse-graph-loader-5.1.2-SNAPSHOT
LDR=/home/automaton/$VERSION/graphloader
INPUTEXAMPLE='filePattern'
INPUTBASEDIR='/home/automaton/graph-examples/food/TEST'
INPUTFILEDIR=$INPUTBASEDIR/$INPUTEXAMPLE/
echo $INPUTFILEDIR

# CSV
SCRIPTNAME1=$INPUTEXAMPLE'Map.groovy'
echo $SCRIPTNAME1
GRAPHNAME1='testFilePatCSV'
$LDR $INPUTFILEDIR/$SCRIPTNAME1 -graph $GRAPHNAME1 -address localhost

# JSON
SCRIPTNAME2=$INPUTEXAMPLE'JSONMap.groovy'
echo $SCRIPTNAME2
GRAPHNAME2='testFilePatJSON'
$LDR $INPUTFILEDIR/$SCRIPTNAME2 -graph $GRAPHNAME2 -address localhost

# MULT
SCRIPTNAME3=$INPUTEXAMPLE'MULTMap.groovy'
echo $SCRIPTNAME3
GRAPHNAME3='testFilePatMULT'
$LDR $INPUTFILEDIR/$SCRIPTNAME3 -graph $GRAPHNAME3 -address localhost

# RANGE
SCRIPTNAME4=$INPUTEXAMPLE'RANGEMap.groovy'
echo $SCRIPTNAME4
GRAPHNAME4='testFilePatRANGE'
$LDR $INPUTFILEDIR/$SCRIPTNAME4 -graph $GRAPHNAME4 -address localhost

