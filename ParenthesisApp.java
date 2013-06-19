package com.aol;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Question 1: Parenthesis Given a string comprising just of the characters
 * (,),{,},[,] determine if it is well-formed or not.
 * 
 * Your program should accept as its first argument a path to a filename. Each
 * line in this file contains a string comprising of the characters mentioned
 * above. e.g.
 * 
 * () <br>
 * ([)]
 * 
 * Print out True or False if the string is well-formed e.g.
 * 
 * True <br>
 * False
 */
public class ParenthesisApp {
  /** Stack that handles the tokens */
  private Stack<Character> tokensStack;
  /** Array with file's lines */
  private List<String> inputLines;

  /**
   * Constructor.
   * 
   * @param filename Input filename
   * @throws FileNotFoundException If the file does not exist
   * @throws IOException If an I/O error occurs
   */
  public ParenthesisApp(String filename) throws FileNotFoundException,
    IOException {
    tokensStack = new Stack<Character>();
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
   * Verifies if brackets pair are matching.
   * 
   * @param openToken Open bracket token
   * @param closeToken Close bracket token
   * @return {@code true} if are matching, {@code false} otherwise
   */
  private boolean isBracketsMatching(char openToken, char closeToken) {
    if ((openToken == '{' && closeToken == '}')
      || (openToken == '(' && closeToken == ')')
      || (openToken == '[' && closeToken == ']')) {
      return true;
    }
    return false;
  }

  /**
   * Verifies if an input text has all parenthesis matching.
   * 
   * @param text input text
   * @return {@code true} if all tokens are matching, {@code false} otherwise
   */
  private boolean isValid(String text) {
    tokensStack.clear();

    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);

      switch (c) {
        case '{':
        case '(':
        case '[':
          tokensStack.push(c);
          break;
        case '}':
        case ')':
        case ']':
          if (tokensStack.isEmpty()
            || !isBracketsMatching(tokensStack.pop(), c)) {
            return false;
          }
        default:
          continue;
      }
    }
    if (tokensStack.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * Runs the application.
   */
  public void run() {
    for (String inputLine : inputLines) {
      System.out.println(isValid(inputLine));
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
      System.err.println("Usage: java ParenthesisApp <filename>");
      System.exit(-1);
    }

    ParenthesisApp app = new ParenthesisApp(args[0]);
    app.run();
    System.exit(0);
  }
}
