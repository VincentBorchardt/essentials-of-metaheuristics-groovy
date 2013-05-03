package robocode

import groovy.transform.ToString

class SimpleRobotEvolutionProblem {
	protected rand = new java.util.Random()
	def maximalQuality = { 9999 }
	def noDisplay = true
	def isWindows
	def template = "Trollbot"
	Integer evalCount = 0
	Integer maxIterations = 10

	def quality = { a ->
		if(a.get("score")==null){
			++evalCount
			def scoreText = RunBattle.runBattle(template, a, noDisplay, isWindows)
			def score = RunBattle.getScore(scoreText, a.get("id"), isWindows)
			a.put("score", score)
			return -score
		}
		return -a.get("score")
	}

	def create = {
		def id = rand.nextInt(100000000)
		def changeDirPeriod = 10
		def optimalDistance = 300
		def rammingDistance = 80
		def values = ["id":id,  "changeDirPeriod":changeDirPeriod, "optimalDistance":optimalDistance, "rammingDistance":rammingDistance]
		return values
	}
	
	def tweak = { a, mutationRate = 1 ->
		def new_id = rand.nextInt(100000000)
		def new_changeDirPeriod = Math.min(1, Math.max(0,
			(2*rand.nextFloat()-1)*0.2*mutationRate + a.get("changeDirPeriod")))
		def new_optimalDistance = Math.min(1000, Math.max(100,
			(2*rand.nextFloat()-1)*25*mutationRate + a.get("optimalDistance")))
		def new_rammingDistance = Math.min(200, Math.max(80,
			(2*rand.nextFloat()-1)*10*mutationRate + a.get("rammingDistance")))
		def new_values = ["id":new_id, "changeDirPeriod":new_changeDirPeriod, "optimalDistance":new_optimalDistance, "rammingDistance":new_rammingDistance]
		return new_values
	}

	def copy = { a -> a.clone() }
	
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
