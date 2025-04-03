package tokenProcessor;
import java.io.*;
import java.util.*;

public class TokenProcessor {
	
	private static void tokenizeHelper(String input) {
        int expressionCounter = 0;
        while (expressionCounter < input.length()) {
            char c = input.charAt(expressionCounter);
            
            // check for whitespace.
            if (Character.isWhitespace(c)) {
            	expressionCounter++;
                continue;
            }
            
            // If the character is one of the allowed single-character tokens:
            // Operators: +, -, *, /
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                System.out.println("\"" + c + "\" - an operator");
                expressionCounter++;
                continue;
            }
            
            // Parentheses.
            if (c == '(') {
                System.out.println("\"" + c + "\" - a left parenthesis");
                expressionCounter++;
                continue;
            }
            if (c == ')') {
                System.out.println("\"" + c + "\" - a right parenthesis");
                expressionCounter++;
                continue;
            }
            
            // If the token starts with a letter or digit, collect all letters or digit that makes a unit.
            if (Character.isLetter(c) || Character.isDigit(c)) {
                int start = expressionCounter;
                boolean startsWithLetter = Character.isLetter(c);
                // If token starts with digit, we assume it should be a number.
                // But if any letter appears after, then it is an illegal token.
                boolean validNumber = Character.isDigit(c);
                expressionCounter++;
                while (expressionCounter < input.length() && (Character.isLetter(input.charAt(expressionCounter)) || Character.isDigit(input.charAt(expressionCounter)))) {
                    char next = input.charAt(expressionCounter);
                    if (!Character.isDigit(next)) {
                        validNumber = false;
                    }
                    expressionCounter++;
                }
                String token = input.substring(start, expressionCounter);
                
                // Classify the token.
                if (startsWithLetter) {
                    // It is an identifier because it starts with a letter.
                    System.out.println("\"" + token + "\" - an identifier");
                } else {
                    if (validNumber) {
                        System.out.println("\"" + token + "\" - a number");
                    } else {
                        System.out.println("\"" + token + "\" - an illegal token");
                    }
                }
                continue;
            }
            
            // If the character does not match any of the allowed types,
            // treat it as an illegal token.
            System.out.println("\"" + c + "\" - an illegal token");
            expressionCounter++;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 1) {
            System.out.println("Usage: java Tokenizer <input_file>");
            System.exit(1);
        }
        String fileName = args[0];
        StringBuilder sb = new StringBuilder();
        
        // Read the entire file into a single string.
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Append a space to ensure tokens separated by punctuation are processed.
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            System.exit(1);
        }
        
        String input = sb.toString();
        tokenizeHelper(input);

	}

}
