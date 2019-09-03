## Indexing ##

Indexes are required for many graph queries. For vertices, if a query uses any property besides the partition key, an index is required. In addition, edges are unidirectional, so if a vertex requires both _in()_ and _out()_, an index will be required. The schema step _indexFor()_ can analyze for necessary indexes, using the traversal that is desired. Both an _analyze()_ and an _apply()_ option are available, to first examine the suggested index, and also to apply it.

Currently, materialized view and search indexes will be suggested with _indexFor()_. Secondary indexes, materialized view indexes, and searches indexes can also be created manually.

[Top &#8593;](#sections)
