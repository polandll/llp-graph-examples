// Alter all tables with either VERTEX LABEL or EDGE LABEL using CQL:

ALTER TABLE food_cql_conversion.person WITH VERTEX LABEL "person";
ALTER TABLE food_cql_conversion.book WITH VERTEX LABEL "book";
ALTER TABLE food_cql_conversion.person_authored_book 
  WITH EDGE LABEL "authored"
  FROM person(person_name, person_id)
  TO book(book_name, book_id);
