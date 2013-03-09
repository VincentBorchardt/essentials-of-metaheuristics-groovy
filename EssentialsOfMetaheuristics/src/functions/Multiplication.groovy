package functions

class Multiplication extends Function{
	def function = {x, y -> x * y}
	
	@Override
	String toString(){
		"Multiplication"
	}
}