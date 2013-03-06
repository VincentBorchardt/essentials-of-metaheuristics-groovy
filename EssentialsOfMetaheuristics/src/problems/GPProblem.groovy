package problems

import gpMethods.TreeGenerator

class GPProblem {
	protected rand = new java.util.Random()
	Integer evalCount = 0
	Integer maxIterations = 1000
	Integer treeSize = 10
	List functionList
	List constantList
	List variableList
	TreeGenerator gen

	def create = {
		gen = new TreeGenerator(functionList: functionList, constantList: constantList, variableList: variableList, maxFunctions: treeSize)
		return gen.generateNode()
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

	def tweak = { a, mutationRate = 1 ->
		
	}

	def random = {
		
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
