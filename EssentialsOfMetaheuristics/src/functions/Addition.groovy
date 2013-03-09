package functions

class Addition extends Function{
	def function = {x, y -> x + y}
	
	@Override
	String toString(){
		"Addition"
	}
}