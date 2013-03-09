package gpMethods

import spock.lang.Specification
import gpMethods.functions.*

class TreeToStringTest extends Specification {
	def varMap
	
	def setup() {
		varMap=["x":4, "y":5, "z":6]
	}
	
	def "test prefix toString"() {
		when:
			def tree1=new FunctionNode(function:new Modulus(),
									   children:[new ConstantNode(value:7), new ConstantNode(value:3)])
			println tree1.toString()
		
		then:
			tree1.toString() == "Mod[7, 3]"
	}
	
	def "test infix toString"() {
		when:
			def tree1=new FunctionNode(function:new Addition(),
									   children:[new FunctionNode(function:new Subtraction(),
											                      children:[new ConstantNode(value:7), new ConstantNode(value:3)]),
										         new FunctionNode(function:new Multiplication(),
											                      children:[new ConstantNode(value:2), new ConstantNode(value:3)])])
			println tree1.toString()
		
		then:
			tree1.toString() == "((7 - 3) + (2 * 3))"
	}
	
	def "test postfix toString"() {
		when:
			def tree1=new FunctionNode(function:new Square(),
									   children:[new ConstantNode(value:7)])
			println tree1.toString()
		
		then:
			tree1.toString() == "(7)^2"
	}
}
