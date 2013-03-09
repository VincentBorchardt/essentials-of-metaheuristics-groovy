package gpMethods

import gpMethods.functions.Function

class FunctionNode extends Node {
	def arity
	Function function
	def children

	@Override
	def eval(varMap) {
		if (getArity() == 1) {
			return function.getFunction()(children[0].eval(varMap))
		} else {
			return function.getFunction()(children*.eval(varMap))
			// *. doesn't work on one thing!?!?!
		}
	}

	def getArity() {
		if (arity == null) {
			arity = function.getFunction().parameterTypes.size()
		}
		return arity
	}

	@Override
	String toString() {
		//TODO maybe print what the function is (seems hard)
		return "Function node - type = " + function.toString() + " - children = " + children
	}

	Object clone() {
		return new FunctionNode(children: children.clone(), function: function.clone())
	}
}
