package gpMethods.functions

class Square extends Function{
	def function = {x -> x * x}
	def name = "Square"
	
	@Override
	String toString() {
		return name
	}
}