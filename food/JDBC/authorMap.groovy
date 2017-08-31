//Configures the data loader to create the schema
config create_schema: false, load_new: true

    // Don't forget to add the jdbc driver to the loader classpath (loader root directory)
    db = Database.connection('jdbc:mysql://localhost/jdbcmysql').user('root').password('foo').MySQL();//driver('com.mysql.jdbc.Driver');

    author = db.query 'select * from author'
    book = db.query 'select * from book'
    authorbook = db.query 'select * from authorbook'

load(author).asVertices {
    label 'author'
    key 'name'
}
/*
load(customers).asVertices {
    label 'address'
    key address: 'address', postalcode: 'postalcode'
    ignore 'customerid'
    ignore 'firstname' 
    ignore 'lastname'
    ignore 'email'
    ignore 'phone'
    ignore 'createdtime'
}

load(sessions).asVertices {
    label 'session'
    key 'sessionid'
}


load(customerOrders).asEdges {
    label 'places'
    outV 'customerid', {
        label 'customer'
        key 'customerid'
    }
    inV 'orderid', {
        label 'order'
        key 'orderid'
    }
}
*/
