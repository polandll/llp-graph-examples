// START-alterKS-convert
ALTER KEYSPACE food_cql_conversion 
  WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1} 
  AND graph_engine = 'Core';
// END-alterKS-convert
