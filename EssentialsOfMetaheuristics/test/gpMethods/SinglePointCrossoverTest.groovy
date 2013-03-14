package gpMethods

import operators.GPOnePointCrossover;
import spock.lang.Specification
import gpMethods.functions.*
import gpMethods.FunctionNode

class SinglePointCrossoverTest extends Specification {
	def crossover
	def varMap
	
	def setup() {
		varMap=["x":4, "y":5, "z":6]
		crossover = new GPOnePointCrossover().crossover
	}
	
	def "brute force testing clone in crossover to make sure it doesn't break the world"() {
		when:
			def fNode1 = new FunctionNode(function:new Addition(),
									  	  children:[new FunctionNode(function:new Subtraction(),
											                         children:[new ConstantNode(value:7), new ConstantNode(value:3)]),
										            new FunctionNode(function:new Multiplication(),
											                         children:[new ConstantNode(value:2), new ConstantNode(value:3)])])
			def fNode2 = fNode1.clone()
			//println "starting crossover"
			
			//brute force testing
			100.times{
				//println "attempting to break the world again"
				def crossoverResults=crossover(fNode1, fNode2, 0.3)
				//println "1st Node Depth = " + fNode1.getDepth()
				//println "2nd Node Depth = " + fNode2.getDepth()
				//println "1st Node Depth after cross = " + crossoverResults[0].getDepth()
				//println "2nd Node Depth after cross = " + crossoverResults[1].getDepth()
			}
		then:
			1==1
	}
	
	def "crossover on two constant nodes"() {
		when:
			def cNode1 = new ConstantNode(value:1)
			def cNode2 = new ConstantNode(value:2)
			def result = crossover(cNode1, cNode2, 1)
		then:
			result[0].value == 2
			result[1].value == 1
	}
	
	def "crossover on two constant nodes 0 chance"() {
		when:
			def cNode1 = new ConstantNode(value:1)
			def cNode2 = new ConstantNode(value:2)
			def result = crossover(cNode1, cNode2, 0)
		then:
			result[0].value == 1
			result[1].value == 2
	}
	
	def "crossover on two function nodes doesn't break"() {
		when:
			def fNode1 = new FunctionNode(function:new Addition(),
						children:[new FunctionNode(function:new Subtraction(),
							children:[new ConstantNode(value:7), new ConstantNode(value:3)]),
						new FunctionNode(function:new Multiplication(),
							children:[new ConstantNode(value:2), new ConstantNode(value:3)])])
			def fNode2 = new FunctionNode(function:new Square(),
						children:[new FunctionNode(function:new Square(),
							children:[new ConstantNode(value:2)])])
			def result = crossover(fNode1, fNode2, 0.5)
		then:
			0 == 0
	}
}