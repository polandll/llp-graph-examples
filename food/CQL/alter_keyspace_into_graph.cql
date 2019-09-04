// START-alterKS-graph-Core
ALTER KEYSPACE food_cql 
  WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1} 
  AND graph_engine = 'Core';
// END-alterKS-graph-Core
