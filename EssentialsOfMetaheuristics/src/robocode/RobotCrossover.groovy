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
		def new_tree = gpCrossover.crossover(parent1.get("aimingFunctionTree"), parent2.get("aimingFunctionTree"),chance, maxDepth)
		def new_values = ["id" : new_id, "aimingFunctionTree" : new_tree, "predictiveAimingFunction" : new_tree.toString()]
		return new_values
	}
}