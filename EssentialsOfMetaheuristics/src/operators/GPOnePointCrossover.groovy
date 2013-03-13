package operators



class GPOnePointCrossover {
	final static Random random = new Random()
	def defaultMaxDepth = 20
	def defaultChance = 0.1
	//def initialChance = 0.5
	
	def crossover = {parent1, parent2, chance = defaultChance, maxDepth = defaultMaxDepth ->
		def clone1 = parent1.clone()
		def clone2 = parent2.clone()
		
		return crossoverHelper(clone1, clone2, chance, maxDepth)
	}
	
	def crossoverHelper(parent1, parent2, chance, maxDepth) {
		if (random.nextFloat() <= chance) {
			return [parent2, parent1, Math.max(parent1.getDepth(), parent2.getDepth())]
		} else {
			def nodeToConsider1 = randomlyChooseNode(parent1)
			def nodeToConsider2 = randomlyChooseNode(parent2)
			
			if (nodeToConsider1[1] == null && nodeToConsider2[1] == null) {
				return [parent1, parent2, Math.max(parent1.getDepth(), parent2.getDepth())]
			} else {
				def result = crossoverHelper(nodeToConsider1[0], nodeToConsider2[0], Math.sqrt(chance), maxDepth)// We may not always want this here, but probably will
				if (Math.max(parent1.getDepth(), parent2.getDepth()) + result[2] <= maxDepth) {
					if (nodeToConsider1[1] == null) { 
						parent1 = result[0]
					} else {
						parent1.children[nodeToConsider1[1]] = result[0]
					}
					
					if (nodeToConsider2[1] == null) { 
						parent2 = result[1]
					} else {
						parent2.children[nodeToConsider2[1]] = result[1]
					}
					
					return [parent1, parent2, Math.max(parent1.getDepth(), parent2.getDepth()) + result[2]]
				} else {
					return [parent1, parent2, Math.max(parent1.getDepth(), parent2.getDepth())]
				}
			}
		}
	}

	private randomlyChooseNode(parent) {
		if (random.nextInt(2) == 0) {// vary this chance
			return [parent, null]
		} else {
			if (parent.children == null) {
				return [parent, null]
			} else {
				def idx = random.nextInt(parent.children.size())
				return [parent.children[idx], idx]
			}
		}
	}
	/*
	def crossover(parent1, parent2, chance = 0.5) {
		initialChance=chance
		def cloneA = parent1.clone()
		def cloneB = parent2.clone()
		
		crossoverPart1(cloneA, cloneB, chance)
	}
	
	def crossoverPart1(nodeA, nodeB, chance){
		if(chance >= random.nextFloat()){
			return crossoverPart2(nodeA, nodeB, initialChance)
		} else {
			return crossoverChildren1(nodeA, nodeB, Math.sqrt(chance))
		}
	}
	
	def crossoverChildren1(nodeA, nodeB, chance){
		if (nodeA.children == null){
			return nodeA
		} else {
			def childNum=random.nextInt(nodeA.children.size())
			nodeA.children[childNum] = crossoverPart1(nodeA.children[childNum], nodeB, chance)
			return nodeA
		}
	}
	
	def crossoverPart2(nodeA, nodeB, chance){
		if(chance >= random.nextFloat()){
			return crossoverPart2(nodeA, nodeB, initialChance)
		} else {
			return crossoverChildren1(nodeA, nodeB, Math.sqrt(chance))
		}
	}
	
	def crossoverChildren2(nodeA, nodeB, chance){
		if (nodeB.children == null){
			return nodeB
		} else {
			def childNum=random.nextInt(nodeB.children.size())
			nodeB.children[childNum] = crossoverPart1(nodeA, nodeB.children[childNum], chance)
			return nodeB
		}
	}
	*/
}