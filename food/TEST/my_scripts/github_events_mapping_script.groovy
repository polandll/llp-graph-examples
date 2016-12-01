path_to_github_data = "/users/lorinapoland/CLONES/dse-graph-loader-5.0.3-SNAPSHOT/my_scripts/github_events1000.json"
github_data = File.json(path_to_github_data).transform{
	// for action edgeLabel
	edge_verb = it["type"].split("Event")[0] + "ed";
	it["action_keys"] = [:];
	it["action_keys"]["action"] = edge_verb;
	it["action_keys"]["actor"] = it["actor"];
	// for was_performed_on edgeLabel
	it["performed_on_keys"] = [:];
	it["performed_on_keys"]["repo"] = it["repo"];
	it["performed_on_keys"]["public"] = it["public"];
	it["performed_on_keys"]["payload"] = it["payload"];
	it;
}

// data mapper
eventV = {
	labelField "type"
	key "id"
	// User -(?)-> Event edgeLabel
	inE "action_keys", {
		labelField "action"
		vertex "actor", {
			label "User"
			key "id"
		}
	}
	// Event -(was_performed_on)-> Repository
	outE "performed_on_keys", "was_performed_on", {
		vertex "repo", {
			label "Repository"
			key "id"
		}
	}
	// ignore these keys, since we did transforms
	ignore "repo"
	ignore "public"
	ignore "payload"
	ignore "actor"
	ignore "org"
}

// load the data
load(github_data).asVertices(eventV)
