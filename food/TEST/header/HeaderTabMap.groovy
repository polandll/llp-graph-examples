/** SAMPLE INPUT
See input1t.csv, input2t.csv, input3t.txt, input4t.txt
All four files are comma-delimited.
**/

// CONFIGURATION
// Configures the data loader to create the schema
config create_schema: true, load_new: true

// DATA INPUT

inputfiledir = '/home/automaton/graph-examples/food/TEST/header/'
// NO HEADER IN FILE
input1 = File.csv(inputfiledir + "input1t.csv").delimiter('\t')
// NO HEADER IN FILE
input5 = File.csv(inputfiledir + "input1t.csv").delimiter('\t').header('name','age')
// HEADER IN FILE
input2 = File.csv(inputfiledir + "input2t.csv").delimiter('\t')
// HEADER IN FILE
input6 = File.csv(inputfiledir + "input2t.csv").delimiter('\t').header('name','age')

// NO HEADER IN FILE
input3 = File.text(inputfiledir + "input3t.txt").delimiter('\t')
// NO HEADER IN FILE
input7 = File.text(inputfiledir + "input3t.txt").delimiter('\t').header('name','age')
// HEADER IN FILE
input4 = File.text(inputfiledir + "input4t.txt").delimiter('\t')
// HEADER IN FILE
input8 = File.text(inputfiledir + "input4t.txt").delimiter('\t').header('name','age')

//Specifies what data source to load using which mapper (as defined inline)
/*  
// DOES NOT WORK 
load(input1).asVertices {
    label "person1"
    key "name"
}

// DOES NOT WORK
load(input2).asVertices {
    label "person2"
    key "name"
}

// DOES NOT WORK
load(input3).asVertices {
    label "person3"
    key "name"
}

// DOES NOT WORK
load(input4).asVertices {
    label "person4"
    key "name"
}

// WORKS
load(input5).asVertices {
    label "person5"
    key "name"
}

// WORKS, BUT MAKES A VERTEX FOR NAME,AGE
load(input6).asVertices {
    label "person6"
    key "name"
}

// DOES NOT WORK
load(input7).asVertices {
    label "person7"
    key "name"
}

// WORKS 
load(input8).asVertices {
    label "person8"
    key "name"
}

// Does not work
load(input9).asVertices {
    label "person9"
    key "name"
}

// Works as expected 
load(input10).asVertices {
    label "person10"
    key "name"
}
*/

// TESTER
load(input8).asVertices {
    label "person8"
    key "name"
}
