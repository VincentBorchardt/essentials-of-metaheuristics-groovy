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
	static isWindows = true
	static noDisplay = false
	static id
	static template = "Trollbot"

	static main(args) {
		if (isWindows) {
			battleRunner = new BattleRunner("templates\\battleWindows.template", template)
		} else {
			battleRunner = new BattleRunner("templates/battle.template", template)
		}
		id = random.nextInt(1000000)
		def changeDirPeriod = 5
		def optimalDistance = 250
		def rammingDistance = 70
		def bT = 0*Math.PI/2
		def vT = 7

		def values = ["id":id, "changeDirPeriod":changeDirPeriod, "optimalDistance":optimalDistance, "rammingDistance":rammingDistance, "bT":bT, "vT":vT]

		if (isWindows) {
			robotBuilder = new RobotBuilder("templates\\${template}.template", template)
		} else {
			robotBuilder = new RobotBuilder("templates/${template}.template", template)
		}
		robotBuilder.buildJarFile(values,isWindows)

		battleRunner.buildBattleFile(id,isWindows)

		def results = battleRunner.runBattle(id,noDisplay,isWindows)
	}
}