package gpMethods

import spock.lang.Specification
import gpMethods.functions.numeric.*

class SingleEvalTest extends Specification {
	def varMap1
	
	def setup(){
		varMap1=["x":3, "y":4, "z":5]
	}
	
	def "constantNode eval returns a constant"(){
		when:
			def cNode1=new ConstantNode(value:5)
		
		then:
			cNode1.eval(varMap1)==5
	}
	
	def "variableNode eval returns the given variable from the map"(){
		when:
			def vNode1=new VariableNode(variable:"x")
			def vNode2=new VariableNode(variable:"y")
		
		then:
			vNode1.eval(varMap1)==3
			vNode2.eval(varMap1)==4
	}
	
	def "functionNode eval returns the result of the function called on the children"(){
		when:
			def cNode1=new ConstantNode(value:5)
			def cNode2=new ConstantNode(value:7)
			def vNode1=new VariableNode(variable:"x")
			def vNode2=new VariableNode(variable:"z")
			def fNode1=new FunctionNode(function:new Addition(), children:[cNode1, cNode2])
			def fNode2=new FunctionNode(function:new Multiplication(), children:[vNode1, vNode2])
			def fNode3=new FunctionNode(function:new Function(function: {x, y, z -> (x + y) * z}), children:[cNode1, vNode1, cNode2])
		
		then:
			fNode1.eval(varMap1)==12
			fNode2.eval(varMap1)==15
			fNode3.eval(varMap1)==56
	}
}