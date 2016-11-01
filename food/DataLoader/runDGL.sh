#!/bin/sh
# LDR defines the graphloader path
# TYPE defines the input type. Values are: TEXT, CSV, JSON, TEXTXFORM
# DRYRUN_SETTING can be set to true (do a dryrun and show proposed schema) or false (load the data)
# INPUTFILEDIR defines the directory of the input files
# SCRIPTNAME defines the name of the mapping script
# GRAPHNAME defines the name of the graph used for loading. It does not have to exist prior 
# to loading

LDR=/Users/lorinapoland/CLONES/dse-graph-loader-5.0.4-SNAPSHOT/graphloader
TYPE=CSV
OPTIONS="-dryrun true -preparation true -create_schema true -load_new true -schema_output loader_output.txt"
#DRYRUN_SETTING=true
INPUTFILEDIR=/Users/lorinapoland/CLONES/graph-examples/food/$TYPE/
#SCRIPTNAME='authorBookMapping'$TYPE'.groovy'
SCRIPTNAME='fiTestMapping.groovy'
#GRAPHNAME='test'$TYPE
GRAPHNAME='test_fi'
#$LDR $INPUTFILEDIR/$SCRIPTNAME -filename INPUTFILEDIR -graph $GRAPHNAME -address localhost -dryrun DRYRUN_SETTING
$LDR $INPUTFILEDIR/$SCRIPTNAME -graph $GRAPHNAME -address localhost $OPTIONS
