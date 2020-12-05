package com.razacx.aoc.y2020.day5

import org.junit.jupiter.api.Test
import com.razacx.aoc.y2020.util.IOUtils.Companion.readLines
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun `creating SeatPosition using binary partition string gives correct row and column numbers`() {
        assertEquals(SeatPosition(70, 7), BoardingPass(("BFFFBBFRRR")).getSeatPosition())
        assertEquals(SeatPosition(14, 7), BoardingPass(("FFFBBBFRRR")).getSeatPosition())
        assertEquals(SeatPosition(102, 4), BoardingPass(("BBFFBBFRLL")).getSeatPosition())
    }

    @Test
    fun `SeatPosition seatID is correctly calculated from the row and column number`() {
        assertEquals(567, SeatPosition(70, 7).seatID)
        assertEquals(119, SeatPosition(14, 7).seatID)
        assertEquals(820, SeatPosition(102, 4).seatID)
    }

    @Test
    fun getBoardingPassWithLargestSeatID_usingPuzzleInput() {
        val largestSeatID = getSeatPositions()
            .map { it.seatID }
            .maxOrNull()
        println(largestSeatID)
    }

    @Test
    fun findMySeat_usingPuzzleInput() {
        val sortedSeatIDsExcludingFirstAndLastRow = getSeatPositions()
            .filter { it.seatID > 7 && it.seatID < 127 * 8 }
            .sortedBy { it.seatID }
            .map { it.seatID }

        for(i in sortedSeatIDsExcludingFirstAndLastRow.indices) {
            if(i > 0) {
                val previousSeatID = sortedSeatIDsExcludingFirstAndLastRow[i - 1]
                val seatID = sortedSeatIDsExcludingFirstAndLastRow[i]
                if(previousSeatID + 1 != seatID) {
                    println("Found some missing Seat ID(s) between $previousSeatID and $seatID")
                    println("Your seat is probably ${seatID-1}")
                }
            }
        }
    }

    private fun getSeatPositions(): List<SeatPosition> {
        return readLines("day5/boarding-passes.txt")
            .map { BoardingPass(it).getSeatPosition() }
    }

}