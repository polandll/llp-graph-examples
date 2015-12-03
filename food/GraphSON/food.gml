<?xml version='1.0' encoding='UTF-8'?>
<graphml xmlns="http://graphml.graphdrawing.org/xmlns" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://graphml.graphdrawing.org/xmlns http://graphml.graphdrawing.org/xmlns/1.1/graphml.xsd">
<key id="labelV" for="node" attr.name="labelV" attr.type="string"/>
<key id="authname" for="node" attr.name="authname" attr.type="string"/>
<key id="gender" for="node" attr.name="gender" attr.type="string"/>
<key id="revname" for="node" attr.name="revname" attr.type="string"/>
<key id="rname" for="node" attr.name="rname" attr.type="string"/>
<key id="iname" for="node" attr.name="iname" attr.type="string"/>
<key id="mname" for="node" attr.name="mname" attr.type="string"/>
<key id="btitle" for="node" attr.name="btitle" attr.type="string"/>
<key id="category" for="node" attr.name="category" attr.type="string"/>
<key id="mealCreationDate" for="node" attr.name="mealCreationDate" attr.type="string"/>
<key id="publicationDate" for="node" attr.name="publicationDate" attr.type="string"/>

<key id="labelE" for="edge" attr.name="labelE" attr.type="string"/>
<key id="stars" for="edge" attr.name="stars" attr.type="string"/>
<key id="ratedDate" for="edge" attr.name="ratedDate" attr.type="string"/>
<key id="createDate" for="edge" attr.name="createDate" attr.type="string"/>
<key id="amount" for="edge" attr.name="amount" attr.type="string"/>

<graph id="graph3" edgedefault="directed">

  <node id="100">
    <data key="labelV">author</data>
    <data key="authname">Julia Child</data>
    <data key="gender">F</data>
  </node>
  <node id="101">
    <data key="labelV">author</data>
    <data key="authname">Jamie King</data>
    <data key="gender">M</data>
  </node>
  <node id="102">
    <data key="labelV">recipe</data>
    <data key="rname">beef bourguignon</data>
  </node>
  <node id="103">
    <data key="labelV">ingredient</data>
    <data key="iname">beef</data>
  </node>
  <node id="104">
    <data key="labelV">ingredient</data>
    <data key="iname">carrots</data>
  </node>
  <node id="105">
    <data key="labelV">book</data>
    <data key="title">Mastering the Art of French Cooking (Vol. 1)</data>
    <data key="publicationDate">1961</data>
  </node>
  <edge id="50" source="100" target="102">
    <data key="labelE">hasCreated</data>
    <data key="createDate">1961</data>
  </edge>
  <edge id="51" source="100" target="105">
    <data key="labelE">hasAuthored</data>
  </edge>
  <edge id="52" source="103" target="102">
    <data key="labelE">hasIncluded</data>
    <data key="amount">2 lbs</data>
  </edge>
  <edge id="53" source="104" target="102">
    <data key="labelE">hasIncluded</data>
    <data key="amount">0.5 lbs</data>
  </edge>
</graph>
</graphml>
