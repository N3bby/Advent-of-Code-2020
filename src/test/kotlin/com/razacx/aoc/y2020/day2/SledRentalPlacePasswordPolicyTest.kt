package com.razacx.aoc.y2020.day2

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SledRentalPlacePasswordPolicyTest {

    @Test
    fun password_isValidWithSledRentalPlacePasswordPolicy_returnsFalseIfCharacterDoesNotAppearEnoughTimes() {
        assertFalse { SledRentalPlacePasswordPolicy(1, 3, 'a').isValid("bcdef") }
    }

    @Test
    fun password_isValidWithSledRentalPlacePasswordPolicy_returnsFalseIfCharacterAppearsTooManyTimes() {
        assertFalse { SledRentalPlacePasswordPolicy(1, 3, 'a').isValid("aaaabcdef") }
    }

    @Test
    fun password_isValidWithSledRentalPlacePasswordPolicy_returnsTrueIfCharacterAppearsMinimumAmountOfTimes() {
        assertTrue { SledRentalPlacePasswordPolicy(1, 3, 'a').isValid("abcdef") }
    }

    @Test
    fun password_isValidWithSledRentalPlacePasswordPolicy_returnsTrueIfCharacterAppearsMaximumAmountOfTimes() {
        assertTrue { SledRentalPlacePasswordPolicy(1, 3, 'a').isValid("aaabcdef") }
    }

}