package com.razacx.aoc.y2020.day2

data class TobogganCorporatePasswordPolicy(
    private val position1: Int,
    private val position2: Int,
    private val character: Char
) : PasswordPolicy {

    override fun isValid(password: String): Boolean {
        val charAtPos1 = password.getOrNull(position1 - 1)
        val charAtPos2 = password.getOrNull(position2 - 1)
        return (charAtPos1 == character) xor (charAtPos2 == character)
    }

}

fun createTobogganCorporatePasswordPolicy(
    position1: Int,
    position2: Int,
    character: Char
): TobogganCorporatePasswordPolicy {
    return TobogganCorporatePasswordPolicy(position1, position2, character)
}