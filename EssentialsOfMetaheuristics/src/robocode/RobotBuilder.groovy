package robocode

import groovy.text.SimpleTemplateEngine

class RobotBuilder {
	def template
	def robotDirectory = "evolved_robots"
	def robotPackage = "evolved"
	
	def RobotBuilder(String templateFileName) {
		def engine = new SimpleTemplateEngine()
		template = engine.createTemplate(new File(templateFileName))
	}
	
	def buildJarFile(values,isWindows=true) {
		buildClassFile(values,isWindows)
		buildPropertiesFile(values,isWindows)
		def id = values['id']
		def fileNamePrefix = "Individual_${id}"
		def command = "jar -cf ${fileNamePrefix}.jar"
		if (isWindows) {
			[".java", ".class", "\$MicroEnemy.class", ".properties"].each { suffix ->
				command += " ${robotPackage}\\${fileNamePrefix}${suffix}"
			}
		} else {
			[".java", ".class", "\$MicroEnemy.class", ".properties"].each { suffix ->
				command += " ${robotPackage}/${fileNamePrefix}${suffix}"
			}
		}
		def proc = command.execute(null, new File(robotDirectory))
		proc.waitFor()
		println(proc.in.text)
		assert proc.err.text.equals("")
		assert proc.exitValue() == 0
	}
	
	def buildPropertiesFile(values,isWindows=true) {
		def id = values['id']
		def filenamePrefix = "Individual_${id}"
		def propertiesFileName = "${filenamePrefix}.properties"
		if (isWindows) {
			def propertiesFile = new File("${robotDirectory}\\${robotPackage}\\${propertiesFileName}")
			propertiesFile << "robots: ${robotPackage}\\${filenamePrefix}.class"
		} else {
			def propertiesFile = new File("${robotDirectory}/${robotPackage}/${propertiesFileName}")
			propertiesFile << "robots: ${robotPackage}/${filenamePrefix}.class"
		}
	}
	
	def buildClassFile(values,isWindows=true) {
		def javaFileName = buildJavaFile(values,isWindows)
		def command
		if (isWindows) {
			def parentDir = new File(System.getProperty("user.dir"))
			command = ["javac", "-cp", "${parentDir}\\lib\\robocode.jar", "${robotPackage}\\${javaFileName}"]
		} else {
			command = "javac -cp ../lib/robocode.jar ${robotPackage}/${javaFileName}"
		}
		def proc = command.execute(null, new File(robotDirectory))

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
//        println "return code: ${proc.exitValue()}"
//        println "stderr: ${proc.err.text}"
//        println "stdout: ${proc.in.text}"
	}
	
	def buildJavaFile(values,isWindows=true) {
		def javaFileName = makeJavaFileName(values)
		File javaFile = createFile(javaFileName,isWindows)
		writeFile(javaFile, values)
		return javaFileName
	}
	
	def makeJavaFileName(values,isWindows=true) {
		def id = values['id']
		def filename = "Individual_${id}.java"
	}

	private File createFile(javaFileName,isWindows=true) {
		new File(robotDirectory).mkdir()
		File javaFile
		if (isWindows) {
			new File("${robotDirectory}\\${robotPackage}").mkdir()
			javaFile = new File("${robotDirectory}\\${robotPackage}\\${javaFileName}")
		} else {
			new File("${robotDirectory}/${robotPackage}").mkdir()
			javaFile = new File("${robotDirectory}/${robotPackage}/${javaFileName}")
		}
		assert !javaFile.exists()
		javaFile.createNewFile()
		return javaFile
	}

	private writeFile(javaFile, values,isWindows=true) {
		def result = template.make(values)
		javaFile << result.toString()
	}

}
