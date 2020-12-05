package com.razacx.aoc.y2020.day2

interface PasswordPolicy {

    fun isValid(password: String): Boolean

}