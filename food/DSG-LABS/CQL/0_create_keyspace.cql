CREATE KEYSPACE IF NOT EXISTS food_cql 
WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 };
