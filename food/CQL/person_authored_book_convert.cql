USE food_cql_conversion;

// START-createTablePersonAuthoredBook_convert-cql

// Create table for conversion to edge label table using CQL:

CREATE TABLE food_cql_conversion.person_authored_book (
  person_name text,
  person_id UUID,
  book_name text,
  book_id int,
  PRIMARY KEY ((person_name, person_id), book_name, book_id)
);

// END-createTablePersonAuthoredBook_convert-cql
