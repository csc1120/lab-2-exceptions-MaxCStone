/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Max Stone
 * Last Updated: 9/11/2024
 */
package stonemc;

/**
 * This class stores the sides in a die and can generate a random number to display the 
 * result of a roll
 */
public class Die {
    private int sides;
    private int currentValue;

    public Die(int sides) throws IllegalArgumentException{
        this.sides = sides;
        if(100 < sides || sides < 2) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method returns the most recently generated result or throws an error if the 
     * die has not been rolled yet
     * @return the currentValue
     */
    public int getCurrentValue() throws DieNotRolledException{
        if(currentValue == 0){
            throw new DieNotRolledException();
        }
        int value = currentValue;
        currentValue = 0;
        return value;
    }

    /**
     * This method generates a random value to set currentValue to
     */
    public void roll() {
        currentValue = (int)(Math.random() * (sides-1) + 1);
    }
}