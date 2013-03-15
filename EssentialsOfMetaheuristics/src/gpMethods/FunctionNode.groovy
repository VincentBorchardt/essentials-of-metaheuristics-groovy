package gpMethods

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
	
	def getDepth(start=1) {
		if (containsSelf()) {
			throw new Exception("node contains itself!")
		} else {
			return getDepthHelper(start)
		}
	}
	
	def getDepthHelper(currentDepth) {
		if(currentDepth>=100){
			println "containsSelf check: " + this.containsSelf()
		}
		def max = currentDepth
		for (int i = 0; i < children.size; i++) {
			if (children[i].class == FunctionNode) {
				max = Math.max(max, children[i].getDepth(currentDepth+1))
			}
		}
		//println "b "+(max + 1)
		return max
		//return
		//	children.inject(0) {a, b -> 
		//		Math.max(a instanceof gpMethods.Node ?  a.getDepthHelper(currentDepth+1) : currentDepth, b instanceof gpMethods.Node ?  b.getDepthHelper(currentDepth+1) : currentDepth)}
	}
	
	def containsSelf(checkingNode=this){
		def hasSelf = false
		for(i in children){
			hasSelf = hasSelf || ((i == checkingNode) ? true : i.containsSelf(checkingNode))
		}
		return hasSelf
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
			case "if":
				return "(if " + children[0] + " then " + children[1] + " else " + children[2] + ")"
				break
			default:
				return "(" + children.join(" " + function.name + " ") + ")"
		}
	}

	Object clone() {
		return new FunctionNode(children: getArity() > 1 ? children*.clone() : [children[0].clone()], function: function.clone())
	}
}
