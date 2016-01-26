:remote connect tinkerpop.server conf/remote-objects.yaml
:> system.dropGraph('test')
:> system.createGraph("test").ifNotExist().build()
:remote config alias g test.g