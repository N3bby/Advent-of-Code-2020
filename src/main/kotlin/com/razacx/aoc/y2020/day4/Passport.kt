package com.razacx.aoc.y2020.day4

import com.razacx.aoc.y2020.day4.PassportField.*

enum class PassportField(val fieldName: String) {
    BIRTH_YEAR("byr"),
    ISSUE_YEAR("iyr"),
    EXPIRATION_YEAR("eyr"),
    HEIGHT("hgt"),
    HAIR_COLOR("hcl"),
    EYE_COLOR("ecl"),
    PASSPORT_ID("pid");
}

data class Passport(private val passport: String) {

    private val birthYear = getValue(BIRTH_YEAR)
    private val issueYear = getValue(ISSUE_YEAR)
    private val expirationYear = getValue(EXPIRATION_YEAR)
    private val height = getValue(HEIGHT)
    private val hairColor = getValue(HAIR_COLOR)
    private val eyeColor = getValue(EYE_COLOR)
    private val passportId = getValue(PASSPORT_ID)

    fun areRequiredFieldsPresent(): Boolean {
        return PassportField.values().all { passport.contains("${it.fieldName}:".toRegex()) }
    }

    fun isValid(): Boolean {
        return isBirthYearValid() &&
                isIssueYearValid() &&
                isExpirationYearValid() &&
                isHeightValid() &&
                isHairColorValid() &&
                isEyeColorValid() &&
                isPassportIdValid()
    }

    private fun isBirthYearValid(): Boolean = isYearValid(birthYear, 1920..2002)

    private fun isIssueYearValid(): Boolean = isYearValid(issueYear, 2010..2020)

    private fun isExpirationYearValid(): Boolean = isYearValid(expirationYear, 2020..2030)

    private fun isHeightValid(): Boolean {
        if (height == null) return false
        return when {
            "\\d+cm".toRegex().matches(height) -> height.replace("cm", "").toInt() in 150..193
            "\\d+in".toRegex().matches(height) -> height.replace("in", "").toInt() in 59..76
            else -> false
        }
    }

    private fun isHairColorValid(): Boolean = doesRegexMatch(hairColor, "#[0-9a-f]{6}".toRegex())

    private fun isEyeColorValid(): Boolean = doesRegexMatch(eyeColor, "amb|blu|brn|gry|grn|hzl|oth".toRegex())

    private fun isPassportIdValid(): Boolean = doesRegexMatch(passportId, "\\d{9}".toRegex())

    private fun getValue(passportField: PassportField): String? {
        val fields = passport.replace("\n", " ").split(" ")
        val field = fields.find { it.startsWith(passportField.fieldName) }
        return field?.split(":")?.get(1)
    }

    private fun isYearValid(year: String?, range: IntRange): Boolean {
        if (year == null) return false
        return if ("\\d{4}".toRegex().matches(year)) {
            year.toInt() in range
        } else {
            false
        }
    }

    private fun doesRegexMatch(str: String?, regex: Regex): Boolean {
        if(str == null) return false
        return regex.matches(str)
    }

}

fun parsePassports(data: String): List<Passport> {
    return data.split("\n\n").map { Passport(it) }
}