package problems.GP

import gpMethods.TreeGenerator

class GPProblem {
	protected Random rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations
	Integer treeSize
	Integer defaultMutationRate = 0.2
	List functionList
	List constantList
	List variableList
	TreeGenerator gen

	def create = {maxIterations = 1000, maxSize = 5 ->
		this.treeSize = maxSize
		this.maxIterations = maxIterations
		gen = new TreeGenerator(functionList:functionList, constantList:constantList, variableList:variableList, maxFunctions:maxSize)
		return gen.generateNewTree(maxSize)
	}

	def copy = {
		a -> a.clone()
	}
	
	def random = {maxSize = 10 ->
		return create(maxSize)
	}
	
	/*
	def perturb = {
		
	}
	*/

	def terminate = { a, q = quality(a) ->
		evalCount >= maxIterations || q == maximalQuality()
	}

	String toString() {
		this.class.name.split("\\.")[-1] + "_" + treeSize + "_" + maxIterations
	}
}
