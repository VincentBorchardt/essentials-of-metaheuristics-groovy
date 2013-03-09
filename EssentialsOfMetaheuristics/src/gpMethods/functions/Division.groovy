package gpMethods.functions

class Division extends Function {
	def function = {x, y -> if (y == 0) { return 1 } else { return x / y }}
	def name = "/"
	def type = "infix"
}