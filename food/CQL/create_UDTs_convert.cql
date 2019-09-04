USE food_cql_conversion;

// START-createUDTs-convert
CREATE TYPE IF NOT EXISTS food_cql_conversion.address (
   address1 text,
   address2 text,
   city_code text,
   zip_code text);
   
CREATE TYPE IF NOT EXISTS food_cql_conversion.fullname (
   lastname text,
   firstname text);
   

//Using a nested UDT in another UDT:

CREATE TYPE IF NOT EXISTS food_cql_conversion.location_details (
   loc_address frozen<address>,
   telephone list<text>
);
// END-createUDTs-convert
