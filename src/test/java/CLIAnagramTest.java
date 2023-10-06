import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.ghazouanibassem.CLIAnagram;

import picocli.CommandLine;

public class CLIAnagramTest {
    private CLIAnagram cliAnagram;
    private CommandLine commandLine;
    private StringWriter stringWriter;

    @BeforeEach
    void setup() {
        cliAnagram = new CLIAnagram();
        commandLine = new CommandLine(cliAnagram);
        stringWriter = new StringWriter();
        commandLine.setOut(new PrintWriter(stringWriter));
        System.out.println(stringWriter.toString());
    }

    @Test
    @DisplayName("Should exit the anagram check with 0 as code")
    void shouldExitProgramSuccessfully() {
        // Given
        String[] cmdLineValues = {"-f1", "ram", "mar"};

        // When
        int exitCode = this.commandLine.execute(cmdLineValues);

        // Then
        assertEquals("Welcome to Anagram CLI - Spec for test", stringWriter.toString());
        assertEquals(0, exitCode);
    }

    @Test
    @DisplayName("Should display the error message when args are missing")
    void shouldDisplayErrorMessageWhenArgsAreMissing() {
        // Given
        String[] cmdLineValues = {};
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldErr = System.err;
        PrintStream oldOut = System.out;
        int exitCode;

        // When
        try {
            System.setErr(new PrintStream(err));
            System.setOut(new PrintStream(out));
            exitCode = this.commandLine.execute(cmdLineValues);
        } finally {
            System.setErr(oldErr);                 
            System.setOut(oldOut);
        }
        
        String expectedErrorString = "Missing required option: '-f1'\n" +
        "Usage: CLI Anagram [-h] -f1 <firstWord> <secondWord>\n" +
        "Check If two words are anagram or not !\n" +
        "      <firstWord>    First word to check\n" + 
        "      <secondWord>   Second word to check\n" +
        "      -f1            Option related to run the anagram validation\n" +
        "  -h, version        Display helper message for the user\n";
        int expectedInvalidCommandusageErrorCode = 2;

        // Then
        assertEquals("", out.toString());
        assertEquals(expectedErrorString, err.toString());

        assertEquals(expectedInvalidCommandusageErrorCode, exitCode);
    }

    @Test
    @DisplayName("Should display the help message when args contains -h")
    void shouldDisplayHelpMessageWhenHelpOptionIsAdded() {
        // Given
        String[] cmdLineValues = {"-f1", "-h"};
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldErr = System.err;
        PrintStream oldOut = System.out;
        int exitCode;

        // When
        try {
            System.setErr(new PrintStream(err));
            System.setOut(new PrintStream(out));
            exitCode = this.commandLine.execute(cmdLineValues);
        } finally {
            System.setErr(oldErr);                 
            System.setOut(oldOut);
        }
        
        String expectedOutputString = "Welcome to Anagram CLI\n" + 
                "\nThe following CLI allow you to verfiy if two word are anagram.\n" +
                "You must run the program with -f1 option and specify two words as args.\n" +
                "\n";
        int expectedExitCode = 0;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(expectedExitCode, exitCode);
    }

    @Test
    @DisplayName("Should display the successful message for the anagrams")
    void shouldDisplaySuccessfulMessageForAnagrams() {
        // Given
        String firstWord = "listeN";
        String secondWord = "siLent";
        String[] cmdLineValues = {"-f1", firstWord, secondWord};
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldErr = System.err;
        PrintStream oldOut = System.out;
        int exitCode;

        // When
        try {
            System.setErr(new PrintStream(err));
            System.setOut(new PrintStream(out));
            exitCode = this.commandLine.execute(cmdLineValues);
        } finally {
            System.setErr(oldErr);                 
            System.setOut(oldOut);
        }
        
        String expectedOutputString = "Welcome to Anagram CLI\n" + 
        "\nThe validation of the two words : " +  firstWord + " " + secondWord + " " + "true\n";
        int expectedExitCode = 0;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(expectedExitCode, exitCode);
    }

    @Test
    @DisplayName("Should display the fail message for the NON anagrams")
    void shouldDisplayFaillMessageForAnagrams() {
        // Given
        String firstWord = "listeN";
        String secondWord = "test";
        String[] cmdLineValues = {"-f1", firstWord, secondWord};
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oldErr = System.err;
        PrintStream oldOut = System.out;
        int exitCode;

        // When
        try {
            System.setErr(new PrintStream(err));
            System.setOut(new PrintStream(out));
            exitCode = this.commandLine.execute(cmdLineValues);
        } finally {
            System.setErr(oldErr);                 
            System.setOut(oldOut);
        }
        
        String expectedOutputString = "Welcome to Anagram CLI\n" + 
        "\nThe validation of the two words : " +  firstWord + " " + secondWord + " " + "false\n";
        int expectedExitCode = 0;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(expectedExitCode, exitCode);
    }
}
