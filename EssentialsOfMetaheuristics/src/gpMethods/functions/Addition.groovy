package gpMethods.functions

class Addition extends Function{
	def function = {x, y -> x + y}
	def name = "Addition"
	
	@Override
	String toString() {
		return name
	}
}