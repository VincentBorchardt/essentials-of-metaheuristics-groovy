package gpMethods.functions.bool

import gpMethods.Function

class BoolIf extends Function {
	def function = {x, y, z -> x ? y : z}
	def name = "if"
	def type = "if"
	
	@Override
	String toString() {
		return name
	}
}