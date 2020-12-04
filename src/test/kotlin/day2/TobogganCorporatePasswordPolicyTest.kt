package day2

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TobogganCorporatePasswordPolicyTest {

    @Test
    fun password_isValidWithTobogganCorporatePasswordPolicy_returnsFalseIfCharacterDoesNotAppearInGivenPositions() {
        assertFalse { TobogganCorporatePasswordPolicy(1, 3, 'a').isValid("cdefg") }
    }

    @Test
    fun password_isValidWithTobogganCorporatePasswordPolicy_returnsFalseIfCharacterAppearsInBothGivenPositions() {
        assertFalse { TobogganCorporatePasswordPolicy(2, 9, 'c').isValid("ccccccccc") }
    }

    @Test
    fun password_isValidWithTobogganCorporatePasswordPolicy_returnsTrueIfCharacterAppearsInOneGivenPosition() {
        assertTrue { TobogganCorporatePasswordPolicy(1, 3, 'a').isValid("abcde") }
    }

    @Test
    fun password_isValidWithTobogganCorporatePasswordPolicy_doesNotCrashOnOutOfRangePositionCheck() {
        assertFalse { TobogganCorporatePasswordPolicy(1, 3, 'a').isValid("b") }
    }

}