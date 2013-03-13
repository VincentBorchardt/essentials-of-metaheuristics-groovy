package gpMethods

class VariableNode extends Node{
	def variable
	
	@Override
	def eval(varMap) {
		return varMap.get(variable)
	}
	
	@Override
	String toString() {
		return "$variable"
	}
	
	def containsSelf(checkingNode=this){ false }
	
	Object clone() {
		return new VariableNode(variable: variable)
	}
	
	def getDepth() {
		return 0
	}
}
