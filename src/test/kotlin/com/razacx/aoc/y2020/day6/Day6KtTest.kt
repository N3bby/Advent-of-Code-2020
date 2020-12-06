package com.razacx.aoc.y2020.day6

import com.razacx.aoc.y2020.util.IOUtils.Companion.readText
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6KtTest {

    @Test
    fun getSumOfUniqueQuestionsAnsweredYesToPerGroup_usingExampleInput() {
        val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
        assertEquals(11, getSumOfUniqueQuestionsAnsweredYesToPerGroup(input))
    }

    @Test
    fun getSumOfQuestionsEveryoneAnsweredPerGroup_usingExampleInput() {
        val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
        assertEquals(6, getSumOfQuestionsEveryoneAnsweredYesToPerGroup(input))
    }

    @Test
    fun getSumOfUniqueQuestionAnsweredYesToPerGroup_usingPuzzleInput() {
        val input = readText("day6/answers.txt")
        println("Day 6 Part 1 | Sum of unique questions people answered 'yes' to per group: " + getSumOfUniqueQuestionsAnsweredYesToPerGroup(input))
    }

    @Test
    fun getSumOfQuestionsEveryoneAnsweredPerGroup_usingPuzleInput() {
        val input = readText("day6/answers.txt")
        println("Day 6 Part 2 | Sum of questions everyone answered 'yes' to per group: " + getSumOfQuestionsEveryoneAnsweredYesToPerGroup(input))
    }

}