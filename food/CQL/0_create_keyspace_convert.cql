// START-createKS-convert
CREATE KEYSPACE IF NOT EXISTS food_cql_conversion
WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 };
// END-createKS-convert
