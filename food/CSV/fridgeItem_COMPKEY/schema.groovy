// SCHEMA
// Example of custom vertex id:
// PROPERTIES
schema.propertyKey('fridgeItem').Text().create()
schema.propertyKey('city_id').Text().create()
schema.propertyKey('sensor_id').Uuid().create()
schema.propertyKey('location').Point().create()
// VERTEX LABELS
schema.vertexLabel('ingredient').create()
schema.vertexLabel('FridgeSensor').partitionKey('city_id').clusteringKey('sensor_id').create()
schema.vertexLabel('FridgeSensor').properties('fridgeItem', 'location').add()
// EDGE LABELS
schema.edgeLabel('isIn').connection('ingredient','FridgeSensor').create()
