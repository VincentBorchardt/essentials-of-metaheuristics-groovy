package robocode

class RunBattle {
	static Random random = new Random()
	static robotName
	
	static runBattle(robotTemplate, values, noDisplay=true, isWindows=true) {
		robotName = robotTemplate
		def RobotBuilder robotBuilder
		def BattleRunner battleRunner
		def id = values.get("id")
		if (isWindows) {
			robotBuilder = new RobotBuilder("templates\\${robotTemplate}.template", robotTemplate)
		} else {
			robotBuilder = new RobotBuilder("templates/${robotTemplate}.template", robotTemplate)
		}
		robotBuilder.buildJarFile(values,isWindows)
		
		if (isWindows) {
			battleRunner = new BattleRunner("templates\\battleWindows.template", robotName)
		} else {
			battleRunner = new BattleRunner("templates/battle.template", robotName)
		}
		
		battleRunner.buildBattleFile(id,isWindows)
		
		return battleRunner.runBattle(id,noDisplay,isWindows)
		// returns list of lines from console output
	}
	
	static getScore(scoreText, id, isWindows=true) {
		def result = "missing"
		def pattern
		if (isWindows) {
			//pattern = ~/evolved\.${robotName}_${id}\*\s+(\d+)/
			pattern = ~/evolved.HypeMachine_31\s+(\d+)/
		} else {
			pattern = ~/evolved\.${robotName}_${id}\s+(\d+)/
		}
		scoreText.each { line ->
			def m = (line =~ pattern)
			if (m) {
				result = Integer.parseInt(m[0][1])
			}
		}
		if (result != "missing") {
			return result
		} else {
			throw new RuntimeException("Didn't find score for evolved robot")
		}
	}
}