package gpMethods.functions.numeric

import gpMethods.Function;

class Modulus extends Function {
	def function = {x, y -> x % y}
	def name = "Mod"
	def type = "prefix"
}