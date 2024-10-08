/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Max Stone
 * Last Updated: 9/11/2024
 */
package stonemc;

/**
 * This class extends RuntimeException to throw an exception when called.
 */
public class DieNotRolledException extends Exception {
    public DieNotRolledException() {
        super("The dice's value was retrieved before it was rolled.");
    }
}
