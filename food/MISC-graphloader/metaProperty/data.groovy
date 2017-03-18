// One way to add data
julia=graph.addVertex(label,'author', 'name', 'Julia Child')
props=julia.property(list,'country','France')
props.property('start_date', '1950-01-01')
props.property('end_date', '1960-12-31')
props2 = julia.property(list, 'country', 'USA')
props2.property('start_date', '1961-01-01')
props2.property('end_date', '1984-06-23')

// Another way to add data
g.addV('author').
  property('name', 'Emeril').
  property('country', 'France', 'start_date', '1970-04-05', 'end_date','1973-09-09').
  property('country', 'USA', 'start_date', '1973-02-02', 'end_date', '2017-03-01')
