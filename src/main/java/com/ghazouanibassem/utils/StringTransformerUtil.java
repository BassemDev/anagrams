package com.ghazouanibassem.utils;

import org.apache.commons.lang3.StringUtils;

public class StringTransformerUtil {
    public static String toLowerCase(String word) {
        return word.toLowerCase();
    }

    // This method will remove all redundant spaces
    public static String trimAllRedundantSpaces(String word) {
        return StringUtils.normalizeSpace(word);
    }
}
