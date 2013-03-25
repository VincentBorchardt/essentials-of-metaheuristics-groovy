package problems.GP

class Multiplexer11Bit extends GPProblem {
	def maximalQuality = {2048}
	def listOfVarMaps = generateVarMaps()
	
	def quality = {a ->
		++evalCount
		def quality = 0
		
		for (i in listOfVarMaps) {
			if (a.eval(i) == numToBool(i)) {
				quality++
			}
		}
		
		return quality
	}
	
	private numToBool(varMap) {
		def a0 = varMap.get("a0")
		def a1 = varMap.get("a1")
		def a2 = varMap.get("a2")
		
		return varMap.get(4*a0 + 2*a1 + a2 + 3)
		
		/*
		if(a0 && a1 && a2){
			return varMap.get("d7")
		}
		if(a0 && a1 && !a2){
			return varMap.get("d6")
		}
		if(a0 && !a1 && a2){
			return varMap.get("d5")
		}
		if(a0 && !a1 && !a2){
			return varMap.get("d4")
		}
		if(!a0 && a1 && a2){
			return varMap.get("d3")
		}
		if(!a0 && a1 && !a2){
			return varMap.get("d2")
		}
		if(!a0 && !a1 && a2){
			return varMap.get("d1")
		}
		if(!a0 && !a1 && !a2){
			return varMap.get("d0")
		}
		*/
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
			
			varMaps.add(tempMap)
		} 
		
		/*
		for (def i = 0; i <= 1; i++) {
			for (def j = 0; j <= 1; j++) {
				for (def k = 0; k <= 1; k++) {
					for (def l = 0; l <= 1; i++) {
						for (def m = 0; m <= 1; j++) {
							for (def n = 0; n <= 1; k++) {
								for (def p = 0; p <= 1; j++) {
									for (def q = 0; q <= 1; k++) {
										for (def r = 0; r <= 1; i++) {
											for (def s = 0; s <= 1; j++) {
												for (def t = 0; t <= 1; k++) {
													varMaps.add(["a0":i, "a1":j, "a2":k, "d0":l, "d1":m, "d2":n, "d3":p, "d4":q, "d5":r, "d6":s, "d7":t])
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		*/
		
		return varMaps
	}
}