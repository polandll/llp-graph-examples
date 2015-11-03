<?xml version='1.0' encoding='UTF-8'?>
<graphml xmlns="http://graphml.graphdrawing.org/xmlns" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://graphml.graphdrawing.org/xmlns http://graphml.graphdrawing.org/xmlns/1.1/graphml.xsd">
<key id="labelV" for="node" attr.name="labelV" attr.type="string"/>
<key id="gender" for="node" attr.name="gender" attr.type="string"/>
<key id="origin" for="node" attr.name="origin" attr.type="string"/>
<key id="name" for="node" attr.name="name" attr.type="string"/>
<key id="house" for="node" attr.name="house" attr.type="string"/>
<key id="colors" for="node" attr.name="colors" attr.type="string"/>
<key id="labelE" for="edge" attr.name="labelE" attr.type="string"/>
<graph id="G" edgedefault="directed">
<node id="100"><data key="labelV">human</data>
<data key="gender">F</data><data key="origin">Dragonstone</data><data key="name">Daenerys</data><data key="house">Targaryen</data></node>
<node id="101"><data key="labelV">human</data>
<data key="gender">M</data><data key="origin">King's Landing</data><data key="name">Viserys</data><data key="house">Targaryen</data></node>
<node id="102"><data key="labelV">human</data>
<data key="gender">M</data><data key="origin">King's Landing</data><data key="name">The King</data><data key="house">Targaryen</data></node>
<node id="103"><data key="labelV">human</data>
<data key="gender">M</data><data key="origin">Vaes Dothrak</data><data key="name">Drogo</data><data key="house">Dothraki</data></node>
<node id="104"><data key="labelV">dragon</data>
<data key="name">Drogon</data><data key="colors">blackand red</data></node>
<node id="105"><data key="labelV">dragon</data>
<data key="name">Viserion</data><data key="colors">gold and red-orange</data></node>
<node id="106"><data key="labelV">dragon</data>
<data key="name">Rhaegal</data><data key="colors">green bronze and yellow-orange</data></node>
<edge id="50" source="100" target="104">
<data key="labelE">hatchedBy</data></edge>
<edge id="51" source="100" target="105">
<data key="labelE">hatchedBy</data></edge>
<edge id="52" source="100" target="106">
<data key="labelE">hatchedBy</data></edge>
<edge id="53" source="100" target="101">
<data key="labelE">sister</data></edge>
<edge id="54" source="100" target="102">
<data key="labelE">daughter</data></edge>
<edge id="55" source="101" target="100">
<data key="labelE">brother</data></edge>
<edge id="56" source="101" target="102">
<data key="labelE">son</data></edge>
</graph></graphml>
