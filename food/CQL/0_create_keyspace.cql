// START-createKS-CQL
CREATE KEYSPACE IF NOT EXISTS food_cql 
WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 };
// END-createKS-CQL
