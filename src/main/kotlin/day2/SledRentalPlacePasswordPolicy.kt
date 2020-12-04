package day2

data class SledRentalPlacePasswordPolicy(
    private val charMinOccurrence: Int,
    private val charMaxOccurrence: Int,
    private val character: Char
) : PasswordPolicy {

    override fun isValid(password: String): Boolean {
        val passwordCharacters = password.toCharArray()
        val characterOccurrenceMap =
            passwordCharacters.toSet().map { char -> Pair(char, passwordCharacters.count { it == char }) }.toMap()

        val characterOccurrence = characterOccurrenceMap.getOrDefault(character, 0)

        return characterOccurrence in charMinOccurrence..charMaxOccurrence
    }

}

fun createSledRentalPlacePasswordPolicy(
    charMinOccurrence: Int,
    charMaxOccurrence: Int,
    character: Char
): SledRentalPlacePasswordPolicy {
    return SledRentalPlacePasswordPolicy(charMinOccurrence, charMaxOccurrence, character)
}