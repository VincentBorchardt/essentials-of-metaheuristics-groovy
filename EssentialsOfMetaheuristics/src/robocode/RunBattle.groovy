package robocode

class RunBattle {
	static Random random = new Random()
	
	static runBattle(template, values, noDisplay=true, isWindows=true) {
		def RobotBuilder robotBuilder
		def BattleRunner battleRunner
		def id = values.get("id")
		if (isWindows) {
			robotBuilder = new RobotBuilder("templates\\HawkOnFireOS.template")
		} else {
			robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
		}
		robotBuilder.buildJarFile(values,isWindows)
		
		if (isWindows) {
			battleRunner = new BattleRunner("templates\\battle.template")
		} else {
			battleRunner = new BattleRunner("templates/battle.template")
		}
		
		battleRunner.buildBattleFile(id,isWindows)
		
		return battleRunner.runBattle(id,noDisplay,isWindows)
		// returns list of lines from console output
	}
	
	static getScore(scoreText, id) {
		def result = "missing"
		def pattern = ~/evolved\.Individual_${id}\s+(\d+)/
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