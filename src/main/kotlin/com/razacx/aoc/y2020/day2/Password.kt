package com.razacx.aoc.y2020.day2

data class Password(val passwordPolicy: PasswordPolicy, val password: String) {
    fun isValid(): Boolean = passwordPolicy.isValid(password)
}