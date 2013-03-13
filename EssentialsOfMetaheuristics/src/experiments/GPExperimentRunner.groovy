package experiments

import operators.GPOnePointCrossover;
import problems.GP.*;
import singleStateMethods.HillClimber
import populationMethods.GeneticAlgorithm
import gpMethods.functions.*
//import singleStateMethods.SteepestAscentHillClimber
//import singleStateMethods.SteepestAscentHillClimberWithReplacement

class GPExperimentRunner {
	static defaultMaxTreeSize = 5
	static defaultNumRuns = 300
	static defaultMaxIterations = 1000
	static defaultNumComparisonPoints = 20

    static runExperiment(searchers, problems, numRuns = defaultNumRuns) {
        for (p in problems) {
            for (s in searchers) {
                for (i in 0 ..< numRuns) {
                    p.evalCount = 0
                    def result = s.maximize(p)
                    println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\t${result}"
					//println "${s.toString()}\t${p.quality(result)}"
                }
            }
        }
    }

    static main(args) {
		def functionToFit = {map -> Math.sin(map."x")}
		//def functionToFit = {map -> map."x"}
		def variableList = ["x"]
		def constantList = [-1, 0.01, 0.5, 1, 2]
		def functionList = [new Addition(), new Multiplication()]
		
		def samplePoints = (0 .. defaultNumComparisonPoints).collect{i -> ["x": (2 * i * Math.PI) / defaultNumComparisonPoints]}//[["x": 0], ["x": 1]]
		
        def searchers = [
			new GeneticAlgorithm(crossover: new GPOnePointCrossover().crossover, maxDepth: defaultMaxTreeSize)
			//new HillClimber()
			
			/*
            new HillClimber(),
			new SteepestAscentHillClimber(numGradientSamples : 1),
            new SteepestAscentHillClimber(numGradientSamples : 2),
            new SteepestAscentHillClimber(numGradientSamples : 4),
            new SteepestAscentHillClimberWithReplacement(numGradientSamples : 1),
            new SteepestAscentHillClimberWithReplacement(numGradientSamples : 2),
            new SteepestAscentHillClimberWithReplacement(numGradientSamples : 4),
            new SteepestAscentHillClimberWithReplacement(numGradientSamples : 8),
            new SteepestAscentHillClimberWithReplacement(numGradientSamples : 16)
            */
        ]
        def problems = [
			new SymbolicRegression(functionToFit:functionToFit, samplePoints:samplePoints, variableList:variableList, constantList:constantList, functionList:functionList, 
				maxIterations:defaultMaxIterations, treeSize:defaultMaxTreeSize)
            //			new OnesMax(numBits : 100, maxIterations : 250),
            //			new LeadingOnes(numBits : 100, maxIterations : 1000),
            //			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 1),
            //			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 2),
            //			new LeadingOnesBlocks(numBits : 100, maxIterations : 10000, blockSize : 4),
            //			new Trap [Functi(numBits : 4, maxIterations : 1000),
            //			new Trap(numBits : 8, maxIterations : 1000),
            //			new Trap(numBits : 16, maxIterations : 1000),
            //new HIFF(numBits : 4, maxIterations : 1000),
            //new HIFF(numBits : 8, maxIterations : 1000),
            //new HIFF(numBits : 16, maxIterations : 1000),
            //new HIFF(numBits : 32, maxIterations : 1000)
        ]
        // It would be nice to collect the total time here and include it in the
        // output.
        runExperiment(searchers, problems)
    }

}
