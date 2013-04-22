package robocode

import gpMethods.TreeGenerator;
import gpMethods.functions.numeric.*
import groovy.transform.ToString
import problems.GP.GPProblem

class RobotCodeEvolutionProblem {
	protected rand = new java.util.Random()
	def maximalQuality = { 99999 }
	def noDisplay = true
	def isWindows = false
	def template = null
	def functionList=[new Addition(), new Subtraction(), new Multiplication(), new Division()]
	def variableList=["distance","adjustedBearing"]
	def constantList=[0, 1, 2, 0.5, 0.1, -1]
	def defaultMaxTreeSize = 20
	Integer evalCount = 0
	Integer maxIterations = 10
	def constantChance=0.3
	def variableChance=0.3
	def functionChance=0.4
	TreeGenerator gen
	def GPProblem gpTree=new GPProblem(variableList:variableList, constantList:constantList, functionList:functionList, treeSize:defaultMaxTreeSize,
				constantChance:constantChance, variableChance:variableChance, functionChance:functionChance)

	def quality = { a ->
		if(a.get("score")==null){
			++evalCount
			def scoreText = RunBattle.runBattle(template, a, noDisplay, isWindows)
			def score = RunBattle.getScore(scoreText, a.get("id"), isWindows)
			a.put("score", score)
			return score
		}
		return a.get("score")
	}
	
	

	def create = {
		def id = rand.nextInt(1000000)
		def aimingFunctionTree=gpTree.create()
		def values = ["id" : id, "aimingFunctionTree" : aimingFunctionTree, "predictiveAimingFunction" : aimingFunctionTree.toString()]
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
	
	def tweak = { a, mutationRate = 0.5 ->
		def new_id = rand.nextInt(1000000)
		def new_aimingFunctionTree = gpTree.tweak((a.get("aimingFunctionTree")), mutationRate)
		def new_values = ["id" : new_id, "aimingFunctionTree" : new_aimingFunctionTree, "predictiveAimingFunction" : new_aimingFunctionTree.toString()]
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
