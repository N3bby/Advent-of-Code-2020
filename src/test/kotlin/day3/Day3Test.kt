package day3

import org.junit.jupiter.api.Test
import util.IOUtils.Companion.readLines

class Day3Test {

    @Test
    fun countTreesHit_usingPuzzleInput() {
        val forest = getForestFromPuzzleInput()
        println(forest.countTreesOnSlope(Slope(3, 1)))
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
        println(multiplication)
    }

    private fun getForestFromPuzzleInput(): Forest {
        return createForest(readLines("day3/forest.txt"))
    }

}