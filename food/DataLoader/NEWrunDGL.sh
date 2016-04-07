#!/bin/sh
# LDR defines the jar file for the DSE Graph Loader
# TYPE defines the input type. Values are: TEXT, CSV, JSON, TEXTXFORM
# DRYRUN_SETTING can be set to true (do the load) or false (do a dry run)
# INPUTFILEDIR defines the directory of the input files
# SCRIPTNAME defines the name of the mapping script
# GRAPHNAME defines the name of the graph used for loading. It does not have to exist prior 
# to loading

#LDR=/Users/lorinapoland/CLONES/dse-graph-loader/target/dse-graph-loader-0.1.0-SNAPSHOT-uberjar.jar
LDR=/Users/lorinapoland/CLONES/dse-graph-loader/bin/graphloader
TYPE=TEXT
DRYRUN_SETTING=false
INPUTFILEDIR=/Users/lorinapoland/CLONES/graph-examples/food/$TYPE/
#SCRIPTNAME='authorBookMapping'$TYPE'.groovy'
SCRIPTNAME='authorBookMapping'$TYPE'XFORM2.groovy'
GRAPHNAME=$TYPE'TEST'
$LDR $INPUTFILEDIR/$SCRIPTNAME -filename INPUTFILEDIR -graph $GRAPHNAME -address localhost -dryrun DRYRUN_SETTING
