# ANAGRAM CLI 📃
The following project allows to verifiy if two words are anagrams and get the group of anagrams related to a specific word.
By defintion two words (X, Y) are anagrams if Y could be formed with the exact same number of caracters of the Y. 
The project interaction is handled via CLI command, please take a look on the section CLI Command below to know the list of possible command to use.

## Project stack information 
The project is using java 17 with maven as project manager. There is different dependencies used within it to handle verification and validation of inputs form the used when interaction with the CLI.
The most important library in this project which you should take a look into it is:
- picocli (The library in charge of making java app runnable via terminal) -> [DOCS](https://picocli.info/) 👩‍💻🧑‍💻


## Setup ⏳
Please make sure you have the minimum requirement to be able to run the project. This mean you already installed JDK/java and maven since the project will be run via maven plugin life cycle.
- You can install Java/OpenJdk from here: [Download](https://jdk.java.net/17/)
- You can install Maven from the following link: [Download](https://maven.apache.org/download.cgi)

## How to run the project ⏲
This a maven project so you need to make sure to clean, validate, compile, test and package it so you would have **JAR** resource under **target** folder that will be run via ``java -cp``.\
To be more sure about if all correctly ready to be used, you will find a file with the following name `target/anagrams-1.0-SNAPSHOT.jar` in your system after running the basic maven command (ie: reach the package stage).

## CLI command available when running the project
First, let's undrestand that running the project is simple as:\
1. Open the terminal in the the same path of the root project
2. Run the command `java -cp target/anagrams-1.0-SNAPSHOT.jar com.ghazouanibassem.CLIAnagram`.

Now you must know that the behavior of the project differs based on the argument you give and it wait for **USER INPUT** excpet when asking for help (Please check below).

To start with a simple command, let's use the help 👀 argument:
- Run the command `java -cp target/anagrams-1.0-SNAPSHOT.jar com.ghazouanibassem.CLIAnagram -h` .

The output you will receive from the terminal would be the following and this is the helpful message that explain for you how to interact with the terminal
```
Welcome to Anagram CLI

The following CLI allow you to verfiy if two word are anagram or find out ALREADY tested related anagram word group.
- To run the check of anagram, You should run the command with -f1 option and specify two words as args.
- To find out ALREADY tested related anagram to a keyword, You should run the command with -f2 option and specify the word as an arg.      
```

💡 The CLI provide you with options and arguments. The available options in the project are -h, -f1 and -f2.
The available arguments are only **firstWord** and **secondWord**.
Please be careful there is a validation for the different invoke function based on the option you choose.
- For **-f1**, you are allowed to run the command with the two argument above (firstWord, secondWord) **on the first run**.
- For **-f2**, you are only allowed to run it with one word **on the first run**.

After you decide to run of these command, the CLI will be interactive and wait for the user to input his choice.
The interactive CLI will be accepting either **-f1** or **-f2** or **N**.
- ``-f1`` will prompt to you to enter the first word and then the second word.
- ``-f2`` will prompt tp you to enter the word you want to get his anagrams group.
- ``N`` will allow you to exit the CLI and then all the data will be removed (No more anagram history details).

```
would you like to continue using the tool or you would like to stop:
- If yes, please enter -f1/-f2.
- If not, please enter N.
N
```
