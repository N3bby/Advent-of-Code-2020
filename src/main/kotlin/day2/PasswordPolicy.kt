package day2

interface PasswordPolicy {

    fun isValid(password: String): Boolean

}