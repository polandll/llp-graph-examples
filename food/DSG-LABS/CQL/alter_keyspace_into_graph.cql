ALTER KEYSPACE food_cql 
  WITH replication = {'class': 'NetworkTopologyStrategy', 'replication_factor': 1} 
  AND graph_engine = 'Core';
