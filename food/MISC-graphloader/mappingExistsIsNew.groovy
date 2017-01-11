// Mapping script fragments for:
// exists()
// isNew()


load(authorBookInput).asEdges {
    label "authored"
    outV "aname", { exists()
        label "author"
        key "name"
    }
    inV "bname", {
        label "book"
        key "name"
    }
}

load(authorBookInput).asEdges {
    isNew()
    label "authored"
    outV "aname", { exists()
        label "author"
        key "name"
    }
    inV "bname", {
        label "book"
        key "name"
    }
}
