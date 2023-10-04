package com.ghazouanibassem.model;

import com.ghazouanibassem.utils.StringTransformerUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataInputWraaper {
    private final String input;

    DataInputWraaper(String input) {
        this.input = input;
    }

    public String getNormalizedInput() {
        final String lowerCaseString = StringTransformerUtil.toLowerCase(input);
        final String normalizedString = StringTransformerUtil.trimAllRedundantSpaces(lowerCaseString);
        return normalizedString;
    }
}