USE food_cql;

// START-createTablePersonAuthoredBookAsEL-cql

// Create edge label table using CQL

CREATE TABLE IF NOT EXISTS food_cql.person_authored_book (
    person_id UUID,
    person_name text,
    book_id int,
    book_name text,
    PRIMARY KEY ( (person_name, person_id) , book_name, book_id)
) WITH EDGE LABEL person_authored_book
      FROM person(name, person_id)
      TO book(name, book_id);

// END-createTablePersonAuthoredBookAsEL-cql
