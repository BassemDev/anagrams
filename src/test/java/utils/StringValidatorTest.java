package utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ghazouanibassem.utils.StringValidatorUtil;

public class StringValidatorTest {

    @Test
    @DisplayName("Should verify that the word is formed only with letter")
    void shouldVerifyStringCharacters() {
        // Given
        String firstSample = "test";
        String secondSample = "IAMCorrect";
        String incorrectFirstSample = "test#";
        String incorrectSecondSample = "test09t";
        String incorrectThirdSample = "test/^";
        String incorrectFourthSample = "test-t";

        // When
        boolean firstSampleOutput = StringValidatorUtil.isStringFormedOnlyWithLetter(firstSample);
        boolean secondSampleOutput = StringValidatorUtil.isStringFormedOnlyWithLetter(secondSample);
        boolean incorrectFirstSampleOutput = StringValidatorUtil.isStringFormedOnlyWithLetter(incorrectFirstSample);
        boolean incorrectSecondSampleOutput = StringValidatorUtil.isStringFormedOnlyWithLetter(incorrectSecondSample);
        boolean incorrectThirdSampleOutput = StringValidatorUtil.isStringFormedOnlyWithLetter(incorrectThirdSample);
        boolean incorrectFourthSampleOutput = StringValidatorUtil.isStringFormedOnlyWithLetter(incorrectFourthSample);

        // Then
        assertTrue(firstSampleOutput, "The validation of word composed of only letters failed");
        assertTrue(secondSampleOutput, "The validation of the word with upper case failed");
        assertFalse(incorrectFirstSampleOutput, "The validation of the word with special chars failed");
        assertFalse(incorrectSecondSampleOutput, "The validation of the word with numbers failed");
        assertFalse(incorrectThirdSampleOutput, "The validation of the word with mixed special chars failed");
        assertFalse(incorrectFourthSampleOutput, "The validation of the word with special char '-' failed");
    }
    
}
