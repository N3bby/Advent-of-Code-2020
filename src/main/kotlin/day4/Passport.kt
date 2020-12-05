package day4

import day4.PassportField.*

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

    fun isValid(): Boolean {
        return birthYearValid() &&
                issueYearValid() &&
                expirationYearValid() &&
                heightValid() &&
                hairColorValid() &&
                eyeColorValid() &&
                passportIdValid()
    }

    private fun birthYearValid(): Boolean = yearValid(birthYear, 1920..2002)

    private fun issueYearValid(): Boolean = yearValid(issueYear, 2010..2020)

    private fun expirationYearValid(): Boolean = yearValid(expirationYear, 2020..2030)

    private fun heightValid(): Boolean {
        if (height == null) return false
        return if ("\\d+cm".toRegex().matches(height)) {
            height.replace("cm", "").toInt() in 150..193
        } else if ("\\d+in".toRegex().matches(height)) {
            height.replace("in", "").toInt() in 59..76
        } else {
            false
        }
    }

    private fun hairColorValid(): Boolean {
        if (hairColor == null) return false
        return "#[0-9a-f]{6}".toRegex().matches(hairColor)
    }

    private fun eyeColorValid(): Boolean {
        if (eyeColor == null) return false
        return "amb|blu|brn|gry|grn|hzl|oth".toRegex().matches(eyeColor)
    }

    private fun passportIdValid(): Boolean {
        if (passportId == null) return false
        return "\\d{9}".toRegex().matches(passportId)
    }

    private fun getValue(passportField: PassportField): String? {
        val fields = passport.replace("\n", " ").split(" ")
        val field = fields.find { it.startsWith(passportField.fieldName) }
        return field?.split(":")?.get(1)
    }

    private fun yearValid(year: String?, range: IntRange): Boolean {
        if (year == null) return false
        return if ("\\d{4}".toRegex().matches(year)) {
            year.toInt() in range
        } else {
            false
        }
    }

}

fun parsePassports(data: String): List<Passport> {
    return data.split("\n\n").map { Passport(it) }
}