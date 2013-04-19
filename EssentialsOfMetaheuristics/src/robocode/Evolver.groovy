package robocode

import populationMethods.MuCommaLambdaES
import populationMethods.MuPlusLambdaES
import singleStateMethods.HillClimber
import singleStateMethods.IteratedLocalSearchRandomRestarts

class Evolver {
	static numRuns = 5
	static numIterations = 20
	static battleRunner
	static isWindows = false

	static runExperiment(searchers, problems) {
		for (p in problems) {
			for (s in searchers) {
				for (i in 0 ..< numRuns) {
					p.evalCount = 0
					def result = s.maximize(p)
					println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\t${result}"
				}
			}
		}
	}

	static main(args) {
		/*
		if (isWindows) {
			battleRunner = new BattleRunner("templates/battle.template")
		} else {
			battleRunner = new BattleRunner("templates/battleWindows.template")
		}
		*/
		def searchers = [
			//new IteratedLocalSearchRandomRestarts()
			new HillClimber()
			//new MuPlusLambdaES()
		]
		def problems = [
			//new SimpleRobotEvolutionProblem(maxIterations:numIterations)
			new RobotCodeEvolutionProblem(maxIterations:numIterations)
		]
		// It would be nice to collect the total time here and include it in the
		// output.
		runExperiment(searchers, problems)
	}

}