package anagramUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ghazouanibassem.anagramUtils.AnagramsDictionaryUtil;

public class AnagramsDictionaryUtilTest {

    @Test
    @DisplayName("Should return true if two given words are anagram")
    void shouldReturnForPotentialAnagrams() {
        // Given
        final String firstWord = "ram";
        final String secondWord = "arm";
        final String thirdWord = "listen";
        final String fourthWord = "silent";
        
        // When
        final boolean firstEvaluationResult = AnagramsDictionaryUtil.isValid(firstWord, secondWord);
        final boolean secondEvaluationResult = AnagramsDictionaryUtil.isValid(thirdWord, fourthWord);

        // Then
        assertTrue(firstEvaluationResult, "Failed to evaluate an anagram word to true - first case");
        assertTrue(secondEvaluationResult, "Failed to evaluate an anagram word to true - second case");
    }

    @Test
    @DisplayName("Should return false if two given words are having two different size")
    void shouldReturnFlaseForNonAnagrams() {
        // Given
        final String firstWord = "ramm";
        final String secondWord = "arm";
        
        // When
        final boolean evaluationResult = AnagramsDictionaryUtil.isValid(firstWord, secondWord);

        // Then
        assertFalse(evaluationResult, "Failed to return NON anagaram for words with different sizes");
    }
    
}
