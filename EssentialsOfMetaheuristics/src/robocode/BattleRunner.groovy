package robocode

import groovy.text.SimpleTemplateEngine

// I'm adding a comment via GitHub - how weird is that?!?

/*
 * This assumes that there is a copy of Robocode in your home directory,
 * and that it has been configured (via the GUI) to be able to load robot
 * files from the evolved_robots directory in this project.
 */
class BattleRunner {
	def robocodeDirLinux = System.getProperty("user.home") + "/robocode"
	def robocodeDirWindows = "C:\\robocode"
	def robotDirectory = "evolved_robots"
	def robotDirectoryAbsolute = new File(robotDirectory).absolutePath
	def template

	def BattleRunner(String templateFileName) {
		def engine = new SimpleTemplateEngine()
		template = engine.createTemplate(new File(templateFileName))
	}

	def buildBattleFile(id, isWindows=true) {
		def File battleFile
		if (isWindows) {
			battleFile = new File("${robotDirectory}\\evolve.battle")
		} else {
			battleFile = new File("${robotDirectory}/evolve.battle")
		}
		battleFile.delete()
		battleFile.createNewFile()
		def result = template.make(["id" : id])
		battleFile << result.toString()
	}

	def runBattle(id, nodisplay=true, isWindows=true) {//nodisplay option goes in here
		linkJarFile(id, isWindows)
		File battleFile = new File("${robotDirectory}/evolve.battle")
		def command
		if (isWindows) {
			battleFile = new File("${robotDirectory}\\evolve.battle")
			command = ["${robocodeDirWindows}\\robocode.bat", "-battle", battleFile.absolutePath]
		} else {
			battleFile = new File("${robotDirectory}/evolve.battle")
			command = ["${robocodeDirLinux}/robocode.sh", "-battle", battleFile.absolutePath]
		}
		if (nodisplay) {
			command += "-nodisplay"
		}
		
		def proc = command.execute(null, new File(robotDirectory))
		def errStream
		if (isWindows) {
			def initialSize = 4096
			errStream = new ByteArrayOutputStream(initialSize)
	
			Thread.start{
				try {
					 proc.in.eachLine { line ->
						println line
					 }
				} catch (Exception e) {
					e.printStackTrace()
				}
			}
	
			proc.consumeProcessErrorStream(errStream)
		}
		proc.waitFor()

		if (isWindows) {
			assert errStream.size() == 0, errStream
		}

		assert proc.exitValue() == 0, proc.err.text
		//println proc.err.text
		assert proc.err.text.equals("")
		def lines = proc.in.text.split("\n")
		def result = false
		lines.each { line ->
			def pattern = ~/evolved\.Individual_${id}\s+(\d+)/
			def m = (line =~ pattern)
			if (m) {
				result = Integer.parseInt(m[0][1])
			}
		}
		if (result) {
			return result
		} else {
			throw new RuntimeException("Didn't find score for evolved robot")
		}
	}
	
	def linkJarFile(id, isWindows) {
		if (isWindows) {
			return
		}
		def robotDir
		def command
		if (isWindows) {
			robotDir = new File("${robocodeDirWindows}\\robots/")
			command = ["cmd", "/c", "mklink", "/D", "${robotDirectoryAbsolute}\\Individual_${id}.jar","%CD%"]
		} else {
			robotDir = new File("${robocodeDirLinux}/robots/")
			command = "ln -s ${robotDirectoryAbsolute}/Individual_${id}.jar ."
		}
		def proc = command.execute(null, robotDir)
		def initialSize = 4096
		def errStream = new ByteArrayOutputStream(initialSize)

		Thread.start{
			try {
				 proc.in.eachLine { line ->
					println line
				 }
			} catch (Exception e) {
				e.printStackTrace()
			}
		}

		proc.consumeProcessErrorStream(errStream)
		proc.waitFor()

		assert errStream.size() == 0, errStream

		assert proc.exitValue() == 0, proc.err.text
		assert proc.err.text.equals("")
	}
}
