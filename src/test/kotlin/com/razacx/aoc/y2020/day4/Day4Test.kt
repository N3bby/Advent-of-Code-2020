package com.razacx.aoc.y2020.day4

import org.junit.jupiter.api.Test
import com.razacx.aoc.y2020.util.IOUtils.Companion.readText
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PassportTest {

    @Test
    fun passport_isValid_returnsTrueIfAllFieldsArePresent() {
        val passport = Passport(
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                    "byr:1937 iyr:2017 cid:147 hgt:183cm"
        )
        assertTrue(passport.isValid())
    }

    @Test
    fun passport_isValid_returnsTrueIfOnlyCountryIDIsMissing() {
        val passport = Passport(
            "hcl:#ae17e1 iyr:2013\n" +
                    "eyr:2024\n" +
                    "ecl:brn pid:760753108 byr:1931\n" +
                    "hgt:179cm\n"
        )
        assertTrue(passport.isValid())
    }

    @Test
    fun passport_isValid_returnsFalseIfAnyOtherFieldIsMissing() {
        val passport = Passport(
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                    "hcl:#cfa07d byr:1929"
        )
        assertFalse(passport.isValid())
    }

    @Test
    fun passport_isValid_birthYear() {
        assertFalse(createPassport(birthYear = "1919").isValid())
        assertTrue(createPassport(birthYear = "1920").isValid())
        assertTrue(createPassport(birthYear = "2002").isValid())
        assertFalse(createPassport(birthYear = "2003").isValid())
        assertFalse(createPassport(birthYear = "abc").isValid())
        assertFalse(createPassport(birthYear = null).isValid())
    }

    @Test
    fun passport_isValid_issueYear() {
        assertFalse(createPassport(issueYear = "2009").isValid())
        assertTrue(createPassport(issueYear = "2010").isValid())
        assertTrue(createPassport(issueYear = "2020").isValid())
        assertFalse(createPassport(issueYear = "2021").isValid())
        assertFalse(createPassport(issueYear = "abc").isValid())
        assertFalse(createPassport(issueYear = null).isValid())
    }

    @Test
    fun passport_isValid_expirationYear() {
        assertFalse(createPassport(expirationYear = "2019").isValid())
        assertTrue(createPassport(expirationYear = "2020").isValid())
        assertTrue(createPassport(expirationYear = "2030").isValid())
        assertFalse(createPassport(expirationYear = "2031").isValid())
        assertFalse(createPassport(expirationYear = "abc").isValid())
        assertFalse(createPassport(expirationYear = null).isValid())
    }

    @Test
    fun passport_isValid_height() {
        assertFalse(createPassport(height = "149cm").isValid())
        assertTrue(createPassport(height = "150cm").isValid())
        assertTrue(createPassport(height = "193cm").isValid())
        assertFalse(createPassport(height = "194cm").isValid())

        assertFalse(createPassport(height = "58in").isValid())
        assertTrue(createPassport(height = "59in").isValid())
        assertTrue(createPassport(height = "76in").isValid())
        assertFalse(createPassport(height = "77in").isValid())

        assertFalse(createPassport(height = "60").isValid())
        assertFalse(createPassport(height = "160").isValid())
        assertFalse(createPassport(height = "cm").isValid())
        assertFalse(createPassport(height = "in").isValid())
        assertFalse(createPassport(height = "a").isValid())
        assertFalse(createPassport(height = "").isValid())
        assertFalse(createPassport(height = null).isValid())
    }

    @Test
    fun passport_isValid_hairColor() {
        assertTrue(createPassport(hairColor = "#000000").isValid())
        assertTrue(createPassport(hairColor = "#999999").isValid())
        assertTrue(createPassport(hairColor = "#aaaaaa").isValid())
        assertTrue(createPassport(hairColor = "#ffffff").isValid())
        assertTrue(createPassport(hairColor = "#f8b0d3").isValid())

        assertFalse(createPassport(hairColor = "#12345").isValid())
        assertFalse(createPassport(hairColor = "#1234567").isValid())
        assertFalse(createPassport(hairColor = "#gggggg").isValid())
        assertFalse(createPassport(hairColor = "123456").isValid())
        assertFalse(createPassport(hairColor = "").isValid())
        assertFalse(createPassport(hairColor = null).isValid())
    }

    @Test
    fun passport_isValid_eyeColor() {
        assertTrue(createPassport(eyeColor = "amb").isValid())
        assertTrue(createPassport(eyeColor = "blu").isValid())
        assertTrue(createPassport(eyeColor = "brn").isValid())
        assertTrue(createPassport(eyeColor = "gry").isValid())
        assertTrue(createPassport(eyeColor = "grn").isValid())
        assertTrue(createPassport(eyeColor = "hzl").isValid())
        assertTrue(createPassport(eyeColor = "oth").isValid())

        assertFalse(createPassport(eyeColor = "amba").isValid())
        assertFalse(createPassport(eyeColor = "abc").isValid())
        assertFalse(createPassport(eyeColor = "").isValid())
        assertFalse(createPassport(eyeColor = null).isValid())
    }

    @Test
    fun passport_isValid_passportID() {
        assertTrue(createPassport(passportID = "000000001").isValid())

        assertFalse(createPassport(passportID = "0123456789").isValid())
        assertFalse(createPassport(passportID = "01234567").isValid())
        assertFalse(createPassport(passportID = "000a00001").isValid())
        assertFalse(createPassport(passportID = "").isValid())
        assertFalse(createPassport(passportID = null).isValid())
    }

    @Test
    fun parsePassports_splitsByBlankLine() {
        val passportBatch = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm

            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929

            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm

            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()
        val passports = parsePassports(passportBatch)
        assertEquals(
            listOf(
                Passport("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm"),
                Passport("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929"),
                Passport("hcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm"),
                Passport("hcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"),
            ), passports
        )
    }

    @Test
    fun countValidPassports_usingPuzzleInput() {
        val passports = parsePassports(
            readText("day4/passport-batch.txt").replace(
                "\r",
                ""
            )
        ) // Damn Windows and its carriage returns
        println("Day 4 Part 2 | Valid passports using extended validation rules: " + passports.count(Passport::isValid))
    }

    private fun createPassport(
        birthYear: String? = "1920",
        issueYear: String? = "2010",
        expirationYear: String? = "2020",
        height: String? = "150cm",
        hairColor: String? = "#000000",
        eyeColor: String? = "amb",
        passportID: String? = "000000001"
    ): Passport {
        var passportStr = ""
        if (birthYear != null) {
            passportStr += " byr:$birthYear"
        }
        if(issueYear != null) {
            passportStr += " iyr:$issueYear"
        }
        if(expirationYear != null) {
            passportStr += " eyr:$expirationYear"
        }
        if(height != null) {
            passportStr += " hgt:$height"
        }
        if(hairColor != null) {
            passportStr += " hcl:$hairColor"
        }
        if(eyeColor != null) {
            passportStr += " ecl:$eyeColor"
        }
        if(passportID != null) {
            passportStr += " pid:$passportID"
        }
        return Passport(passportStr.trim())
    }

}