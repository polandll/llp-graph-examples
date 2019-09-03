### Using the DataStax Bulk Loader to insert data into CQL

Data can be loaded with DataStax Bulk Loader using CSV or JSON input files (see the next cell for CSV files that will be loaded). 

The following commands should be run from a terminal on a cluster node that has `dsbulk` installed.

Load data to `person` table from a CSV  file with a pipe delimiter and allow missing field values:

    dsbulk load --schema.keyspace food_cql --schema.table person -url data/vertices/person.csv -delim '|' --schema.allowMissingFields true

Load data to `book` table, identifying the field -> columns with schema mapping:

    dsbulk load -k food_cql -t book -url data/vertices/book.csv --schema.mapping '0=book_id, 1=name, 2=publish_year 3=isbn' -delim '|' --schema.allowMissingFields true

Load data to `person_authored_book` table from a CSV file:

    dsbulk load -k food_cql -t person_authored_book -url data/edges/person_authored_book.csv -m '0=lastname, 1=person_id, 2=person_name, 3=book_id 4=book_name' -delim '|' --schema.allowMissingFields true

More information about using DataStax Bulk Loader can be found in the documentation: [https://docs.datastax.com/en/dsbulk/doc/index.html] (https://docs.datastax.com/en/dsbulk/doc/index.html)

[Top &#8593;](#sections)
