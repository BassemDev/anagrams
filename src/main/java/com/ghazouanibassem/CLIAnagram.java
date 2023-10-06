package com.ghazouanibassem;
import java.util.concurrent.Callable;

import com.ghazouanibassem.anagramUtils.AnagramsDictionaryUtil;
import com.ghazouanibassem.model.DataInputWrapper;
import com.ghazouanibassem.utils.StringValidatorUtil;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "CLI Anagram", mixinStandardHelpOptions = true, description = "Check If two words are anagram or not !")
public class CLIAnagram implements Callable<Integer> {

    @Parameters(index = "0", description = "First word to check", defaultValue = "")
    private String firstWord;

    @Parameters(index = "1", description = "Second word to check", defaultValue = "")
    private String secondWord;

    @Option(names = {"-f1"}, description = "Option related to run the anagram validation", required = true)
    private boolean invokeFunction;

    @Option(names = {"-h", "version"}, description = "Display helper message for the user")
    private boolean help;

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Welcome to Anagram CLI \n");
        // Check if the user request help
        if (this.help) {
            System.out.println("The following CLI allow you to verfiy if two word are anagram.\n You must run the program with -f1 option and specify two words as args.\n");
        }

        if (this.invokeFunction) {
            if (this.firstWord == "" && this.secondWord == "") {
                System.out.println("Please provide both word to measure the anagram: \n");
                String value;
                do {
                    value = System.console().readLine("Please enter the value of the FIRST word: (The word MUST be correct -> size > 1 , only with letters and not bank )\n");
                } while(StringValidatorUtil.isOneCharString(value) || 
                    StringValidatorUtil.isStringBlankOrEmpty(value) ||
                    !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
                );
                
                setFirstWord(value);

                do {
                    value = System.console().readLine("Please enter the value of the SECOND word: (The word MUST be correct -> size > 1 , only with letters and not bank )\n");
                } while(StringValidatorUtil.isOneCharString(value) || 
                    StringValidatorUtil.isStringBlankOrEmpty(value) ||
                    !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
                );

                setSecondWord(value);

            } else if (this.firstWord == "" && this.secondWord != "") {
                String value;
                do {
                    value = System.console().readLine("Please enter the value of the FIRST word: (The word MUST be correct -> size > 1 , only with letters and not bank )\n");
                } while(StringValidatorUtil.isOneCharString(value) || 
                    StringValidatorUtil.isStringBlankOrEmpty(value) ||
                    !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
                );
                
                setFirstWord(value);

            } else if (this.firstWord != "" && this.secondWord == "") {
                String value;
                do {
                    value = System.console().readLine("Please enter the value of the Second word: (The word MUST be correct -> size > 1 , only with letters and not bank )\n");
                } while(StringValidatorUtil.isOneCharString(value) || 
                    StringValidatorUtil.isStringBlankOrEmpty(value) ||
                    !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
                );

                setSecondWord(value);
            } else {

                if (StringValidatorUtil.isOneCharString(firstWord) || 
                    StringValidatorUtil.isStringBlankOrEmpty(firstWord) ||
                    !StringValidatorUtil.isStringFormedOnlyWithLetter(firstWord)) {
                    System.out.println("Oups : The first word is either empty or contains invalid caracters !!!");
                    String value;
                    do {
                        value = System.console().readLine("Please enter the value of the first word: (The word MUST be correct -> size > 1 , only with letters and not bank )\n");
                    } while(StringValidatorUtil.isOneCharString(value) || 
                        StringValidatorUtil.isStringBlankOrEmpty(value) ||
                        !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
                    );
                    setFirstWord(value);
                }

                if (StringValidatorUtil.isOneCharString(secondWord) ||
                    StringValidatorUtil.isStringBlankOrEmpty(secondWord) ||
                    !StringValidatorUtil.isStringFormedOnlyWithLetter(secondWord)) {
                    System.out.println("Oups : The second word is either empty or contains invalid caracters !!!");
                    String value;
                    do {
                        value = System.console().readLine("Please enter the value of the Second word: (The word MUST be correct -> size > 1 , only with letters and not bank )\n");
                    } while(StringValidatorUtil.isOneCharString(value) || 
                        StringValidatorUtil.isStringBlankOrEmpty(value) ||
                        !StringValidatorUtil.isStringFormedOnlyWithLetter(value)
                    );
                    setSecondWord(value);
                }
            } 

            // Sanitize Words to avoid performance degradation when operating on anagram checks
            DataInputWrapper firstDataInput = new DataInputWrapper(firstWord);
            DataInputWrapper secondDataInput = new DataInputWrapper(secondWord);

            firstWord = firstDataInput.getSanitizedInput();
            secondWord = secondDataInput.getSanitizedInput();

            System.out.println("The validation of the two words : " + firstWord + ' ' + secondWord + ' ' + AnagramsDictionaryUtil.isValid(firstWord, secondWord));
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CLIAnagram()).execute(args);

        System.out.println("\nBye Bye from Anagram CLI");
        System.exit(exitCode);
    }
}