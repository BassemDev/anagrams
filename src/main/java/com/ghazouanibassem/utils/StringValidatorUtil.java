package com.ghazouanibassem.utils;

import org.apache.commons.lang3.StringUtils;

public class StringValidatorUtil {
    public static boolean isStringFormedOnlyWithLetter(String word) {
        // To validate a correct formed word we will check the following condition:
        // 1- Only formed from letter (uppercase or lowerCase)
        // 2- Does not contain any numers
        // 3- Does not contain any special characters
        final String PATTERN = "^([^0-9!#?^,.:\\-_+&%$§*/]*)$";
        return word.matches(PATTERN);
    }

    public static boolean isOneCharString(String word) {
        // A word should be composed of at least two characters
        return word.length() == 1;
    }

    // Check is the word is Blank or Empty (in case if the word is only formed with empty spaces)
    public static boolean isStringBlankOrEmpty(String word) {
        return StringUtils.isEmpty(word) || StringUtils.isBlank(word);
    }
}
