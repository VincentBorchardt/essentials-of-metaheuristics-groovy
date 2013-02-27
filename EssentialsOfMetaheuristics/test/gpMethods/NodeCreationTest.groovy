package gpMethods

import spock.lang.Specification

class NodeCreationTest extends Specification {
	
	def "able to create constant node"() {
		expect:
		def cNode1 =  new ConstantNode(value:3)
		cNode1.value==3
	}
	
//	def "able to create function node"() {
//		def fNode1 = new FunctionNode(function:{x, y -> x + y}, )
//	}

}
