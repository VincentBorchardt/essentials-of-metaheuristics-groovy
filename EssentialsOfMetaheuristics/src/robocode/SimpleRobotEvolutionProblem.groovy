package robocode

import groovy.transform.ToString

class SimpleRobotEvolutionProblem {
	protected rand = new java.util.Random()
	def maximalQuality = { 9999 }
	def noDisplay = true
	def isWindows = false
	def template = null

	def quality = { a ->
		if(a.get("score")==null){
			++evalCount
			def scoreText = RunBattle.runBattle(template, a, noDisplay, isWindows)
			def score = RunBattle.getScore(scoreText, a.get("id"))
			a.put("score", score)
			return score
		}
		return a.get("score")
	}
	
	Integer evalCount = 0
	Integer maxIterations = 10

	def create = {
		def id = rand.nextInt(1000000)
		def enemy_energy = rand.nextFloat() * 100
		def my_energy = rand.nextFloat() * 100
		def angle_diff = rand.nextFloat() * 100
		def distance = rand.nextFloat() * 100
		def movementPerturbation = (2*rand.nextFloat()-1) * Math.PI / 2
		def values = ["id" : id, "enemy_energy" : enemy_energy, "my_energy" : my_energy, "angle_diff" : angle_diff, 
			"distance" : distance, "movementPerturbation": movementPerturbation]
//		def robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
//		robotBuilder.buildJarFile(values)
//		new File("evolved_robots/evolved/Individual_${id}.java")
		return values
	}

	def copy = { a -> a.clone() }

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
		def new_id = rand.nextInt(1000000)
		def new_enemy_energy = a.get("enemy_energy")+(rand.nextFloat()*2-1)*mutationRate
		def new_my_energy = a.get("my_energy")+(rand.nextFloat()*2-1)*mutationRate
		def new_angle_diff = a.get("angle_diff")+(rand.nextFloat()*2-1)*mutationRate
		def new_distance = a.get("distance")+(rand.nextFloat()*2-1)*mutationRate
		def new_movementPerturbation = a.get("movementPerturbation") + (2*rand.nextFloat()-1)*(Math.PI/4)*mutationRate
		def new_values = ["id" : new_id, "enemy_energy" : new_enemy_energy, "my_energy" : new_my_energy, "angle_diff" : new_angle_diff,
			"distance" : new_distance, "movementPerturbation": new_movementPerturbation]
		return new_values
	}

	def random = { 
		return create()
	}
	
	def perturb = { a, mutationRate = 4 ->
		tweak(a, mutationRate)
	}

	def terminate = { a, q = quality(a) ->
		evalCount >= maxIterations || q >= maximalQuality()
	}

	String toString() {
		this.class.name.split("\\.")[-1] + "_" + maxIterations
	}
}
