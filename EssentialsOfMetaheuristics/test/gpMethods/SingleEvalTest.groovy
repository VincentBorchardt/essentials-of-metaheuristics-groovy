package gpMethods

import spock.lang.Specification

class SingleEvalTest extends Specification{
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
			def fNode1=new FunctionNode(function:{x, y -> x + y}, children:[cNode1, cNode2])
			def fNode2=new FunctionNode(function:{x, y -> x * y}, children:[vNode1, vNode2])
			def fNode3=new FunctionNode(function:{x, y, z -> (x + y) * z}, children:[cNode1, vNode1, cNode2])
		
		then:
			fNode1.eval(varMap1)==12
			fNode2.eval(varMap1)==15
			fNode3.eval(varMap1)==56
	}
	
	def "functionNode works properly with static Functions"() {
		when:
			def cNode1=new ConstantNode(value:5)
			def cNode2=new ConstantNode(value:7)
			def cNode3=new ConstantNode(value:0)
			def fNode1 = new FunctionNode(function:Functions.multiplication, children:[cNode1, cNode2])
			def fNode2 = new FunctionNode(function:Functions.multiplication, children:[cNode1, cNode3])
			def fNode3 = new FunctionNode(function:Functions.protectedMultiplication, children:[cNode1, cNode3])
			def fNode4 = new FunctionNode(function:Functions.protectedMultiplication, children:[cNode3, cNode2])
			def fNode5 = new FunctionNode(function:Functions.division, children:[cNode1, cNode2])
			def fNode6 = new FunctionNode(function:Functions.division, children:[cNode1, cNode3])
			def fNode7 = new FunctionNode(function:Functions.subtraction, children:[cNode2, cNode1])
			def fNode8 = new FunctionNode(function:Functions.pow, children:[cNode1, cNode2])
			def fNode9 = new FunctionNode(function:Functions.pow, children:[cNode3, cNode1])
			
		then:
			fNode1.eval(varMap1) == 35
			fNode2.eval(varMap1) == 0
			fNode3.eval(varMap1) == 5
			fNode4.eval(varMap1) == 7
			fNode5.eval(varMap1) == 5 / 7
			fNode6.eval(varMap1) == 5
			fNode7.eval(varMap1) == 2
			fNode8.eval(varMap1) == Math.pow(5, 7)
			fNode9.eval(varMap1) == 1
	}

}
