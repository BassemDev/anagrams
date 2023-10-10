package mocks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AnagramMock {
    public static HashMap<String, HashSet<String>> generateMockedAnagramMap(String word, HashSet<String> wordGroups) {
        HashMap<String, HashSet<String>> groups = new HashMap<>();
        groups.put(word, wordGroups);

        return groups;
    }

    public static HashMap<String, HashSet<String>> generateRandomMockedAnagramMap() {
        String firstWord = "arm";
        String secondWord = "listen";
        String thirdWord = "roma";
        String fourthWord = "animal";
        HashMap<String, HashSet<String>> groups = new HashMap<>();
        // Creating the HashMap that manage the anagrams related group with their key
        HashSet<String> firstAnagramSet = new HashSet<String>(Arrays.asList("ram", "arm", "mar"));
        HashSet<String> secondAnagramSet = new HashSet<String>(Arrays.asList("elints", "enlist", "inlets", "listen", "silent", "tinsel"));
        HashSet<String> thirdAnagramSet = new HashSet<String>(Arrays.asList("roma", "roam"));
        HashSet<String> fourthAnagramSet = new HashSet<String>(Arrays.asList("animal", "lamina"));
        groups.put(firstWord, firstAnagramSet);
        groups.put(secondWord, secondAnagramSet);
        groups.put(thirdWord, thirdAnagramSet);
        groups.put(fourthWord, fourthAnagramSet);

        return groups;
    }
}
