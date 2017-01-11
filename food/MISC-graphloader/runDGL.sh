#!/bin/sh
# LDR defines the graphloader path
# TYPE defines the input type. Values are: TEXT, CSV, JSON, TEXTXFORM
# DRYRUN_SETTING can be set to true (do a dryrun and show proposed schema) or false (load the data)
# INPUTFILEDIR defines the directory of the input files
# SCRIPTNAME defines the name of the mapping script
# GRAPHNAME defines the name of the graph used for loading. It does not have to exist prior 
# to loading

VERSION=dse-graph-loader-5.0.5
LDR=/Users/lorinapoland/CLONES/$VERSION/graphloader
TYPE=CSV
OPTIONS=$DRYRUN $PREP $CREATE_SCHEMA $LOAD_NEW $SCHEMA_OUTPUT
DRYRUN='-dryrun true'
PREP='-preparation true'
CREATE_SCHEMA='-create_schema true'
LOAD_NEW='-load_new true'
SCHEMA_OUTPUT='-schema_output loader_output.txt'
INPUTFILEDIR=/Users/lorinapoland/CLONES/graph-examples/food/$TYPE/
SCRIPTNAME='authorBookMapping'$TYPE'.groovy'
GRAPHNAME='test'$TYPE
#$LDR $INPUTFILEDIR/$SCRIPTNAME -filename INPUTFILEDIR -graph $GRAPHNAME -address localhost -dryrun DRYRUN_SETTING
$LDR $INPUTFILEDIR/$SCRIPTNAME -graph $GRAPHNAME -address localhost $OPTIONS
