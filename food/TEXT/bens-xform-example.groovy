/* Original data
{
   patient_id1: "a",
   patient_id2: "b",
   hospital_id1: "c",
   hospital_id2: "d"
}
*/

/* Transformed data:
{
   patient: { 
      id1: "a",
      id2: "b"
   },
   hospital: { 
      id1: "c",
      id2: "d"
   }
}
*/

//Configures the data loader to create the schema
config create_schema: true, load_new: true

hospital = File.text(inputfilename + "/hospitals.csv").delimiter(",")
patient = File.text(inputfilename + "/patient.csv").delimiter(",")
the_edges = File.text(inputfilename + "/hospital.csv").delimiter(",")

load(patient).asVertices {
    label "Patient"
    key "patient"
}

load(hospital).asVertices {
    label "Hospital"
    key "hospital"
}

the_edges = the_edges.transform {
    it['patient'] = [
            'id1' : it['patient_id_1'],
            'id2' : it['patient_id_2'] ];
    it['hospital'] = [
            'id1' : it['hospital_id_1'],
            'id2' : it['hospital_id_2']
    ];
    it
}

load(the_edges).asEdges  {
    label "visit"
    outV "patient", {
        label "Patient"
        key id1:"id1", id2:"id2"
    }
    inV "hospital", {
        label "Hospital"
        key id1:"id1", id2:"id2"
    }
}
