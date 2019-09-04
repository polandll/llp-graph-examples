USE food_cql;

// START-createUDTs-cql
CREATE TYPE IF NOT EXISTS food_cql.address (
   address1 text,
   address2 text,
   city_code text,
   zip_code text);
   
CREATE TYPE IF NOT EXISTS food_cql.fullname (
   lastname text,
   firstname text);
   

//Using a nested UDT in another UDT:

CREATE TYPE IF NOT EXISTS food_cql.location_details (
   loc_address frozen<address>,
   telephone list<text>
);
// END-createUDTs-cql
