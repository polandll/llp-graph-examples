repoDir="/home/automaton/graph-examples/food/DATA/CQL_CSV

dsbulk load -url $repoDir/person.csv -k food -t person -h localhost -header true
