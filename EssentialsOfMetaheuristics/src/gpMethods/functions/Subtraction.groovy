package gpMethods.functions

class Subtraction extends Function{
	def function = {x, y -> x - y}
	def name = "Subtraction"
	
	@Override
	String toString() {
		return name
	}
}