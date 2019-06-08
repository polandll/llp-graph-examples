CREATE TABLE food_cql_conversion.person_authored_book (
    person_name text,
    person_id UUID,
    book_name text,
    book_id int,
    PRIMARY KEY ((person_name, person_id), book_name, book_id)
) WITH CLUSTERING ORDER BY (book_name ASC, book_id ASC)
    AND EDGE LABEL authored 
      FROM person(name, person_id) 
      TO book(name, book_id);
