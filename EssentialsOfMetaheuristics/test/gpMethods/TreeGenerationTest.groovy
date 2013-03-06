package gpMethods

import spock.lang.Specification

class TreeGenerationTest extends Specification{
	def varMap
	def plus
	def plus2
	def minus
	def times
	def square
	
	def setup(){
		varMap=["x":4, "y":5, "z":6]
		plus={x, y -> x + y}
		minus={x, y -> x - y}
		times={x, y -> x * y}
		plus2={x, y, z -> x + y + z}
		square={x -> x * x}
	}
	
	def "simple tree, all arity=2"(){
		when:
		def tree1=new FunctionNode(function:plus,
			children:[new FunctionNode(function:minus,
				children:[new ConstantNode(value:7), new ConstantNode(value:3)]),
			new FunctionNode(function:times,
				children:[new ConstantNode(value:2), new ConstantNode(value:3)])])
		
		then:
		tree1.eval(varMap)==10
	}
	
	def "super simple tree, all arity=1"(){
		when:
		def tree2=new FunctionNode(function:square,
			children:[new ConstantNode(value:2)])
		
		then:
		tree2.eval(varMap)==4
	}
	
	def "super simple tree, all arity=3"(){
		when:
		def tree2=new FunctionNode(function:plus2,
			children:[new ConstantNode(value:2),new ConstantNode(value:3),new ConstantNode(value:5)])
		
		then:
		tree2.eval(varMap)==10
	}
	
	def "simple tree, all arity=1"(){
		when:
		def tree3=new FunctionNode(function:square,
			children:[new FunctionNode(function:square,
				children:[new ConstantNode(value:2)])])
		
		then:
		tree3.eval(varMap)==16
	}
	
}