package robocode

/*
 * This assumes that there is a copy of Robocode in your home directory,
 * and that it has been configured (via the GUI) to be able to load robot
 * files from the evolved_robots directory in this project.
 */
class WatchBattle {
	/*
	 * id : an id used in the generation of the name of the class.
	 * enemy_energy : the coefficient for the enemy's energy
	 * my_energy : the coefficient for our energy
	 * angle_diff : the coefficient for the different in angles between us and the point and then and the point
	 * distance : the coefficient for the distance between the point and the enemy
	 */
	static BattleRunner battleRunner
	static isWindows = false
	static noDisplay = false
	static id = 845888
	
	static main(args) {
		if (isWindows) {
			battleRunner = new BattleRunner("templates\\battle.template")
		} else {
			battleRunner = new BattleRunner("templates/battle.template")
		}
		
		battleRunner.buildBattleFile(id,isWindows)
	
		def results = battleRunner.runBattle(id,noDisplay,isWindows, true)
		
		
	}
	// ./robocode.sh -battle "/misc/home/lask0064/essentials-of-metaheuristics-groovy-nic/EssentialsOfMetaheuristics/evolved_robots/evolve.battle"
}