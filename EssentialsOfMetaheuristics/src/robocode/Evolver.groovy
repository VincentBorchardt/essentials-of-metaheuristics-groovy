package robocode

import populationMethods.GeneticAlgorithm
import populationMethods.MuCommaLambdaES
import populationMethods.MuPlusLambdaES
import singleStateMethods.HillClimber
import singleStateMethods.IteratedLocalSearchRandomRestarts

class Evolver {
	static numRuns = 1
	static numIterations = 30
	static battleRunner
	static isWindows = false

	static runExperiment(searchers, problems) {
		for (p in problems) {
			for (s in searchers) {
				for (i in 0 ..< numRuns) {
					p.evalCount = 0
					def result = s.maximize(p)
					def tree = result.get("aimingFunctionTree")
					println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\n${tree}"
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
			//new GeneticAlgorithm(crossover:new RobotCrossover().crossover, popsize:20)
		]
		def problems = [
			new SimpleRobotEvolutionProblem(maxIterations:numIterations, isWindows:isWindows)
			//new RobotCodeEvolutionProblem(maxIterations:numIterations, isWindows:isWindows)
		]
		// It would be nice to collect the total time here and include it in the
		// output.
		runExperiment(searchers, problems)
	}

}