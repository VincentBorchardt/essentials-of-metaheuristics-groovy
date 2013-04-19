package robocode

/*
 * This assumes that there is a copy of Robocode in your home directory,
 * and that it has been configured (via the GUI) to be able to load robot
 * files from the evolved_robots directory in this project.
 */
class WatchBattle {
	static Random random = new Random()
	/*
	 * id : an id used in the generation of the name of the class.
	 * enemy_energy : the coefficient for the enemy's energy
	 * my_energy : the coefficient for our energy
	 * angle_diff : the coefficient for the different in angles between us and the point and then and the point
	 * distance : the coefficient for the distance between the point and the enemy
	 */
	static BattleRunner battleRunner
	static RobotBuilder robotBuilder
	static isWindows = false
	static noDisplay = false
	static id

	static main(args) {
		if (isWindows) {
			battleRunner = new BattleRunner("templates\\battleWindows.template")
		} else {
			battleRunner = new BattleRunner("templates/battle.template")
		}
		id = random.nextInt(1000000)
		def enemy_energy = 17//random.nextFloat() * 100
		def my_energy = 67//random.nextFloat() * 100
		def angle_diff = 79//random.nextFloat() * 100
		def distance = 68//random.nextFloat() * 100
		def movementPerturbation = 0 //(2 * random.nextFloat()-1) * Math.PI / 8

		def values = ["id" : id, "enemy_energy" : enemy_energy, "my_energy" : my_energy, "angle_diff" : angle_diff,
			"distance" : distance, "movementPerturbation": movementPerturbation]

		if (isWindows) {
			robotBuilder = new RobotBuilder("templates\\HawkOnFireOS.template")
		} else {
			robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
		}
		robotBuilder.buildJarFile(values,isWindows)

		battleRunner.buildBattleFile(id,isWindows)

		def results = battleRunner.runBattle(id,noDisplay,isWindows)
	}
}