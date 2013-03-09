package gpMethods

import spock.lang.Specification
import gpMethods.functions.Addition

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
			def fNode1 = new FunctionNode(function:new Addition(), children:[cNode1, cNode2])
		then:
			fNode1.children == [cNode1, cNode2]
			fNode1.function.function(3, 6) == {x, y -> x + y}(3, 6)
			fNode1.arity == 2 //initially the arity is null
			fNode1.arity == 2 //after it is set
	}

	def "able to create variable node"() {
		when:
			def vNode1 = new VariableNode(variable: "x")
		then:
			vNode1.variable == "x"
	}
	
	def "cloning a constant node"() {
		when:
			def cNode = new ConstantNode(value: 10)
		then:
			cNode.clone().value == 10
	}
	
	def "cloning a variable node"() {
		when:
			def vNode = new VariableNode(variable: "x")
		then:
			vNode.clone().variable == "x"
	}
	
	def "cloning a function node"() {
		when:
			def cNode1 = new ConstantNode(value:1)
			def cNode2 = new ConstantNode(value:2)
			def fNode = new FunctionNode(function:new Addition(), children:[cNode1, cNode2])
			println fNode
		then:
			fNode.eval() == fNode.clone().eval()
	}
	
}