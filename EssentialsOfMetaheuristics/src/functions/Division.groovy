package functions

class Division extends Function{
	def function = {x, y -> x / y}
	
	@Override
	String toString(){
		"Division"
	}
}