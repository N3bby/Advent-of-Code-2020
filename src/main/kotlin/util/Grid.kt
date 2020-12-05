package day3

import util.Position

open class Grid<T>(private val grid: Array<Array<T>>) {

    protected val width = grid[0].size
    val height = grid.size

    open fun getAt(position: Position): T {
        return grid[position.y][position.x]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Grid<*>) return false

        if (!grid.contentDeepEquals(other.grid)) return false

        return true
    }

    override fun hashCode(): Int {
        return grid.contentDeepHashCode()
    }

}

class HorizontallyRepeatingGrid<T>(grid: Array<Array<T>>): Grid<T>(grid) {

    override fun getAt(position: Position): T {
        return super.getAt(Position(position.x % width, position.y))
    }

}