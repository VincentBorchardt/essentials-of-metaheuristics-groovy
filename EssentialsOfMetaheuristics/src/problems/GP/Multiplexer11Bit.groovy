package problems.GP

class Multiplexer11Bit extends GPProblem {
	def maximalQuality = {2048}
	def listOfVarMaps = generateVarMaps()
	
	
	def quality = { a ->
		++evalCount
		def fitness = 0
		def temp1, temp2, temp3
		
		for (varMap in listOfVarMaps) {
			temp1 = a.eval(varMap)
			temp2 = numToBool(varMap)
			//temp3 = boolSetToInt(varMap)
			//println "eval: " + temp1 + " numToBool: " + temp2
			if (temp1 == temp2) {
				fitness++
			}
		}
		
		return fitness
	}
	
	private boolSetToInt(varMap) {
		def a0 = varMap.get("a0")
		def a1 = varMap.get("a1")
		def a2 = varMap.get("a2")
		return 4*a0 + 2*a1 + a2
	}
	
	private numToBool(varMap) {
		def a0 = varMap.get("a0")
		def a1 = varMap.get("a1")
		def a2 = varMap.get("a2")
		
		return varMap.get("d" + (4*a0 + 2*a1 + a2))
	}
	
	private generateVarMaps() {
		def varMaps = []
		def tempMap = [:]
		def temp
		int i,n
		
		for (i = 0; i < 2048; i++) {
			temp = i
			tempMap.clear()
			for (n = 10; n >= 0; n--) {
				if (temp >= Math.pow(2, n)) {
					temp -= Math.pow(2, n)
					if (n < 3) {
						tempMap[("a" + n)] = 1
					} else {
						tempMap[("d" + (n - 3))] = 1
					}
				} else {
					if (n < 3) {
						tempMap[("a" + n)] = 0
					} else {
						tempMap[("d" + (n - 3))] = 0
					}
				}
			}
			
			varMaps.add(tempMap.clone())
		}
		
		return varMaps
	}
}