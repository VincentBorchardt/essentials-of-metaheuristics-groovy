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
	
	Object clone() {
		return new ConstantNode(value: value)
	}
}
