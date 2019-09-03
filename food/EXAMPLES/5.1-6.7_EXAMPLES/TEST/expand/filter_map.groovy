input = File.csv("/Users/lorinapoland/CLONES/graph-examples/food/TEST/expand/alice-bob.csv").delimiter(",").expand {
  it["age"] = it["age"].toInteger()
  return it["age"] <= 30 ? [it] : []
}

load(input).asVertices {
  label "person"
  key "name"
}
