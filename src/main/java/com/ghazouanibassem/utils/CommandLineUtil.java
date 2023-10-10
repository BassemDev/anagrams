package com.ghazouanibassem.utils;

import java.util.Iterator;
import java.util.List;

import com.ghazouanibassem.models.AnagramDictionaryModel;
import com.ghazouanibassem.models.DataInputWrapperModel;

// Class dedicated to manage CLI without exact args
public class CommandLineUtil {
    
    public static int manageCliInteraction(AnagramDictionaryModel anagramDictionaryModel) {
        String value1 = "";
        String value2 = "";
        String commandKeyword = "";

        // Request main command to enter the interaction operations
        do {
            commandKeyword = System.console().readLine("would you like to continue using the tool or would you like to exit ?\n- If yes, please enter -f1/-f2.\n- If no, please enter N\n");
            // Check for null pointer exception
            if (commandKeyword.equals(null)) {
                return 0;
            }
        } while (!commandKeyword.equals("-f1") && !commandKeyword.equals("-f2") && commandKeyword.charAt(0) != 'N');

        // Continious operations and handle of user inputs
        do {
            switch(commandKeyword) {
                case "-f1": 
                    // Handle the first invoke function
                    // Get the two words
                    do {
                        value1 = System.console().readLine("Please enter the value of the first word: (The word MUST be correct -> size > 1 , only with letters and not bank)\n");
                    } while(StringValidatorUtil.isOneCharString(value1) || 
                        StringValidatorUtil.isStringBlankOrEmpty(value1) ||
                        !StringValidatorUtil.isStringFormedOnlyWithLetter(value1)
                    );
                    do {
                        value2 = System.console().readLine("Please enter the value of the Second word: (The word MUST be correct -> size > 1 , only with letters and not bank)\n");
                    } while(StringValidatorUtil.isOneCharString(value2) || 
                        StringValidatorUtil.isStringBlankOrEmpty(value2) ||
                        !StringValidatorUtil.isStringFormedOnlyWithLetter(value2)
                    );

                    // Sanitize Words to avoid performance degradation when operating on anagram checks
                    DataInputWrapperModel firstDataInput = new DataInputWrapperModel(value1);
                    DataInputWrapperModel secondDataInput = new DataInputWrapperModel(value2);

                    System.out.println("The validation of the two words : " + value1 + ' ' + value2 + ' ' + AnagramsCheckerUtil.isValid(firstDataInput.getSanitizedInput(), secondDataInput.getSanitizedInput()));

                    // Update the dictionary of anagrams
                    if (anagramDictionaryModel.checkIfAnagramExistInMap(value1)) {
                        anagramDictionaryModel.addWordsToAnagramWorldsGroupWithExactKey(value1, List.of(value1, value2));
                    } else {
                        anagramDictionaryModel.addNewAnagramGroupEntry(List.of(value1, value2));
                    }

                    // Request to continue the operations
                    do {
                        commandKeyword = System.console().readLine("would you like to continue using the tool or you would like to stop:\n- If yes, please enter -f1/-f2.\n- If no, please enter N.\n");
                    } while (!commandKeyword.equals("-f1") && !commandKeyword.equals("-f2") && commandKeyword.charAt(0) != 'N');

                    break;
                case "-f2": 
                    // Handle the second invoke function
                    do {
                        value1 = System.console().readLine("Please enter the word you want to get the related anagram group to (The word MUST be correct) : \n");
                    } while(StringValidatorUtil.isOneCharString(value1) || 
                        StringValidatorUtil.isStringBlankOrEmpty(value1) ||
                        !StringValidatorUtil.isStringFormedOnlyWithLetter(value1)
                    );

                    Iterator<String> groupOfAnagramIteratorX = anagramDictionaryModel.findRelatedAnagramGroup(value1).iterator();
                    if (!groupOfAnagramIteratorX.hasNext()) {
                        System.out.println("There is no matching anagram groups for the word: " + value1);
                        System.out.println("-------------------------------------------------");
                    } else {
                        while (groupOfAnagramIteratorX.hasNext()) {
                            System.out.print(groupOfAnagramIteratorX.next() + " | ");
                        }
                        System.out.println("\n-------------------------------------------------");
                    }

                    // Request to continue the operations
                    do {
                        commandKeyword = System.console().readLine("would you like to continue using the tool or you would like to stop:\n- If yes, please enter -f1/-f2.\n- If not, please enter N.\n");
                        // Check for null pointer exception
                        if (commandKeyword.equals(null)) {
                            return 0;
                        }
                    } while (!commandKeyword.equals("-f1") && !commandKeyword.equals("-f2") && commandKeyword.charAt(0) != 'N');
                    
                    break;
                case "N": 
                    // Handle the exit
                    break;
            }

        } while(commandKeyword.charAt(0) != 'N');

        return 0;
    }

    public static String getValidatedEntry(String word, String errorMessage, String hintMessage) {
        String value = word;
        if (StringValidatorUtil.isOneCharString(word) ||
            StringValidatorUtil.isStringBlankOrEmpty(word) ||
            !StringValidatorUtil.isStringFormedOnlyWithLetter(word)) {
            System.out.println(errorMessage);
            do {
                value = System.console().readLine(hintMessage);
            } while(StringValidatorUtil.isOneCharString(value) || 
                StringValidatorUtil.isStringBlankOrEmpty(value) ||
                !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
            );
        }

        return value;
    }

    public static String readMissingUserInput(String errorMessage, String hintMessage) {
        String value = "";
        System.out.println(errorMessage);
        do {
            value = System.console().readLine(hintMessage);
            // Check for null pointer exception
            if (value.equals(null)) {
                return "0";
            }
        } while(StringValidatorUtil.isOneCharString(value) || 
            StringValidatorUtil.isStringBlankOrEmpty(value) ||
            !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
        );

        return value;
    }
}
