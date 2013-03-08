package gpMethods

class Functions {
	static addition = {x, y -> x + y}
	static multiplication = {x, y -> x * y}
	static subtraction = {x, y -> x - y}
	static protectedMultiplication = {x, y -> if (x == 0) { y } else if (y == 0) { x } else { x * y }}// to avoid bloat
	static division = {x, y -> if (y == 0) { x } else { x / y }}
	static pow = {x, y -> if (x == 0) { 1 } else { Math.pow(x, y) }}
	static sin = {x -> Math.sin(x)}
}