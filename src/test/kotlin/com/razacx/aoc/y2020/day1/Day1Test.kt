package com.razacx.aoc.y2020.day1

import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList
import kotlin.test.assertEquals

class Day1Test {

    val exampleNumbers = listOf(
        1721,
        979,
        366,
        299,
        675,
        1456
    );
    val exampleSum = 2020;

    @Test
    fun findPairThatSumTo_returnsPairOfNumbersThatSumToGivenNumber() {
        val entries = findPairThatSumsTo(exampleNumbers, exampleSum)
        assertPairsEqualIgnoringOrder(Pair(1721, 299), entries!!)
    }

    @Test
    fun findPairThatSumsTo_returnsNullIfNoPairWasFound() {
        val entries = findPairThatSumsTo(exampleNumbers, 8574358)
        assertEquals(entries, null)
    }

    @Test
    fun findTripleThatSumsTo_returnsTripleOfNumbersThatSumsToGivenNumber() {
        val entries = findTripleThatSumsTo(exampleNumbers, exampleSum)
        assertTriplesEqualIgnoringOrder(Triple(979, 366, 675), entries!!)
    }

    @Test
    fun solveExpenseReportPart1_returnsCorrectNumber() {
        val multipliedPair = solveExpenseReportPart1(exampleNumbers, exampleSum)
        assertEquals(514579, multipliedPair)
    }

    @Test
    fun solveExpenseReportPart2_returnsCorrectNumber() {
        val multipliedTriple = solveExpenseReportPart2(exampleNumbers, exampleSum);
        assertEquals(241861950, multipliedTriple)
    }

    @Test
    fun solveExpenseReportPart1_withPuzzleInput() {
        println("Day 1 Part 1 | Expense report result for pairs: " + solveExpenseReportPart1(getPuzzleInput(), 2020))
    }

    @Test
    fun solveExpenseReportPart2_withPuzzleInput() {
        println("Day 1 Part 2 | Expense report result for triples: " + solveExpenseReportPart2(getPuzzleInput(), 2020))
    }

    fun assertPairsEqualIgnoringOrder(pair1: Pair<Any, Any>, pair2: Pair<Any, Any>) {
        assert(pair1 == pair2 || pair1 == Pair(pair2.second, pair2.first)) {"$pair1 does not have the same elements as $pair2"}
    }

    fun assertTriplesEqualIgnoringOrder(triple1: Triple<Int, Int, Int>, triple2: Triple<Int, Int, Int>) {
        assert(listOf(triple1.first, triple1.second, triple1.third).sorted() == listOf(triple2.first, triple2.second, triple2.third).sorted()) {"$triple1 does not have the same elements as $triple2"}
    }

    private fun getPuzzleInput() =
        BufferedReader(InputStreamReader(this.javaClass.classLoader.getResourceAsStream("day1/numbers.txt")))
            .lines()
            .map { it.toInt() }
            .toList()

}