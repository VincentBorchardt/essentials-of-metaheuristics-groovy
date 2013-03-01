package gpMethods

class FunctionNode extends Node {
	def arity
	def function
	def children

	@Override
	def eval(varMap) {
		return function(children*.eval(varMap))
	}

	def getArity() {
		if (arity == null) {
			arity = function.parameterTypes.size()
		}
		return arity
	}
}
