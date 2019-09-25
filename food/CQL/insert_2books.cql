USE food_cql;

// START-insert2books-cql

// Insert book data using CQL:

INSERT INTO food_cql.book (name,book_id,publish_year,isbn,category) VALUES (
  'The French Chef Cookbook',
  1003,
  1968,
  '0-394-40135-2',
  {'French'}
);

INSERT INTO food_cql.book (name,book_id,publish_year,isbn,category) VALUES (
  $$Simca's Cuisine: 100 Classic French Recipes for Every Occasion$$,
  1002,
  1972,
  '0-394-40152-2',
  {'American','French'}
);

// END-insert2books-cql
