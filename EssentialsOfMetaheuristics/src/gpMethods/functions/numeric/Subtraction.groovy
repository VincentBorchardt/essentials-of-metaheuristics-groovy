package gpMethods.functions.numeric

import gpMethods.Function;

class Subtraction extends Function {
	def function = {x, y -> x - y}
	def name = "-"
	def type = "infix"
	
	@Override
	String toString() {
		return name
	}
}