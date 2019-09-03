#!/bin/bash
START=$(date +%s)

export PATH=$PATH:/opt/dse-graph-loader-5.0.6/
graphloader loader.groovy -graph sma_graph_prod_v10 -address 192.168.69.134 -dryrun false -preparation false -abort_on_num_failures 10000 -abort_on_prep_errors false -vertex_complete false

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "It took $DIFF seconds"
