package problems

import gpMethods.TreeGenerator

class GPProblem {
	protected Random rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations = 1000
	Integer treeSize = 10
	List functionList
	List constantList
	List variableList
	TreeGenerator gen

	def create = {maxSize = 10 ->
		gen = new TreeGenerator(functionList:functionList, constantList:constantList, variableList:variableList, maxFunctions:treeSize)
		return gen.generateNewTree(maxSize)
	}

	def copy = {
		a -> a.clone()
	}

	/*
	 * Having this take an option array of bits works, but probably isn't
	 * super efficient, especially for large bit strings, as we need to allocate
	 * memory for and construct the full set of bits, which is really not
	 * necessary. An alternative would be to send in a closure that will return
	 * a 0 or 1 every time it's called. The default closure could return random
	 * bits, while for testing we could send in a closure that advances through
	 * an fixed array of bits. For now, however, this works, so I'm going to leave
	 * it alone and move on.
	 */

	def tweak = { a, mutationRate = 0.1 ->
		if (rand.nextFloat() < mutationRate) {
			return gen.generateNewTree()
		} else {
			return tweakChildren(a, Math.sqrt(mutationRate))
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

	def random = {maxSize = 10 ->
		return create(maxSize)
	}
	
	def perturb = {
		
	}

	def terminate = { a, q = quality(a) ->
		evalCount >= maxIterations || q == maximalQuality()
	}

	String toString() {
		this.class.name.split("\\.")[-1] + "_" + treeSize + "_" + maxIterations
	}
}
