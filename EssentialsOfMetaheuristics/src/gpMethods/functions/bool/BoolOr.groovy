package gpMethods.functions.bool

import gpMethods.Function

class BoolOr extends Function {
	def function = {x, y -> x || y}
	def name = "||"
	def type = "infix"
	
	@Override
	String toString() {
		return name
	}
}