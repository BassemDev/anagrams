package com.ghazouanibassem.model;

import com.ghazouanibassem.utils.StringTransformerUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataInputWrapper {
    private final String input;

    public DataInputWrapper(String input) {
        this.input = input;
    }

    public String getSanitizedInput() {
        final String lowerCaseString = StringTransformerUtil.toLowerCase(input);
        final String normalizedString = StringTransformerUtil.removeAllSpaces(lowerCaseString);
        return normalizedString;
    }
}