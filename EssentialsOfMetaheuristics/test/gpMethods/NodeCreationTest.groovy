package gpMethods

import spock.lang.Specification

class NodeCreationTest extends Specification {
	
	def "able to create constant node"() {
		expect:
			def cNode1 =  new ConstantNode(value:3)
			cNode1.value==3
	}
	
	def "able to create function node"() {
		when:
			def cNode1 = new ConstantNode(value:1)
			def cNode2 = new ConstantNode(value:2)
			def fNode1 = new FunctionNode(function:{x, y -> x + y}, children:[cNode1, cNode2])
		then:
			fNode1.children == [cNode1, cNode2]
			fNode1.function(3, 6) == {x, y -> x + y}(3, 6)
			fNode1.arity == 2 //initially the arity is null
			fNode1.arity == 2 //after it is set
	}

	def "able to create variable node"() {
		when:
			def vNode1 = new VariableNode(variable: "x")
		then:
			vNode1.variable == "x"
	}
	
}