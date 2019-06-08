// SAMPLE DATA:
// personInput includes type of person, name, gender
// type can be either author or reviewer
// sample data in file people.dat:
// author::Julia Child::F
// reviewer::Jane Doe::F

config create_schema: true
config load_new: true
personInput = File.text('people.dat').delimiter("::").header('type','name','gender')


load(personInput).asVertices{
	labelField "type"
	key "name"
//	ignore "gender"
}
