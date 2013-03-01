package gpMethods

class FunctionNode extends Node {
	def arity
	def function
	def children

	@Override
	def eval(varMap) {
		if (getArity() == 1) {
			return function(children[0].eval(varMap))
		} else {
			return function(children*.eval(varMap))
			// *. doesn't work on one thing!?!?!
		}
	}

	def getArity() {
		if (arity == null) {
			arity = function.parameterTypes.size()
		}
		return arity
	}
	
	@Override
	String toString() {
		//TODO maybe print what the function is (seems hard)
		return "Function node - arity = " + getArity()// + ", function = " + function.metaClass.classNode.getDeclaredMethods("doCall")[0].code.text
	}
}
