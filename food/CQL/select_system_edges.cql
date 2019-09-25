// START-selectSystemEdges-cql

// Look at the created edge labels using CQL
select * from system_schema.edges;

// Look at the created edge labels using CQL
select * from system_schema.edges WHERE keyspace_name='food';

// END-selectSystemEdges-cql
