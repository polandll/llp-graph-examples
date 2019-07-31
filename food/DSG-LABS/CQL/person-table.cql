CREATE TABLE IF NOT EXISTS food.person ( 
   person_id UUID, 
   name text, 
   gender text,
   nickname set<text>,
   cal_goal int,
   macro_goal list<int>,
   badge map<text, int>,
   PRIMARY KEY (name, person_id)
) WITH CLUSTERING ORDER BY (person_id ASC) AND VERTEX LABEL;
