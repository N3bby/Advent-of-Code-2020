package day3

data class Slope(val right: Int, val down: Int)

operator fun Position.plus(slope: Slope): Position {
    return Position(x + slope.right, y + slope.down)
}