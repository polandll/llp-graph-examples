// I TOOK OUT CLUSTERING ORDER - FIGURE OUT IF THAT NEEDS TO BE IN THERE

CREATE TABLE IF NOT EXISTS food_cql.person_authored_book (
    person_id UUID,
    lastname text,
    name text,
    book_id int,
    PRIMARY KEY ( lastname, person_id, name, book_id)
) WITH EDGE LABEL created
      FROM person(lastname, person_id)
      TO book(name, book_id);

INSERT INTO food_cql.person_authored_book 
  (lastname,person_id, name, book_id) 
  VALUES ('CHILD', bb6d7af7-f674-4de7-8b4c-c0fdcdaa5cca, 'The French Chef Cookbook', 1003);
INSERT INTO food_cql.person_authored_book
  (lastname,person_id, name, book_id)
  VALUES ('BECK', adb8744c-d015-4d78-918a-d7f062c59e8f, $$Simca's Cuisine: 100 Classic French Recipes for Every Occasion$$, 1002);
