package gpMethods

class ConstantNode extends Node {
	def value
	
	@Override
	def eval(varMap) {
		return value
	}

	@Override
	String toString() {
		return "Constant node. Value = $value" 
	}
}
