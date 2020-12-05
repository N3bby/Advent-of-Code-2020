package com.razacx.aoc.y2020.day3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import com.razacx.aoc.y2020.util.Position
import com.razacx.aoc.y2020.day3.ForestTileType.EMPTY as E
import com.razacx.aoc.y2020.day3.ForestTileType.TREE as T

class ForestKtTest {

    @Test
    fun createForest_createsForestGrid() {
        val lines = listOf(
            "..##.",
            "#...#",
            ".#...",
            "..#.#"
        )
        val forest = createForest(lines)
        assertEquals(
            Forest(
                arrayOf(
                    arrayOf(E, E, T, T, E),
                    arrayOf(T, E, E, E, T),
                    arrayOf(E, T, E, E, E),
                    arrayOf(E, E, T, E, T),
                )
            ), forest
        )
    }

    @Test
    fun forest_repeatsHorizontally() {
        val forest = Forest(arrayOf(arrayOf(E, T)))
        val horizontalValues = listOf(
            forest.getAt(Position(0, 0)),
            forest.getAt(Position(1, 0)),
        )
        val horizontalValuesOverrun = listOf(
            forest.getAt(Position(0 + 4, 0)),
            forest.getAt(Position(1 + 4, 0)),
        )
        assertEquals(horizontalValues, horizontalValuesOverrun)
    }

    @Test
    fun forest_countTreesOnSlope_returnsAllTreesMet() {
        val forest = Forest(
            arrayOf(
                // Made a bit longer so you don't have to think about the horizontal repeating yourself for this test
                arrayOf(E, E, T, T, E, E, T, T),
                arrayOf(T, T, E, E, T, T, E, E),
                arrayOf(E, T, T, E, E, T, T, E),
                arrayOf(E, E, T, E, E, E, T, E),
            )
        )
        assertEquals(1, forest.countTreesOnSlope(Slope(2, 1)))
        assertEquals(2, forest.countTreesOnSlope(Slope(1, 1)))
        assertEquals(1, forest.countTreesOnSlope(Slope(3, 1)))
    }
}