// Look at the created UDTs using CQL
// There is not currently a way to look at just UDTs with Gremlin

select * from system_schema.types WHERE keyspace_name='food_cql';
