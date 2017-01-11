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

VERSION=dse-graph-loader-5.0.5
LDR=/Users/lorinapoland/CLONES/$VERSION/graphloader
TYPE=CSV
INPUTEXAMPLE='edgeProperty'
INPUTBASEDIR='/Users/lorinapoland/CLONES/graph-examples/food'
INPUTFILEDIR=$INPUTBASEDIR/$TYPE/$INPUTEXAMPLE
SCRIPTNAME='edgePropertyMapping.groovy'
GRAPHNAME='test'$INPUTEXAMPLE
$LDR $INPUTFILEDIR/$SCRIPTNAME -graph $GRAPHNAME -address localhost
