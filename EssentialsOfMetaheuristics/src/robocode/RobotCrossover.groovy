package robocode

import java.util.Random;

import operators.GPOnePointCrossover

class RobotCrossover {
	final static Random random = new Random()
	def defaultMaxDepth = 20
	def defaultChance = 0.1
	def gpCrossover=new GPOnePointCrossover(defaultChance: defaultChance)
	def crossover = {parent1, parent2, chance = defaultChance, maxDepth = defaultMaxDepth ->
		def new_id = random.nextInt(1000000)
		def new_trees = gpCrossover.crossover(parent1.get("aimingFunctionTree"), parent2.get("aimingFunctionTree"),chance, maxDepth)
		def new_tree1 = new_trees[0]
		def new_tree2 = new_trees[1]
		def new_values1 = ["id" : new_id, "aimingFunctionTree" : new_tree1, "predictiveAimingFunction" : new_tree1.toString()]
		def new_values2 = ["id" : new_id, "aimingFunctionTree" : new_tree2, "predictiveAimingFunction" : new_tree2.toString()]
		return [new_values1,new_values2]
	}
}