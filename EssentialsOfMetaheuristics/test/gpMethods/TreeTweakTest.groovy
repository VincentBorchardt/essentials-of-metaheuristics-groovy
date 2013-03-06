package gpMethods

import problems.GPProblem
import gpMethods.ConstantNode
import spock.lang.Specification

class TreeTweakTest extends Specification {
	def vList
	def cList
	def fList
	def fList2
	def treeGen
	def treeGen2
	def varMap
	def problem
	def initialTree
	
	def setup(){
		vList=["x", "y", "z"]
		cList=[0, 1, 2, 3, 4, 5]
		fList=[{x, y -> x + y}, {x, y -> x - y}]
		fList2=[{x -> Math.pow(x, 2)}, {x, y -> x + y}, {x, y, z -> Math.max(Math.max(x, y), z)}]
		treeGen=new TreeGenerator(variableList:vList, constantList:cList, functionList:fList)
		//treeGen2=new TreeGenerator(variableList:vList, constantList:cList, functionList:fList2)
		problem = new GPProblem(variableList:vList, constantList:cList, functionList:fList)
		varMap=["x":6, "y":7, "z":8]
		initialTree=problem.create()
	}
	
	def "mutating a constant node"(){
		when:
			def cNode = new ConstantNode(value: 5)
			problem.tweak(cNode, 1)
		then:
			0 == 0
	}
	
	def "mutating a variable node"(){
		when:
			def vNode = new VariableNode(variable: "x")
			problem.tweak(vNode, 1)
		then:
			0 == 0
	}
	
	def "mutating a function node"(){
		when:
			def cNode1 = new ConstantNode(value:1)
			def cNode2 = new ConstantNode(value:2)
			def fNode = new FunctionNode(function:{x, y -> x + y}, children:[cNode1, cNode2])
			problem.tweak(fNode, 0.5)
		then:
			0 == 0
	}
}