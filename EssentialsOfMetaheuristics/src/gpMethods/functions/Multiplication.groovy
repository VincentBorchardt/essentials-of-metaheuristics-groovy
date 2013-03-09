package gpMethods.functions

class Multiplication extends Function{
	def function = {x, y -> x * y}
	def name = "Multiplication"
	
	@Override
	String toString() {
		return name
	}
}