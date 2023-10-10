package com.ghazouanibassem.models;

import com.ghazouanibassem.utils.StringTransformerUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataInputWrapperModel {
    private final String input;

    public DataInputWrapperModel(String input) {
        this.input = input;
    }

    public String getSanitizedInput() {
        final String lowerCaseString = StringTransformerUtil.toLowerCase(input);
        final String normalizedString = StringTransformerUtil.removeAllSpaces(lowerCaseString);
        return normalizedString;
    }
}