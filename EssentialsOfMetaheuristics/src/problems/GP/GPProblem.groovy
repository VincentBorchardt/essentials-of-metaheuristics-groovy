package problems.GP

import gpMethods.TreeGenerator

class GPProblem {
	protected Random rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations
	Integer treeSize = 5
	Integer defaultMutationRate = 0.2
	List functionList
	List constantList
	List variableList
	TreeGenerator gen

	def create = { n ->
		//this.treeSize = maxSize
		gen = new TreeGenerator(functionList:functionList, constantList:constantList, variableList:variableList, maxFunctions:treeSize)
		return gen.generateNewTree()
	}

	def copy = {
		a -> a.clone()
	}
	
	def random = { n ->
		return create()
	}
	
	/*
	def perturb = {
		
	}
	*/

	def tweak = { a, mutationRate = defaultMutationRate ->
		if (rand.nextFloat() < mutationRate) {
			return gen.generateNewTree(treeSize - a.getDepth())
		} else {
			return tweakChildren(a, mutationRate)
		}
	}
	
	def tweakChildren = {a, mutationRate ->
		if (a.children == null){
			return a
		} else {
			a.children = a.children.collect{child -> tweak(child, mutationRate)}
			return a
		}
	}

	def terminate = { a, q = quality(a) ->
		evalCount >= maxIterations || q == maximalQuality()
	}

	String toString() {
		this.class.name.split("\\.")[-1] + "_" + treeSize + "_" + maxIterations
	}
}
