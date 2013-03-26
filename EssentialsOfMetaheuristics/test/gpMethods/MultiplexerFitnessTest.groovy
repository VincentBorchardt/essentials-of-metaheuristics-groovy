package gpMethods

import problems.GP.Multiplexer11Bit
import spock.lang.Specification
import gpMethods.functions.bool.*

class MultiplexerFitnessText extends Specification {
	def variableList
	def functionList
	def problem
	def a0
	def a1
	def a2
	def d0
	def d1
	def d2
	def d3
	def d4
	def d5
	def d6
	def d7
	def tree
	
	
	def setup(){
		variableList = ["a0", "a1", "a2", "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7"]
		functionList = [new BoolIf()]
		problem = new Multiplexer11Bit(variableList:variableList, functionList:functionList, constantList:[])
		a0=new VariableNode(variable:"a0")
		a1=new VariableNode(variable:"a1")
		a2=new VariableNode(variable:"a2")
		d0=new VariableNode(variable:"d0")
		d1=new VariableNode(variable:"d1")
		d2=new VariableNode(variable:"d2")
		d3=new VariableNode(variable:"d3")
		d4=new VariableNode(variable:"d4")
		d5=new VariableNode(variable:"d5")
		d6=new VariableNode(variable:"d6")
		d7=new VariableNode(variable:"d7")
	}
	
	def "test tree of a single node"(){
		//d0
		when:
			tree=d0
		
		then:
			problem.quality(tree)==1152
			
	}
	
	def "test tree correct for single bit"(){
		//if a0 then d1 else d0
		when:
			tree=new FunctionNode(function:new BoolIf(), children:[a0,d1,d0])
			
		
		then:
			problem.quality(tree)==1280
			
	}
	
	def "test tree correct for two bits"(){
		//if a0 then (if a1 then d3 else d1) else (if a1 then d2 else d0)
		when:
			def node1 = new FunctionNode(function:new BoolIf(), children:[a1,d3,d1])
			def node2 = new FunctionNode(function:new BoolIf(), children:[a1,d2,d0])
			tree=new FunctionNode(function:new BoolIf(), children:[a0,node1,node2])
			
		
		then:
			problem.quality(tree)==1536
	}
	
	def "test tree that should have a perfect fitness"(){
		//if a0 then (if a1 then (if a2 then d7 else d3) else (if a2 then d5 else d1)) else (if a1 then (if a2 then d6 else d2) else (if a2 then d4 else d0))
		when:
			def node11 = new FunctionNode(function:new BoolIf(), children:[a2,d7,d3])
			def node12 = new FunctionNode(function:new BoolIf(), children:[a2,d5,d1])
			def node21 = new FunctionNode(function:new BoolIf(), children:[a2,d6,d2])
			def node22 = new FunctionNode(function:new BoolIf(), children:[a2,d4,d0])
			def node1 = new FunctionNode(function:new BoolIf(), children:[a1,node11,node12])
			def node2 = new FunctionNode(function:new BoolIf(), children:[a1,node21,node22])
			tree=new FunctionNode(function:new BoolIf(), children:[a0,node1,node2])
		
		then:
			problem.quality(tree)==2048
	}
}