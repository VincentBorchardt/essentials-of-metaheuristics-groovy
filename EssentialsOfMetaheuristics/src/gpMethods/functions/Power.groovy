package gpMethods.functions

class Power extends Function {
	def function = {x, y -> if (x == 0 && y == 0) { 1 } else { Math.pow(x, y) }}
	def type = "prefix"
	def name = "Power"
}