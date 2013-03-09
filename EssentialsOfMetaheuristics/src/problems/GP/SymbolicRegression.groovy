package problems.GP

class SymbolicRegression extends GPProblem {
	def maximalQuality = {0}
	def functionToFit
	def samplePoints // = [{"x": 0}, {"x": 1}]
	
	//def startPoint
	//def endPoint
	//def numSamples
	
	def quality = {a ->
		++evalCount
		def quality = 0
		samplePoints.each {
			quality -= Math.pow(a.eval(it) - functionToFit(it), 2)
		}
		
		return quality
	}

	def tweak = { a, mutationRate = defaultMutationRate ->
		if (rand.nextFloat() < mutationRate) {
			return gen.generateNewTree()
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
}