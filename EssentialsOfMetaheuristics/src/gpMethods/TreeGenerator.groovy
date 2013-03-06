package gpMethods

class TreeGenerator {
	def rand=new Random()
	def variableList
	def constantList
	def functionList
	def maxFunctions = 10
	private numFunctions = 0
	
	def generateConstantNode(){
		return new ConstantNode(value:(constantList[rand.nextInt(constantList.size())]))
	}
	
	def generateVariableNode(){
		return new VariableNode(variable:(variableList[rand.nextInt(variableList.size())]))
	}
	
	def generateFunctionNode(constantChance = 0.2, variableChance = 0.2, functionChance = 0.6){
		numFunctions++
		def chosenFunction = functionList[rand.nextInt(functionList.size())]
		def arity = chosenFunction.parameterTypes.size()
		//def childrenList = new List<Node>[arity]
		def childrenList = (0 ..< arity).collect {
            generateNode(constantChance, variableChance, functionChance)
        }
		
		System.out.println(childrenList)
		
		return new FunctionNode(function:chosenFunction, children:childrenList)
	}
	
	def generateNode(constantChance = 0.2, variableChance = 0.2, functionChance = 0.6){
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
