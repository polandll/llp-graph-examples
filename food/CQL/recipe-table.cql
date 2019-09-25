USE food_cql;

// START-createTableRecipe-cql

// Assume we have the following existing non-vertex label table.
// Create a CQL table that can be converted into a vertex label

 CREATE TABLE food_cql.recipe_table (
   recipe_id int,
   name text,
   cuisine set<text>,
   instructions text,
   notes text,
   PRIMARY KEY (recipe_id));

// END-createTableRecipe-cql
