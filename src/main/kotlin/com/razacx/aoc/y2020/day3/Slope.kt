package com.razacx.aoc.y2020.day3

import com.razacx.aoc.y2020.util.Position

data class Slope(val right: Int, val down: Int)

operator fun Position.plus(slope: Slope): Position {
    return Position(x + slope.right, y + slope.down)
}