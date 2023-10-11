package models;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ghazouanibassem.models.AnagramDictionaryModel;

import mocks.AnagramMock;

public class AnagramDictionaryModelTest {
    private AnagramDictionaryModel anagramDictionaryModel;
    
    @BeforeEach
    void setup() {
        anagramDictionaryModel = new AnagramDictionaryModel();
    }

    @Test
    @DisplayName("Should find the potenitel match anagram group for a given keyword")
    void shouldFindAnagramGroup() {
        // Given
        String firstWord = "ram";
        HashMap<String , HashSet<String>> groups = AnagramMock.generateMockedAnagramMap("ram", new HashSet<String>(Arrays.asList("ram", "arm", "mar")));
        anagramDictionaryModel.setGroups(groups);

        // When
        Set<String> matchingAnagramSet = anagramDictionaryModel.findRelatedAnagramGroup(firstWord);
        Set<String> expectedSet = Set.of("ram", "arm", "mar");

        // Then
        assertEquals(expectedSet, matchingAnagramSet);
    }

    @Test
    @DisplayName("Should return null when no anagaram list found")
    void shouldReturnNullWhenNoMatchingAnagramGroupFound() {
        // Given
        String firstWord = "test";
        HashMap<String , HashSet<String>> groups = AnagramMock.generateRandomMockedAnagramMap();
        anagramDictionaryModel.setGroups(groups);

        // When
        String matchingAnagramMapKey = anagramDictionaryModel.findAnagramMatchingKey(firstWord);
       
        // Then
        assertEquals(null, matchingAnagramMapKey);
    }

    @Test
    @DisplayName("Should add new anagram group to the anagram dictionary")
    void shouldAddNewAnagram() {
        // Given
        // Before update of the HashMap
        String newWord = "German";
        HashMap<String , HashSet<String>> groups = AnagramMock.generateRandomMockedAnagramMap();
        anagramDictionaryModel.setGroups(groups);

        // When
        // Before update of the HashMap
        String matchingAnagramMapKey = anagramDictionaryModel.findAnagramMatchingKey(newWord);
       
        // Then
        // Before update of the HashMap
        assertEquals(null, matchingAnagramMapKey);

        // Given
        // After update of the HashMap
        HashSet<String> newWordsGroup = new HashSet<String>(Arrays.asList("engram", "German", "manager", "ragmen"));
        anagramDictionaryModel.updateAnagramDictionary(newWord, newWordsGroup);

        // When
        // After update of the HashMap
        String matchingNewAnagramMapKey = anagramDictionaryModel.findAnagramMatchingKey(newWord);
       
        // Then
        // Affore update of the HashMap
        assertEquals("German", matchingNewAnagramMapKey);
    }

    @Test
    @DisplayName("Should add new word to the righ anagram group")
    void shouldAddWordToTheBelongingAnagramGroup() {
        // Given
        // Before update of the HashMap
        String newWordLookup = "animal";
        HashMap<String , HashSet<String>> groups = AnagramMock.generateRandomMockedAnagramMap();
        anagramDictionaryModel.setGroups(groups);

        // When
        anagramDictionaryModel.addWordsToAnagramWorldsGroupWithExactKey(newWordLookup, List.of("lamina", "manila"));
        Set<String> relatedWordsGroup = anagramDictionaryModel.findRelatedAnagramGroup(newWordLookup);
        Set<String> expectedRelatedWordsGroup = Set.of("animal", "lamina", "manila");
       
        // Then
        // Afore update of the HashMap
        assertEquals(expectedRelatedWordsGroup, relatedWordsGroup);
    }

    @Test
    @DisplayName("Should add new words to the righ anagram group")
    void shouldAddWordsToTheBelongingAnagramGroup() {
        // Given
        // Before update of the HashMap
        String newWordLookup = "manila";
        HashMap<String , HashSet<String>> groups = AnagramMock.generateRandomMockedAnagramMap();
        anagramDictionaryModel.setGroups(groups);

        // When
        anagramDictionaryModel.addWordsToAnagramHashMAp(newWordLookup, List.of("manila", "lamina"));
        Set<String> relatedWordsGroup = anagramDictionaryModel.findRelatedAnagramGroup(newWordLookup);
        Set<String> expectedRelatedWordsGroup = Set.of("animal", "lamina", "manila");
       
        // Then
        // Aefore update of the HashMap
        assertEquals(expectedRelatedWordsGroup, relatedWordsGroup);
    }

    @Test
    @DisplayName("Should add new words to the righ anagram group without overiding old values")
    void shouldAddWordsToTheAnagramGroupWithoutremovingOthers() {
        // Given
        // Before update of the HashMap
        String newWordLookup = "manila";
        HashMap<String , HashSet<String>> groups = AnagramMock.generateRandomMockedAnagramMap();
        anagramDictionaryModel.setGroups(groups);

        // When
        anagramDictionaryModel.addWordsToAnagramHashMAp(newWordLookup, List.of("manila", "lamina"));
        anagramDictionaryModel.addWordsToAnagramHashMAp(newWordLookup, List.of("manila", "animal"));
        Set<String> relatedWordsGroup = anagramDictionaryModel.findRelatedAnagramGroup(newWordLookup);
        Set<String> expectedRelatedWordsGroup = Set.of("animal", "lamina", "manila");
       
        // Then
        // Afore update of the HashMap
        assertEquals(expectedRelatedWordsGroup, relatedWordsGroup);
    }

    @Test
    @DisplayName("Should create new hash map key WHEN anagram list is still NOT added")
    void shouldCreateNewMapKey() {
        // Given
        // Before update of the HashMap
        String newWordLookup = "alert";
        HashMap<String , HashSet<String>> groups = AnagramMock.generateRandomMockedAnagramMap();
        anagramDictionaryModel.setGroups(groups);
        // Check that no matching key is available
        assertNull(anagramDictionaryModel.findAnagramMatchingKey(newWordLookup), "Failed to verify null value of unexisting map key");

        // When
        anagramDictionaryModel.addNewAnagramGroupEntry(List.of("alert", "later"));
       
        // Then
        // Afore update of the HashMap
        assertNotNull(anagramDictionaryModel.findAnagramMatchingKey(newWordLookup), "Failed to find key of an existing anagrams group");
        assertEquals(anagramDictionaryModel.findRelatedAnagramGroup(newWordLookup), Set.of("alert", "later"));
    }
}
