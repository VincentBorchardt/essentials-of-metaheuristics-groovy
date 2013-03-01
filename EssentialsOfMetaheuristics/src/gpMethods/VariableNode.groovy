package gpMethods

class VariableNode extends Node{
	def variable
	
	@Override
	def eval(varMap) {
		return varMap.get(variable)
	}
	
	@Override
	String toString() {
		return "Variable node. Variable = $variable"
	}
}
