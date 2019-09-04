// START-createTable-person-asCQL
CREATE TABLE IF NOT EXISTS food_cql.person ( 
   person_id UUID, 
   name text, 
   gender text,
   nickname set<text>,
   cal_goal int,
   macro_goal list<int>,
   badge map<text, date>,
   PRIMARY KEY (name, person_id)
) WITH CLUSTERING ORDER BY (person_id ASC) AND VERTEX LABEL;
// END-createTable-person-asCQL
