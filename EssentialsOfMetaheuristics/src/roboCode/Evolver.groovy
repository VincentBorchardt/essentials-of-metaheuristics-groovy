package roboCode

import singleStateMethods.HillClimber

class Evolver {
	static numRuns = 10
	static battleRunner

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
		battleRunner = new BattleRunner("templates/battle.template")
		def searchers = [
			new HillClimber()
		]
		def problems = [
			new RobotEvolutionProblem()
		]
		// It would be nice to collect the total time here and include it in the
		// output.
		runExperiment(searchers, problems)
	}

}
