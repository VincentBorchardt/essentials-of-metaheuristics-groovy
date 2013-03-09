package gpMethods.functions

class Function{
	def function
	def name
	def type
	
	def clone() {
		return this
	}
	
	@Override
	String toString() {
		return name
	}
}