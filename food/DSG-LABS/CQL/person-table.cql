CREATE TABLE IF NOT EXISTS food.person ( 
   personid UUID, 
   lastname text, 
   firstname text,
   gender text,
   nickname set<text>,
   calgoal int,
   macrogoal list<int>,
   PRIMARY KEY (lastname, personid));
