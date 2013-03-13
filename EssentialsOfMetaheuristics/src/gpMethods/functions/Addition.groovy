package gpMethods.functions

class Addition extends Function {
	def function = {x, y -> x + y}
	def name = "+"
	def type = "infix"
	
	@Override
	String toString() {
		return name
	}
}