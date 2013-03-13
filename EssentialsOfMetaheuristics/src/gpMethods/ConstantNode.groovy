package gpMethods

class ConstantNode extends Node {
	def value
	
	@Override
	def eval(varMap) {
		return value
	}

	@Override
	String toString() {
		return "$value" 
	}
	
	def containsSelf(checkingNode=this){ false }
	
	Object clone() {
		return new ConstantNode(value: value)
	}
	
	def getDepth() {
		return 0
	}
}
