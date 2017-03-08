:remote config alias g DSEQuickStart.g
schema.config().option('graph.allow_scan').set('true')

g.V().hasLabel('recipe').has('name', Search.prefix('R')).values('name')
g.V().hasLabel('recipe').has('name', Search.regex('.*ee.*')).values('name')
g.V().hasLabel('recipe').has('name', eq('Carrot Soup')).values('name')
g.V().hasLabel('recipe').has('name', eq('Carrot')).valueMap()
g.V().hasLabel('recipe').has('name', neq('Carrot')).values('name')
g.V().has('recipe','instructions', Search.token('Saute')).values('name')
g.V().hasLabel('recipe').has('instructions', Search.tokenPrefix('Sea')).values('name','instructions')
g.V().hasLabel('recipe').has('instructions', Search.tokenRegex('.*sea.*in.*')).values('name','instructions')
