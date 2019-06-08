:remote config alias g multiCard.g
schema.config().option('graph.allow_scan').set('true')
g.V().outE()
//==>e[{~label=livedIn, ~out_vertex={~label=author, community_id=1328235264, member_id=0}, ~in_vertex={~label=city, community_id=1530484480, member_id=0}, ~local_id=5665a690-f949-11e6-992f-fd2bc8750177}][{~label=author, community_id=1328235264, member_id=0}-livedIn->{~label=city, community_id=1530484480, member_id=0}]
//==>e[{~label=livedIn, ~out_vertex={~label=author, community_id=1328235264, member_id=0}, ~in_vertex={~label=city, community_id=1530484480, member_id=1}, ~local_id=4a018040-f949-11e6-a82e-0928d7491218}][{~label=author, community_id=1328235264, member_id=0}-livedIn->{~label=city, community_id=1530484480, member_id=1}]
//==>e[{~label=livedIn, ~out_vertex={~label=author, community_id=1328235264, member_id=0}, ~in_vertex={~label=city, community_id=1530484480, member_id=2}, ~local_id=4b476dc1-f949-11e6-b88d-1962445f7559}][{~label=author, community_id=1328235264, member_id=0}-livedIn->{~label=city, community_id=1530484480, member_id=2}]
//==>e[{~label=livedIn, ~out_vertex={~label=author, community_id=1328235264, member_id=1}, ~in_vertex={~label=city, community_id=1530484480, member_id=0}, ~local_id=4b476dc0-f949-11e6-b88d-1962445f7559}][{~label=author, community_id=1328235264, member_id=1}-livedIn->{~label=city, community_id=1530484480, member_id=0}]

g.V().outE().has('dateStart', '1960-01-01')
//==>e[{~label=livedIn, ~out_vertex={~label=author, community_id=1328235264, member_id=1}, ~in_vertex={~label=city, community_id=1530484480, member_id=0}, ~local_id=4b476dc0-f949-11e6-b88d-1962445f7559}][{~label=author, community_id=1328235264, member_id=1}-livedIn->{~label=city, community_id=1530484480, member_id=0}]

g.V().outE().has('dateStart', '1960-01-01').outV()
//==>v[{~label=author, community_id=1328235264, member_id=1}]

g.V().outE().has('dateStart', '1960-01-01').outV().valueMap()
//==>{author=[Simone Beck]}

g.V().outE().has('dateStart', gt('1960-01-01')).outV().valueMap()
//==>{author=[Julia Child]}
//==>{author=[Julia Child]}
//==>{author=[Julia Child]}
