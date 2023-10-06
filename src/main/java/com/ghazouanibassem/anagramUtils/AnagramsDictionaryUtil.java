package com.ghazouanibassem.anagramUtils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class AnagramsDictionaryUtil {

    public static boolean isValid(String firstWord, String secondWord) {
        // If both words are having different length 
        // Then  Both words could NOT be anagram
        if (firstWord.length() != secondWord.length()) {
            return false;
        }

        Multiset<Character> firstWordCharacterSet = HashMultiset.create();
        Multiset<Character> secondWordCharacterSet = HashMultiset.create();

        for (int i= 0; i < firstWord.length(); i++) {
            firstWordCharacterSet.add(firstWord.charAt(i));
            secondWordCharacterSet.add(secondWord.charAt(i));
        }

        return firstWordCharacterSet.equals(secondWordCharacterSet);
    }
}
