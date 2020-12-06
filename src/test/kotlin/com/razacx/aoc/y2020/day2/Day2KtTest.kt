package com.razacx.aoc.y2020.day2

import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList
import kotlin.test.assertEquals

class Day2KtTest {

    @Test
    fun parsePasswordsAndPolicies_correctlyParsesInput() {
        val passwords = parsePasswordsWithPasswordPolicies(
            listOf(
                "1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc",
            ),
            ::createSledRentalPlacePasswordPolicy
        )
        assertEquals(
            listOf(
                Password(SledRentalPlacePasswordPolicy(1, 3, 'a'), "abcde"),
                Password(SledRentalPlacePasswordPolicy(1, 3, 'b'), "cdefg"),
                Password(SledRentalPlacePasswordPolicy(2, 9, 'c'), "ccccccccc"),
            ),
            passwords
        )
    }

    @Test
    fun getValidPasswordCount_returns2ForExamplePasswords() {
        val validPasswordCount = getValidPasswordCount(
            listOf(
                Password(SledRentalPlacePasswordPolicy(1, 3, 'a'), "abcde"),
                Password(SledRentalPlacePasswordPolicy(1, 3, 'b'), "cdefg"),
                Password(SledRentalPlacePasswordPolicy(2, 9, 'c'), "ccccccccc"),
            )
        )
        assertEquals(2, validPasswordCount)
    }

    @Test
    fun getValidPasswordCount_usingSledRentalPlacePasswordPolicy_usingPuzzleInput() {
        val passwords = parsePasswordsWithPasswordPolicies(getPuzzlePasswords(), ::createSledRentalPlacePasswordPolicy)
        val validPasswordCount = getValidPasswordCount(passwords)
        println("Day 2 Part 1 | Valid passwords with SledRentalPlacePasswordPolicy: $validPasswordCount")
    }

    @Test
    fun getValidPasswordCount_usingTobogganCorporatePasswordPolicy_usingPuzzleInput() {
        val passwords = parsePasswordsWithPasswordPolicies(getPuzzlePasswords(), ::createTobogganCorporatePasswordPolicy)
        val validPasswordCount = getValidPasswordCount(passwords)
        println("Day 2 Part 2 | Valid passwords with TobogganCorporatePasswordPolicy: $validPasswordCount")
    }

    private fun getPuzzlePasswords() =
        BufferedReader(InputStreamReader(javaClass.classLoader.getResourceAsStream("day2/passwords.txt")))
            .lines()
            .toList()

}