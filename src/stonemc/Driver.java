/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Max Stone
 * Last Updated: 9/11/2024
 */
package stonemc;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class asks the user for a variety of parameters to construct die objects then uses those 
 * objects to determine the distribution of the results of those dice rolled a specified number
 * of times. It finishes by reporting the results of those rolls.
 */
public class Driver {
    public static void main(String[] args) {
        int[] userInputs;
        Die[] dieArray;
        int[] rollResults;
        int maximumValue;

        System.out.println("Please enter the number of dice to roll, how many sides the dice have,"+ 
            "\nand how many rolls to complete, separating the values by a space."+ 
            "\nExample: \"2 6 1000\"\n");
        
        userInputs = getInput();
        dieArray = createDice(userInputs[0],userInputs[1]);
        rollResults = rollDice(dieArray, userInputs[2]);
        maximumValue = findMax(rollResults);
        report(maximumValue,rollResults);
    }

    /**
     * This method prompts the user for 3 values which it then checks and returns as an array.
     * @return It returns the user inputs in an array
     */
    static int[] getInput() {
        ArrayList<Integer> inputList = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running) {
            inputList.clear();
            System.out.print("Enter configuration:");
            String entryString = in.nextLine();
            if(entryString.matches(".*[a-zA-Z].*")) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } else {
                Matcher matcher = Pattern.compile("\\d+").matcher(entryString);
                while(matcher.find()) {
                    inputList.add(Integer.valueOf(matcher.group()));
                }
                if(2 > inputList.get(1) || inputList.get(1) > 100) {
                    System.out.println("Bad die creation: Illegal number of sides: " + 
                    inputList.get(1));
                } else {
                    if(inputList.size() < 3) {
                        System.out.println("Invalid input: Expected 3 whole number values but recieved less" + 
                        "than 3");                    
                    } else if(inputList.size() > 3) {
                        System.out.println("Invalid input: Expected 3 whole number values but recieved " +
                        "greater than 3");
                    } else {
                        running = false;
                   }
                }
            }
            System.out.println("\n");
        }
        int[] inputArray = new int[3];
        int i = 0;
        for(int entry : inputList) {
            inputArray[i] = entry;
            i++;
        }
        in.close();
        return inputArray;
        }


    /**
     * This method creates an array of dice based on the user's parameters
     * @param number is the number of dice created
     * @param sides is the number of sides each die has
     * @return this returns the dieArray
     */
    static Die[] createDice(int number, int sides) {
        Die[] dieArray = new Die[number];
        for(int i = 0; i < number; i++) {
            dieArray[i] = new Die(sides);
        }
        return dieArray;
    }

    /**
     * This method rolls the dice the number of times that the user specified
     * @param dice is the array of dice to be rolled
     * @param numberOfRolls is the number of times each die is rolled
     * @return this method returns an array of results of the dice
     */
    static int[] rollDice(Die[] dice, int numberOfRolls) {
        int[] results = new int[numberOfRolls];
        for(int i = 0; i < results.length; i++) {
            for (int j = 0; j < dice.length; j++) {
                dice[j].roll();
                try {
                    results[i] += dice[j].getCurrentValue();
                } catch(DieNotRolledException e) {
                    System.out.println("Die Not Rolled exception");
                }
            }
        }
        return results;
    }

    /**
     * This formats then displays the frequencies of each rolled result
     * @param maximumValue is the maximum value rolled throughout the trial
     * @param rollResults is array of results from the trial
     */
    static void report(int maximumValue, int[] rollResults) {
        int[] frequencies = findRollFrequency(rollResults);
        String lengthMax = ""+ (int)maximumValue/10;
        String lengthStar = ""+ (int)findMax(frequencies)/10;
        int scale = findMax(frequencies)/10;
        for(int i = 0; i < frequencies.length; i++) {
            if(frequencies[i] > 0) {
                System.out.printf("%-" + (lengthMax.length() + 3) + "s", (i+1)+ ":");
                System.out.printf("%-" + (lengthStar.length() + 3) + "s",frequencies[i]);
                for(int j = 0; j < frequencies[i]/scale; j++) {
                    System.out.print("*");
                }
                System.out.println("");
            }
        }
    }

    /**
     * This method finds the frequency which a specific result occured
     * @param rollResults this is the array of rolls that resulted
     * @return the array of frequencies that rolls occured
     */
    static int[] findRollFrequency(int[] rollResults) {
        int[] frequencies = new int[findMax(rollResults)];
        for(int i : rollResults) {
            frequencies[i-1]++;
        }
        return frequencies;
    }
    
    /**
     * This method finds the maximum within the array of results
     * @param results is the array that is searched
     * @return the integer value of the maximum result
     */
    static int findMax(int[] results) {
        int maximum = 0;
        for(int i : results) {
            if(maximum < i) {
                maximum = i;
            }
        }
        return maximum;
    }
}