// START-createTable-book-asCQL
CREATE TABLE IF NOT EXISTS food_cql.book ( 
   book_id int, 
   name text,
   authors list<frozen<fullname>>,
   publish_year int,
   isbn text,
   category set<text>,
   PRIMARY KEY (name, book_id)
) WITH CLUSTERING ORDER BY (book_id ASC) AND VERTEX LABEL;
// END-createTable-book-asCQL
