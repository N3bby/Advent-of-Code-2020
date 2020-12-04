package day2

fun parsePasswordsWithPasswordPolicies(
    lines: List<String>,
    policyConstructor: (Int, Int, Char) -> PasswordPolicy
): List<Password> {
    return lines.map {
        val (policyStr, passwordStr) = it.split(":")
        val (charOccurrenceRange, character) = policyStr.split(" ")
        val (minCharOccurrence, maxCharOccurrence) = charOccurrenceRange.split("-")
        Password(
            policyConstructor(minCharOccurrence.toInt(), maxCharOccurrence.toInt(), character[0]),
            passwordStr.trim()
        )
    }
}

fun getValidPasswordCount(passwords: List<Password>): Int = passwords.count(Password::isValid)
