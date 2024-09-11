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
        System.out.println("Please enter the number of dice to roll, how many sides the dice have,"+ 
            "\nand how many rolls to complete, separating the values by a space."+ 
            "\nExample: \"2 6 1000\"\n");
        getInput();
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
                if(2 < inputList.get(1) || inputList.get(1) > 100) {
                    System.out.println("Bad die creation: Illegal number of sides:" + 
                    inputList.get(1));
                } else {
                    if(inputList.size() < 3) {
                        System.out.println("Invalid input: Expected 3 values but recieved less" + 
                        "than 3");                    
                    } else if(inputList.size() > 3) {
                        System.out.println("Invalid input: Expected 3 values but recieved" +
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


    // static Die[] createDice(int numbers, int sides) {}

    // static int[] rollDice(Die[] dies) {}

    // static int findMax(int[] results) {}

    // static void report() {}
}