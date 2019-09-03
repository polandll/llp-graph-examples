// Find the properties (countries)
g.V().properties()

// Find the properties of the properties (start_dates and end_dates)
g.V().properties().properties()

// Find the values for the start_date/end_date for all France entries
g.V().properties('country').hasValue('France').properties().value()

// Find the start_date for each country, for each author
g.V().as('author').
    properties('country').as('country').
    has('start_date').as('start_living_in').
select('author','country','start_living_in').
    by('name').
    by(value).
    by('start_date')
