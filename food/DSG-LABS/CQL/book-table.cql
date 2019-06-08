CREATE TABLE IF NOT EXISTS food.book ( 
   bookid int, 
   name text,
   authors list<frozen<fullname>>,
   publishYear int,
   isbn text,
   category set<text>,
   PRIMARY KEY (name, bookid));
