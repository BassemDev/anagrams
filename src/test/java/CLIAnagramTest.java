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
    @DisplayName("Should exit the anagram check with 1 as code")
    void shouldExitProgramSuccessfully() {
        // Given
        String[] cmdLineValues = {"-f1", "ram", "mar"};

        // When
        int exitCode = this.commandLine.execute(cmdLineValues);

        // Then
        assertEquals("Welcome to Anagram CLI - Spec for test", stringWriter.toString());
        assertEquals(1, exitCode);
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
        
        String expectedOutputString = "Welcome to Anagram CLI\n\n" +
                "You MUST specify either -f1 or -f2 as an option to be able to run the program correctly.\n" +
                "- Please select -f1 option and specify two words as args if you want to verify Anagrams.\n" +
                "- Please select -f2 option and specify the word you would like to see his group of Anagram.\n\n";
        int expectedCommandusageErrorCode = 0;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(expectedCommandusageErrorCode, exitCode);
    }

    @Test
    @DisplayName("Should display the help message when -h is passed as agrs")
    void shouldDisplayHelpMessageWhenHelpOptionIsSelected() {
        // Given
        String[] cmdLineValues = {"-h"};
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
        
        String expectedOutputString = "Welcome to Anagram CLI\n\n" +
        "The following CLI allow you to verfiy if two word are anagram or find out ALREADY tested related anagram word group.\n" +
        "- To run the check of anagram, You should run the command with -f1 option and specify two words as args.\n" +
        "- To find out ALREADY tested related anagram to a keyword, You should run the command with -f2 option and specify the word as an arg.\n\n";
        int expectedExitCode = 0;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(expectedExitCode, exitCode);
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
        
        String expectedOutputString = "Welcome to Anagram CLI\n\n" +
        "The following CLI allow you to verfiy if two word are anagram or find out ALREADY tested related anagram word group.\n" +
        "- To run the check of anagram, You should run the command with -f1 option and specify two words as args.\n" +
        "- To find out ALREADY tested related anagram to a keyword, You should run the command with -f2 option and specify the word as an arg.\n\n";
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
        int expectedExitCode = 1;

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
        int expectedExitCode = 1;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(expectedExitCode, exitCode);
    }

    @Test
    @DisplayName("Should fail when command line is called with two function invoke -f1 and -f2")
    void shouldDisplayFaillMessageWhenBothOptionArePresent() {
        // Given
        String[] cmdLineValues = {"-f1", "-f2"};
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
        
        String expectedOutputString = "Welcome to Anagram CLI\n\n"+
        "Operation Not PERMITTED, you can not specify both options -f1 -f2 when running the command.\n"+
        "- Please select -f1 option and specify two words as args if you want to verify Anagrams.\n" +
        "- Please select -f2 option and specify the word you would like to see his group of Anagram.\n\n";
        int terminatedErrorExitCode = 2;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(terminatedErrorExitCode, exitCode);
    }

    @Test
    @DisplayName("Should return empty result when choosing to display anagram from first command run")
    void shouldDisplayEmptyResultWhenLookForAnagramOnFirstRun() {
        // Given
        String word = "arm";
        String[] cmdLineValues = {"-f2", word};
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
        
        String expectedOutputString = "Welcome to Anagram CLI\n\n"+
        "There is no matching anagram groups for the word: arm\n" +
        "-------------------------------------------------\n";
        int terminatedErrorExitCode = 1;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(terminatedErrorExitCode, exitCode);
    }

    @Test
    @DisplayName("Should return error message when function 1 is called with one ONLY word")
    void shouldReturnErrorMessageWhenAnagramIsCalledWithOneWord() {
        // Given
        String word = "arm";
        String[] cmdLineValues = {"-f1", word};
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
        
        String expectedOutputString = "Welcome to Anagram CLI\n\n"+
        "Oups : The second word is either empty or contains invalid caracters !!!\n";
        int terminatedErrorExitCode = 1;

        // Then
        assertEquals(expectedOutputString, out.toString());
        assertEquals("", err.toString());

        assertEquals(terminatedErrorExitCode, exitCode);
    }
}
