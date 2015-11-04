/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

def parse(line, factory) {
    // Split out the vertex, outEdges and inEdges in each line of the 
    // CSV file - they are separated by tabs
    def (vertex, outEdges, inEdges) = line.split(/\t/, 3) 
    // Split out the vertex into the vertex id, the vertex label,
    // and the vertex properties - they are separated by commas
    def (v1id, v1label, v1props) = vertex.split(/,/, 3)
    // create a vertex variable, v1, that converts the vertex id to an
    // integer value and has an associated vertex label variable, v1label
    def v1 = factory.vertex(v1id.toInteger(), v1label)
    // use a different case to parse the vertex properties, depending
    // on what type of vertex it is
    switch (v1label) {
        case "human":
            // define the properties for the vertex type "human"
            def (name, house, gender, origin) = v1props.split(/,/)
            v1.property("name", name)
            v1.property("house", house)
            v1.property("gender", gender)
            v1.property("origin", origin)
            //v1.property("performances", performances.toInteger())
            break
        case "dragon":
            // define the properties for the vertex type "dragon"
	    def (name, colors) = v1props.split(/,/)
            v1.property("name", name)
            v1.property("colors", colors)
            break
        default:
            throw new Exception("Unexpected vertex label: ${v1label}")
    }
  //  [[outEdges, true], [inEdges, false]].each { def edges, def out ->
  //      edges.split(/\|/).grep().each { def edge ->
  //          def parts = edge.split(/,/)
  //         def otherV, eLabel, weight = null
  //          if (parts.size() == 2) {
  //             (eLabel, otherV) = parts
  //          } else {
  //              (eLabel, otherV, weight) = parts
  //          }
  //          def v2 = factory.vertex(otherV.toInteger())
  //          def e = factory.edge(out ? v1 : v2, out ? v2 : v1, eLabel)
  //          if (weight != null) e.property("weight", weight.toInteger())
        }
    }
    return v1
}
