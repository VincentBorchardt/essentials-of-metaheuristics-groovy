package gpMethods

class TreeGenerator {
	def rand=new Random()
	def variableList
	def constantList
	def functionList
	def maxFunctions = 5
	def defaultConstantChance = 0.2
	def defaultVariableChance = 0.4
	def defaultFunctionChance = 0.4
	private numFunctions = 0
	
	def generateNewTree(maxFunctions = this.maxFunctions, constantChance = defaultConstantChance, variableChance = defaultVariableChance, functionChance = defaultFunctionChance) {
		//println functionList
		this.maxFunctions = maxFunctions
		numFunctions = 0
		return generateNode(constantChance, variableChance, functionChance)
	}
	
	def generateConstantNode(){
		return new ConstantNode(value:(constantList[rand.nextInt(constantList.size())]))
	}
	
	def generateVariableNode(){
		return new VariableNode(variable:(variableList[rand.nextInt(variableList.size())]))
	}
	
	def generateFunctionNode(constantChance = defaultConstantChance, variableChance = defaultVariableChance, functionChance = defaultFunctionChance) {
		numFunctions++
		def chosenFunction = functionList[rand.nextInt(functionList.size())]
		//println chosenFunction
		def arity = chosenFunction.function.parameterTypes.size()
		//def childrenList = new List<Node>[arity]
		def childrenList = (0 ..< arity).collect {
            generateNode(constantChance, variableChance, functionChance)
        }
		
		//println childrenList
		
		return new FunctionNode(function:chosenFunction, children:childrenList)
	}
	
	def generateNode(constantChance = defaultConstantChance, variableChance = defaultVariableChance, functionChance = defaultFunctionChance) {
		def randFloat = rand.nextFloat()
		if (randFloat <= constantChance) {
				return generateConstantNode()
		} else if (randFloat <= constantChance + variableChance) {
				return generateVariableNode()
		} else {
			if (numFunctions < maxFunctions) {
				return generateFunctionNode(constantChance, variableChance, functionChance)
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
