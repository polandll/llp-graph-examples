USE food_cql;

// START-insert3persons-cql

// Insert person data using CQL:

INSERT INTO food_cql.person (person_id, name, gender) VALUES (
   bb6d7af7-f674-4de7-8b4c-c0fdcdaa5cca,
   'Julia CHILD',
   'F'
);

INSERT INTO food_cql.person (person_id, name, gender, nickname) VALUES (
   adb8744c-d015-4d78-918a-d7f062c59e8f,
   'Simone BECK',
   'F',
   {'Simca'}
);
INSERT INTO food_cql.person (person_id, name, gender) VALUES (
   888ad970-0efc-4e2c-b234-b6a71c30efb5,
   'Fritz STREIFF',

   'M'
);

// END-insert3persons-cql
