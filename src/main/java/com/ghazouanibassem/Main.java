package com.ghazouanibassem;

import java.io.DataInput;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.A;

import com.ghazouanibassem.anagramUtils.AnagramsDictionaryUtil;
import com.ghazouanibassem.model.DataInputWrapper;
import com.ghazouanibassem.utils.StringTransformerUtil;
import com.ghazouanibassem.utils.StringValidatorUtil;

public class Main {
    public static void main(String[] args) {
        System.out.println("Anagram validator!");
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            String firstWord;
            String secondWord;

            do {
                System.out.println("Please introduce the first word (The word should be correct) !");
                firstWord = scanner.nextLine();
            } while (
                StringValidatorUtil.isOneCharString(firstWord) || 
                StringValidatorUtil.isStringBlankOrEmpty(firstWord) ||
                !StringValidatorUtil.isStringFormedOnlyWithLetter(firstWord)
            );

            do {
                System.out.println("Please introduce the second word (The word should be correct)!");
                secondWord = scanner.nextLine();
            } while (
                StringValidatorUtil.isOneCharString(secondWord) ||
                StringValidatorUtil.isStringBlankOrEmpty(secondWord) ||
                !StringValidatorUtil.isStringFormedOnlyWithLetter(secondWord)
            );
            
            // Sanitize Words to avoid performance degradation when operating on anagram checks
            DataInputWrapper firstDataInput = new DataInputWrapper(firstWord);
            DataInputWrapper secondDataInput = new DataInputWrapper(firstWord);

            firstWord = firstDataInput.getSanitizedInput();
            secondWord = secondDataInput.getSanitizedInput();

            System.out.println("The validation of the two words : " + AnagramsDictionaryUtil.isValid(firstWord, secondWord));
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    
    }
}