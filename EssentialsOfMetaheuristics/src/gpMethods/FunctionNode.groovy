package gpMethods

class FunctionNode extends Node {
	//testing!
	def arity
	def function
	def children

	@Override
	def eval(varMap) {
		// TODO Auto-generated method stub
		return null
	}

	def getArity() {
		if (arity == null) {
			arity = function.parameterTypes.size()
		}
		return arity
	}
}
