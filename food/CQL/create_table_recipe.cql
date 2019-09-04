// Assume we have the following existing non-vertex label table.
// Create a CQL table that can be converted into a vertex label

CREATE KEYSPACE IF NOT EXISTS food
WITH REPLICATION = {
   'class' : 'SimpleStrategy',
   'replication_factor' : 1 };

USE food;
// START-createTable-recipe-cql
CREATE TABLE food.recipe_table (
   recipe_id int,
   name text,
   cuisine set<text>,
   instructions text,
   notes text,
   PRIMARY KEY (recipe_id));
// END-createTable-recipe-cql
