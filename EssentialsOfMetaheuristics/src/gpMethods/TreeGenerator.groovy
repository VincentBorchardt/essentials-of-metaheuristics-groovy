package gpMethods

class TreeGenerator {
	def rand=new Random()
	def variableList
	def constantList
	def functionList
	
	def generateConstantNode(){
		return new ConstantNode(value:(constantList[rand.nextInt(constantList.size())]))
	}
	
	def generateVariableNode(){
		return new VariableNode(variable:(variableList[rand.nextInt(variableList.size())]))
	}
	
	def generateFunctionNode(){
		def chosenFunction = functionList[rand.nextInt(functionList.size())]
		def arity = chosenFunction.parameterTypes.size()
		//def childrenList = new List<Node>[arity]
		def childrenList = (0 ..< arity).collect {
            generateNode()
        }
		return new FunctionNode(function:chosenFunction, children:childrenList)
	}
	
	def generateNode(){
		//TODO let the probability distribution of node types be variable when creating a random node
		switch(rand.nextInt(3)) {
			case 0:
				return generateConstantNode()
				break;
			case 1:
				return generateVariableNode()
				break;
			case 2:
				return generateFunctionNode()
				break;
			default:
				//NOT POSSIBRU!
				throw new Exception("How did this happen? generateNode()")
		}
	}
}
