package com.ghazouanibassem;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import com.ghazouanibassem.models.AnagramDictionaryModel;
import com.ghazouanibassem.models.DataInputWrapperModel;
import com.ghazouanibassem.utils.AnagramsCheckerUtil;
import com.ghazouanibassem.utils.CommandLineUtil;
import com.ghazouanibassem.utils.StringValidatorUtil;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name = "CLI Anagram", mixinStandardHelpOptions = true, description = "Check If two words are anagram or not !")
public class CLIAnagram implements Callable<Integer> {
    // used for the test scope
    @Spec 
    CommandSpec spec;

    @Parameters(index = "0", description = "First word to check", defaultValue = "")
    private String firstWord;

    @Parameters(index = "1", description = "Second word to check", defaultValue = "")
    private String secondWord;

    @Option(names = {"-f1"}, description = "Option related to run the anagram validation")
    private boolean invokeF1Function;

    @Option(names = {"-f2"}, description = "Option related to run the anagram validation")
    private boolean invokeF2Function;

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
        // Only used for the test
        this.spec.commandLine().getOut().print("Welcome to Anagram CLI - Spec for test");

        // Set up dictionary of anagram word
        AnagramDictionaryModel anagramDictionaryModel = new AnagramDictionaryModel();

        System.out.println("Welcome to Anagram CLI\n");
        // Check if the user request help
        if (this.help) {
            System.out.println("The following CLI allow you to verfiy if two word are anagram or find out ALREADY tested related anagram word group.\n" +
                "- To run the check of anagram, You should run the command with -f1 option and specify two words as args.\n" +
                "- To find out ALREADY tested related anagram to a keyword, You should run the command with -f2 option and specify the word as an arg.\n"
            );
            return 0;
        }

        // Check if one of the function option was specified (Early exit)
        if (!this.invokeF1Function && !this.invokeF2Function) {
            System.out.println("You MUST specify either -f1 or -f2 as an option to be able to run the program correctly.\n- Please select -f1 option and specify two words as args if you want to verify Anagrams.\n- Please select -f2 option and specify the word you would like to see his group of Anagram.\n");
            return 0;
        }

        // Check if one of the function option was specified (Early exit)
        if (this.invokeF1Function && this.invokeF2Function) {
            System.out.println("Operation Not PERMITTED, you can not specify both options -f1 -f2 when running the command.\n- Please select -f1 option and specify two words as args if you want to verify Anagrams.\n- Please select -f2 option and specify the word you would like to see his group of Anagram.\n");
            return 2;
        }

        // Handle all operations related to F1
        if (this.invokeF1Function) {
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
            DataInputWrapperModel firstDataInput = new DataInputWrapperModel(firstWord);
            DataInputWrapperModel secondDataInput = new DataInputWrapperModel(secondWord);

            System.out.println("The validation of the two words : " + firstWord + ' ' + secondWord + ' ' + AnagramsCheckerUtil.isValid(firstDataInput.getSanitizedInput(), secondDataInput.getSanitizedInput()));
            anagramDictionaryModel.addNewAnagramGroupEntry(List.of(firstWord, secondWord));
            CommandLineUtil.manageCliInteraction(anagramDictionaryModel);

        } else {
            // Handle all operations related to F2
            if (!StringUtils.isBlank(this.firstWord) && !StringUtils.isBlank(this.secondWord)) {
                System.out.println("Oups : The function f2 expect only one word not two !!!");
                return 2;
            }

            String value = "";
            if (this.firstWord == "") {           
                value = CommandLineUtil.readMissingUserInput(
                    "Oups : The word given is either empty or contains invalid caracters !!!",
                    "Please enter the value of the word you want to get its anagram groups: (The word MUST be correct -> size > 1 , only with letters and not bank )\n"
                );
            } else {
                    value = CommandLineUtil.getValidatedEntry(
                        firstWord,
                        "Oups : The word given is either empty or contains invalid caracters !!!",
                        "Please enter the value of the word you want to get its anagram groups: (The word MUST be correct -> size > 1 , only with letters and not bank )\n"
                    );
            }

            DataInputWrapperModel dataInput = new DataInputWrapperModel(value);
            Iterator<String> groupOfAnagramIteratorX = anagramDictionaryModel.findRelatedAnagramGroup(dataInput.getSanitizedInput()).iterator();
            if (!groupOfAnagramIteratorX.hasNext()) {
                System.out.println("There is no matching anagram groups for the word: " + dataInput.getSanitizedInput());
                System.out.println("-------------------------------------------------");
            } else {
                while (groupOfAnagramIteratorX.hasNext()) {
                    System.out.print(groupOfAnagramIteratorX.next() + " | ");
                }
                System.out.println("-------------------------------------------------");
            }

            CommandLineUtil.manageCliInteraction(anagramDictionaryModel);
        }

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CLIAnagram()).execute(args);

        System.out.println("\nBye Bye from Anagram CLI");
        System.exit(exitCode);
    }
}