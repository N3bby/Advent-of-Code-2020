package day1

import java.lang.IllegalArgumentException

fun solveExpenseReportPart1(numbers: List<Int>, sum: Int): Int {
    val pair = findPairThatSumsTo(numbers, sum)
        ?: throw IllegalArgumentException("No pair was found that sums to the given number")
    return pair.first * pair.second;
}

fun solveExpenseReportPart2(numbers: List<Int>, sum: Int): Int {
    val triple = findTripleThatSumsTo(numbers, sum)
        ?: throw IllegalArgumentException("No triple was found that sums to the given number")
    return triple.first * triple.second * triple.third;
}

fun findPairThatSumsTo(numbers: List<Int>, sum: Int): Pair<Int, Int>? {
    val numberPairs = numbers
        .mapIndexed { index, el -> List(numbers.size - index) { el } zip numbers.drop(index) }
        .flatten()
    return numberPairs.find { it.first + it.second == sum }
}

// Our earlier approach might work, but it won't be very efficient now that we're dealing with triples
// (napkin math -> 200^3 = 8e6 triples to generate and check (if we don't eliminate doubles) )
// So let's try to use a loop instead
fun findTripleThatSumsTo(numbers: List<Int>, sum: Int): Triple<Int, Int, Int>? {
    for (i in numbers.indices) {
        for (j in numbers.indices) {
            if(i == j) continue
            for (k in numbers.indices) {
                if(i == k || j == k) continue
                if (numbers[i] + numbers[j] + numbers[k] == sum) {
                    return Triple(numbers[i], numbers[j], numbers[k])
                }
            }
        }
    }
    return null
}