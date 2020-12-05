package day5

import kotlin.math.pow

val BOARDING_PASS_REGEX = "[BF]{7}[LR]{3}".toRegex()

data class BoardingPass(private val boardingPass: String) {

    init {
        if(!BOARDING_PASS_REGEX.matches(boardingPass)) {
            throw IllegalArgumentException("BoardingPass must match regex '${BOARDING_PASS_REGEX.pattern}'")
        }
    }

    fun getSeatPosition(): SeatPosition {
        return SeatPosition(
            getNumberFromBinaryPartitionString(boardingPass.take(7)),
            getNumberFromBinaryPartitionString(boardingPass.takeLast(3))
        )

    }

    private fun getNumberFromBinaryPartitionString(binaryPartitionString: String): Int {
        var range = 0..(2.0.pow(binaryPartitionString.length).toInt())
        for (c in binaryPartitionString) {
            when (c) {
                'F', 'L' -> range = range.first..range.first + range.size / 2
                'B', 'R' -> range = range.last - range.size / 2..range.last
            }
        }
        return range.first
    }

}

data class SeatPosition(val row: Int, val column: Int) {
    val seatID: Int get() = row * 8 + column
}

val IntRange.size: Int get() = last - first