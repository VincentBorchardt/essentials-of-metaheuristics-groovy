package gpMethods

class TreeGenerator {
	def rand=new Random()
	def variableList
	def constantList
	def functionList
	def maxFunctions = 5
	def constantChance = 0.2
	def variableChance = 0.4
	def functionChance = 0.4
	private numFunctions = 0
	
	def generateNewTree(maxFunctions = this.maxFunctions) {
		//println functionList
		this.maxFunctions = maxFunctions
		numFunctions = 0
		return generateNode()
	}
	
	def generateConstantNode(){
		return new ConstantNode(value:(constantList[rand.nextInt(constantList.size())]))
	}
	
	def generateVariableNode(){
		return new VariableNode(variable:(variableList[rand.nextInt(variableList.size())]))
	}
	
	def generateFunctionNode() {
		numFunctions++
		def chosenFunction = functionList[rand.nextInt(functionList.size())]
		//println chosenFunction
		def arity = chosenFunction.function.parameterTypes.size()
		//def childrenList = new List<Node>[arity]
		def childrenList = (0 ..< arity).collect {
            generateNode()
        }
		
		//println childrenList
		
		return new FunctionNode(function:chosenFunction, children:childrenList)
	}
	
	def generateNode() {
		def randFloat = rand.nextFloat()
		if (randFloat <= constantChance) {
			return generateConstantNode()
		} else if (randFloat <= constantChance + variableChance) {
			return generateVariableNode()
		} else {
			if (numFunctions < maxFunctions) {
				return generateFunctionNode()
			} else {
				return generateNonFunctionNode()
			}
		}
	}
	
	def generateNonFunctionNode() {
		switch(rand.nextInt(2)) {
			case 0:
				return generateConstantNode()
				break;
			case 1:
				return generateVariableNode()
				break;
			default:
				//NOT POSSIBRU!
				throw new Exception("How did this happen? generateNode()")
		}
	}
}
