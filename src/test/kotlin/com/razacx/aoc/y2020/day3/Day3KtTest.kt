package com.razacx.aoc.y2020.day3

import org.junit.jupiter.api.Test
import com.razacx.aoc.y2020.util.IOUtils.Companion.readLines

class Day3KtTest {

    @Test
    fun countTreesHit_usingPuzzleInput() {
        val forest = getForestFromPuzzleInput()
        println("Day 3 Part 1 | Trees hit using slope (3, 1): " + forest.countTreesOnSlope(Slope(3, 1)))
    }

    @Test
    fun getMultiplicationOfTreesHitOnDifferentSlopes_usingPuzzleInput() {
        val forest = getForestFromPuzzleInput()
        val slopes = listOf(
            Slope(1, 1),
            Slope(3, 1),
            Slope(5, 1),
            Slope(7, 1),
            Slope(1, 2)
        )

        val multiplication = slopes
            .map { forest.countTreesOnSlope(it) }
            .reduce { multiplication, treesOnThisSlope -> multiplication * treesOnThisSlope }
        println("Day 3 Part 2 | Result of multiplication of trees hit from different slopes: $multiplication")
    }

    private fun getForestFromPuzzleInput(): Forest {
        return createForest(readLines("day3/forest.txt"))
    }

}