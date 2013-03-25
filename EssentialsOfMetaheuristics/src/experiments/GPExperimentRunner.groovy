package experiments

import operators.GPOnePointCrossover;
import problems.GP.*;
import singleStateMethods.HillClimber
import populationMethods.GeneticAlgorithm
//import gpMethods.functions.*
import gpMethods.functions.bool.*
import gpMethods.functions.numeric.*
//import singleStateMethods.SteepestAscentHillClimber
//import singleStateMethods.SteepestAscentHillClimberWithReplacement

class GPExperimentRunner {
	static defaultMaxTreeSize = 20
	static defaultNumRuns = 100
	static defaultMaxIterations = 100
	//static defaultNumComparisonPoints = 20
	//static necessaryQualityMinimum = -(defaultNumComparisonPoints-0)/2
	static constantChance = 0.0
	static variableChance = 0.2
	static functionChance = 0.8
	static mutationRate = 0.3
	//static minX = 0
	//static maxX = 2 * Math.PI

    static runExperiment(searchers, problems, numRuns = defaultNumRuns) {
        for (p in problems) {
            for (s in searchers) {
                for (i in 0 ..< numRuns) {
                    p.evalCount = 0
                    def result = s.maximize(p)
					//if (p.quality(result) > necessaryQualityMinimum) {
						println "${s.toString()}\t${p.toString()}\t${p.quality(result)}\t${result}"
					//}
					//println "${s.toString()}\t${p.quality(result)}"
                }
            }
        }
    }

    static main(args) {
		def functionToFit = {map -> Math.sin(map."x")}
		//def functionToFit = {map -> map."x"}
//		def variableList = ["x"]
//		def constantList = [-1, 0.01, 0.05, 0.1, 0.5, 1, 2, 5, 10]
//		def functionList = [new Addition(), new Multiplication()]
		def variableList = ["a0", "a1", "a2", "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7"]
		def constantList = [true, false]
		def functionList = [new BoolAnd(), new BoolOr(), new BoolIf(), new BoolNot()]
		
		//def samplePoints = (0 .. defaultNumComparisonPoints).collect{i -> ["x": minX + (i * maxX / defaultNumComparisonPoints)]}//[["x": 0], ["x": 1]]
		
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
			new Multiplexer11Bit(variableList:variableList, constantList:constantList, functionList:functionList, maxIterations:defaultMaxIterations, treeSize:defaultMaxTreeSize,
				constantChance:constantChance, variableChance:variableChance, functionChance:functionChance, defaultMutationRate:mutationRate)
//			new SymbolicRegression(functionToFit:functionToFit, samplePoints:samplePoints, variableList:variableList, constantList:constantList, functionList:functionList, 
//				maxIterations:defaultMaxIterations, treeSize:defaultMaxTreeSize, constantChance:constantChance, variableChance:variableChance, functionChance:functionChance,
//				defaultMutationRate:mutationRate)
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
