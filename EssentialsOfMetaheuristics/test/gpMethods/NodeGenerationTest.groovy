package gpMethods

import spock.lang.Specification
import gpMethods.functions.*
import java.lang.Math

class NodeGenerationTest extends Specification{
	def vList
	def cList
	def fList
	def fList2
	def treeGen
	def treeGen2
	def varMap
	
	def setup(){
		vList=["x", "y", "z"]
		cList=[0, 1, 2, 3, 4, 5]
		fList=[new Addition(), new Subtraction()]
		fList2=[new Square(), new Addition(), new Function(function: {x, y, z -> Math.max(Math.max(x, y), z)}, name: "arity 3 max")]
		treeGen=new TreeGenerator(variableList:vList, constantList:cList, functionList:fList)
		treeGen2=new TreeGenerator(variableList:vList, constantList:cList, functionList:fList2)
		varMap=["x":6, "y":7, "z":8]
	}
	
	def "generateConstantNode creates a constant node from the cList"(){
		when:
			def node1=treeGen.generateConstantNode()
		
		then:
			//System.out.println(node1)
			node1.class==ConstantNode
			cList.contains(node1.eval(varMap))
	}
	
	def "generateVariableNode creates a variable node from the vList"(){
		when:
			def node2=treeGen.generateVariableNode()
		
		then:
			//System.out.println(node2)
			node2.class==VariableNode
			[6, 7, 8].contains(node2.eval(varMap))
	}
	
	def "generateFunctionNode creates a function node"(){
		when:
			def node3=treeGen.generateFunctionNode()
		
		then:
			//System.out.println(node3)
			node3.class==FunctionNode
			//node3.eval(varMap) != null
	}
	
	def "functionNodes evaluate properly"() {
		when:
			System.out.println("Starting functionNode evaluation test")
			def node3=treeGen2.generateFunctionNode()
			
		then:
			System.out.println(node3)
			node3.eval(varMap) != null
	}
	
	def "generateNode creates a new Node"(){
		when:
			def node4 = treeGen.generateNode()
		
		then:
			node4.class == ConstantNode || node4.class == VariableNode || node4.class == FunctionNode
	}
	
	def "generateNonFunctionNode does not create a function node"() {
		when:
			def node5 = treeGen.generateNonFunctionNode()
			
		then:
			node5.class == ConstantNode || node5.class == VariableNode
	}

}
