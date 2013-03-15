package gpMethods.functions.bool

import gpMethods.Function

class BoolNot extends Function {
	def function = {x -> !x}
	def name = "!"
	def type = "prefix"
	
	@Override
	String toString() {
		return name
	}
}