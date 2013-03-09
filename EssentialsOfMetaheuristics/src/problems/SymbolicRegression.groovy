package problems

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
			quality -= Math.abs(a.eval(it) - functionToFit(it))
		}
		
		return quality
	}
}