USE food_cql;

// START-insert1Edge-cql

// Insert 1 person_authored_book edge using CQL:

INSERT INTO food_cql.person_authored_book (person_id, person_name, book_id, book_name) VALUES (
   bb6d7af7-f674-4de7-8b4c-c0fdcdaa5cca,
   'Julia CHILD',
   1003,
   'The French Chef Cookbook'
);

// END-insert1Edge-cql
