package com.razacx.aoc.y2020.day3

import com.razacx.aoc.y2020.day3.ForestTileType.TREE
import com.razacx.aoc.y2020.util.HorizontallyRepeatingGrid
import com.razacx.aoc.y2020.util.Position
import java.lang.IllegalArgumentException

typealias Forest = HorizontallyRepeatingGrid<ForestTileType>

enum class ForestTileType(private val character: Char) {
    EMPTY('.'),
    TREE('#');

    companion object {
        fun getForCharacter(character: Char): ForestTileType {
            return values().find { it.character == character }
                ?: throw IllegalArgumentException("No ForestTileType found for character '$character'")
        }
    }
}

fun createForest(lines: List<String>): Forest {
    return Forest(
        lines.map { line ->
            line.toCharArray().map { character -> ForestTileType.getForCharacter(character) }.toTypedArray()
        }.toTypedArray()
    )
}

fun Forest.countTreesOnSlope(slope: Slope): Int {
    var position = Position(0, 0)
    var treesHit = 0
    while ((position + slope).y < height) {
        position += slope
        if (getAt(position) == TREE) {
            treesHit++
        }
    }
    return treesHit
}

