package com.razacx.aoc.y2020.day2

fun parsePasswordsWithPasswordPolicies(
    lines: List<String>,
    policyConstructor: (Int, Int, Char) -> PasswordPolicy
): List<Password> {
    return lines.map {
        val (policyStr, passwordStr) = it.split(":")
        val (numbers, character) = policyStr.split(" ")
        val (number1, number2) = numbers.split("-")
        Password(
            policyConstructor(number1.toInt(), number2.toInt(), character[0]),
            passwordStr.trim()
        )
    }
}

fun getValidPasswordCount(passwords: List<Password>): Int = passwords.count(Password::isValid)
