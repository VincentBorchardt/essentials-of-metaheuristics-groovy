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
	
	def swapChild(index, child) {
		children[index] = child
	}
	
	def getDepth() {
		return 1 + 
			children.inject(0) {a, b -> 
				Math.max(a instanceof gpMethods.Node ?  a.getDepth() : a, b instanceof gpMethods.Node ?  b.getDepth() : b)}
	}

	@Override
	String toString() {
		switch (function.type) {
			case "prefix":
				return function.name + "[" + children.join(", ") + "]"
				break
			case "infix":
				return "(" + children.join(" " + function.name + " ") + ")"
				break
			case "postfix":
				return "(" + children.join(", ") + ")" + function.name
				break
			default:
				return "(" + children.join(" " + function.name + " ") + ")"
		}
	}

	Object clone() {
		return new FunctionNode(children: children.clone(), function: function.clone())
	}
}
