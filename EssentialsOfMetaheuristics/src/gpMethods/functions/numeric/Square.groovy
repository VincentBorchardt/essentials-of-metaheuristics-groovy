package gpMethods.functions.numeric

import gpMethods.Function;

class Square extends Function {
	def function = {x -> x * x}
	def name = "^2"
	def type = "postfix"
}