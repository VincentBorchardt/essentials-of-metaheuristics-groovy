package gpMethods

import spock.lang.Specification
import gpMethods.functions.*

class TreeGenerationTest extends Specification{
	def varMap
	
	def setup(){
		varMap=["x":4, "y":5, "z":6]
	}
	
	def "simple tree, all arity=2"(){
		when:
		def tree1=new FunctionNode(function:new Addition(),
			children:[new FunctionNode(function:new Subtraction(),
				children:[new ConstantNode(value:7), new ConstantNode(value:3)]),
			new FunctionNode(function:new Multiplication(),
				children:[new ConstantNode(value:2), new ConstantNode(value:3)])])
		
		then:
		tree1.eval(varMap)==10
	}
	
	def "super simple tree, all arity=1"(){
		when:
		def tree2=new FunctionNode(function:new Square(),
			children:[new ConstantNode(value:2)])
		
		then:
		tree2.eval(varMap)==4
	}
	
	def "simple tree, all arity=1"(){
		when:
		def tree3=new FunctionNode(function:new Square(),
			children:[new FunctionNode(function:new Square(),
				children:[new ConstantNode(value:2)])])
		
		then:
		tree3.eval(varMap)==16
	}
	
}