package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ghazouanibassem.utils.StringTransformerUtil;

public class StringTransformerTest {

    @Test
    @DisplayName("Should change all characters to lower case")
    void shouldReturnLowerCaseString() {
        // Given 
        String firstSample = "first TeSt";
        String secondSample = "test";
        String thirdSample = "LAST TEST";
        
        // When
        String firstOutput = StringTransformerUtil.toLowerCase(firstSample);
        String secondOutput = StringTransformerUtil.toLowerCase(secondSample);
        String thirdOutput = StringTransformerUtil.toLowerCase(thirdSample);
        
        // Then
        assert(firstOutput).equals("first test");
        assert(secondOutput).equals("test");
        assert(thirdOutput).equals("last test");
    }

    @Test
    @DisplayName("Should remove all duplicated or redundant spaces")
    void shouldReturnStringWithoutRedundantSpace() {
        // Given 
        String firstSample = " first   TeSt  ";
        String secondSample = "te     st";
        String thirdSample = "     ";
        
        // When
        String firstOutput = StringTransformerUtil.trimAllRedundantSpaces(firstSample);
        String secondOutput = StringTransformerUtil.trimAllRedundantSpaces(secondSample);
        String thirdOutput = StringTransformerUtil.trimAllRedundantSpaces(thirdSample);
        
        // Then
        assert(firstOutput).equals("first TeSt");
        assert(secondOutput).equals("te st");
        assert(thirdOutput).equals("");
    }
    
}
