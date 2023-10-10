package com.ghazouanibassem.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.List;

import com.ghazouanibassem.utils.AnagramsCheckerUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnagramDictionaryModel {
    private HashMap<String, HashSet<String>> groups;

    public AnagramDictionaryModel() {
        this.groups = new HashMap<>();
    }

    public void updateAnagramDictionary(String word, HashSet<String> wordsGroup) {
        this.groups.put(word, wordsGroup);
    }

    // Add new word to the exact anagram words group using the associated key
    public void addWordToAnagramWorldsGroup(String key, String newWord) {
        HashSet<String> anagramRelatedWords = this.groups.get(key);

        // Update the group of related anagram
        anagramRelatedWords.add(newWord);

        // Update the linked key to contain the whole new group 
        this.groups.put(key, anagramRelatedWords);
    }

    // Add List word to the exact anagram words group using the associated key
    public void addWordsToAnagramWorldsGroupWithExactKey(String key, List<String> newWords) {
        HashSet<String> anagramRelatedWords = this.groups.get(key);

        // Update the group of related anagram with the new list added
        anagramRelatedWords.addAll(newWords);

        // Update the linked key to contain the whole new group 
        this.groups.put(key, anagramRelatedWords);
    }

    // Add new word to the an existing anagram map if the keys IS an anagram 
    // Of the word the user entred as look up word
    public void addWordsToAnagramHashMAp(String newWord,  List<String> newWords) {
        String matchingAnagaramKey = findAnagramMatchingKey(newWord);
        
        if (matchingAnagaramKey != null) {
            HashSet<String> anagramRelatedWords = this.groups.get(matchingAnagaramKey);

            // Update the group of related anagram with list of word
            anagramRelatedWords.addAll(newWords);

            // Update the linked key to contain the whole new group 
            this.groups.put(matchingAnagaramKey, anagramRelatedWords);
        }
    }

    // Check if one of the key directly matches the words when invoking the second function
    public boolean checkIfAnagramExistInMap(String newWord) {
        return this.groups.get(newWord) != null ? true : false;
    }

    // Check if one of the key is an anagram of the words when invoking the second function
    public String findAnagramMatchingKey(String newWord) {
        Optional<String> matchingAnagaramKey = this.groups.keySet().stream().filter(key -> AnagramsCheckerUtil.isValid(key, newWord)).findFirst();
        return matchingAnagaramKey.isPresent() ? matchingAnagaramKey.get() : null;
    }

    // Find all related anagrams to a GIVEN word
    public HashSet<String> findRelatedAnagramGroup(String newWord) {
        String matchingAnagaramKey = findAnagramMatchingKey(newWord);
        
        if (matchingAnagaramKey == null) {
            return new HashSet<String>();
        }

        return this.groups.get(matchingAnagaramKey);
    }

    // Update the Map with new entry Key -> Groupe of Anagram
    public void addNewAnagramGroupEntry(List<String> anagrams) {
        final String newMapKey = anagrams.get(0);
        this.groups.put(newMapKey, new HashSet<String>(anagrams));
    }
}
