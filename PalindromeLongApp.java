package com.aol;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Question 2: Reverse and Add
 * 
 * The problem is as follows: choose a number, reverse its digits and add it to
 * the original. If the sum is not a palindrome (which means, it is not the same
 * number from left to right and right to left), repeat this procedure. eg.
 * 
 * 195 (initial number) + 591 (reverse of initial number) = 786
 * 
 * 786 + 687 = 1473
 * 
 * 1473 + 3741 = 5214
 * 
 * 5214 + 4125 = 9339 (palindrome) In this particular case the palindrome 9339
 * appeared after the 4th addition. This method leads to palindromes in a few
 * step for almost all of the integers. But there are interesting exceptions.
 * 196 is the first number for which no palindrome has been found. It is not
 * proven though, that there is no such a palindrome.
 * 
 * Your program should accept as its first argument a path to a filename. Each
 * line in this file is one test case. Each test case will contain an integer n
 * < 4,294,967,295. Assume each test case will always have an answer and that it
 * is computable with less than 1000 iterations (additions).
 * 
 * For each line of input, generate a line of output which is the number of
 * iterations (additions) to compute the palindrome and the resulting
 * palindrome. (they should be on one line and separated by a single space
 * character)
 * 
 * 
 * Test samples: http://www.jasondoucette.com/pal/
 */
public class PalindromeLongApp {
  /** Array with file's lines */
  private List<String> inputLines;
  /** Number of maximum iterations (additions) */
  private final int nIterations;

  /**
   * Constructor.
   * 
   * @param filename Input filename
   * @throws FileNotFoundException If the file does not exist
   * @throws IOException If an I/O error occurs
   */
  public PalindromeLongApp(String filename) throws FileNotFoundException,
    IOException {
    nIterations = 1000;
    readFile(filename);
  }

  /**
   * Reads a file and creates a list of inputs.
   * 
   * @param filename Input's filename
   * @throws FileNotFoundException If the file does not exist
   * @throws IOException If an I/O error occurs
   */
  private void readFile(String filename) throws FileNotFoundException,
    IOException {
    inputLines = new ArrayList<String>();

    DataInputStream input = new DataInputStream(new FileInputStream(filename));
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    String line;

    while ((line = reader.readLine()) != null) {
      inputLines.add(line.trim());
    }
    input.close();
  }

  /**
   * Checks if a number is a Palindrome.
   * 
   * @param number Input number
   * @return {@code true} if is a Palindrome, {@code false} otherwise
   */
  private boolean isPalindrome(long number) {
    if (number == getReversedNumber(number)) {
      return true;
    }
    return false;
  }

  /**
   * Reverses the number's digits.
   * 
   * @param number Input number
   * @return The input number with reversed digits
   */
  private long getReversedNumber(long number) {
    long reverse = 0;
    long remainder = 0;

    do {
      remainder = number % 10;
      reverse = reverse * 10 + remainder;
      number = number / 10;

    } while (number > 0);

    return reverse;
  }

  /**
   * The problem is as follows: choose a number, reverse its digits and add it
   * to the original. If the sum is not a palindrome, repeat this procedure. The
   * method prints the first found palindrome.
   * 
   * @param number Input number
   */
  private void printPalindrome(Long number) {
    Long num1 = number;
    Long num2 = getReversedNumber(number);

    for (int i = 1; i <= nIterations; i++) {
      long total = num1 + num2;

      if (isPalindrome(total)) {
        System.out.println(i + " " + total);
        return;
      }
      num1 = total;
      num2 = getReversedNumber(total);
    }
    System.out.println("Does not exists a Palindrome in " + nIterations
      + " iterations.");
  }

  /**
   * Runs the application.
   */
  public void run() {
    for (String inputLine : inputLines) {
      printPalindrome(new Long(inputLine));
    }
  }

  /**
   * Simple demo application.
   * 
   * @param args The first argument is the filename
   * @throws FileNotFoundException If the file does not exist
   * @throws IOException If an I/O error occurs
   */
  public static void main(String[] args) throws FileNotFoundException,
    IOException {

    if (args.length == 0) {
      System.err.println("Usage: java PalindromeApp <filename>");
      System.exit(-1);
    }

    PalindromeLongApp app = new PalindromeLongApp(args[0]);
    app.run();
    System.exit(0);
  }
}
