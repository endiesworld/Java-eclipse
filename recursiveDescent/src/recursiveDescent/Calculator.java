package recursiveDescent;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
	
	 private String[] tokens;  // Token array (e.g. {"2", "+", "3"})
	    private int pos = 0;      // Current position in the token array

	    // Constructor takes the raw input string and tokenizes it
	    public Calculator(String input) {
	    	String[] raw = tokenizeWithoutSpaces(input.trim());
	        tokens = new String[raw.length + 1];
	        System.arraycopy(raw, 0, tokens, 0, raw.length);
	        tokens[raw.length] = "EOF";  // end of input
	    }

	    
	    
	    private String[] tokenizeWithoutSpaces(String input) {
	        List<String> result = new ArrayList<>();
	        int i = 0;

	        while (i < input.length()) {
	            char c = input.charAt(i);

	            // Skip spaces
	            if (Character.isWhitespace(c)) {
	                i++;
	                continue;
	            }

	            // Number (int or float)
	            if (Character.isDigit(c) || c == '.') {
	                StringBuilder number = new StringBuilder();
	                while (i < input.length() && 
	                      (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
	                    number.append(input.charAt(i));
	                    i++;
	                }
	                result.add(number.toString());
	            }

	            // Operators or parentheses
	            else if ("+-*/()^".indexOf(c) != -1) {
	                result.add(String.valueOf(c));
	                i++;
	            }

	            // Anything else is illegal for now
	            else {
	                throw new RuntimeException("Invalid character: " + c);
	            }
	        }

	        // Convert list to array and add "EOF"
	        result.add("EOF");
	        return result.toArray(new String[0]);
	    }

	 
	    // Entry point to parse and evaluate the expression
	    public double parse() {
	        double result = expr();
	        if (!tokens[pos].equals("EOF")) {
	            throw new RuntimeException("Unexpected token after expression: " + tokens[pos]);
	        }
	        return result;
	    }

	    // expr → term (( "+" | "-" ) term )*
	    private double expr() {
	        double value = term();
	        while (pos < tokens.length) {
	            String tok = tokens[pos];
	            if (tok.equals("+")) {
	                pos++;
	                value += term();
	            } else if (tok.equals("-")) {
	                pos++;
	                value -= term();
	            } else {
	                break;
	            }
	        }
	        return value;
	    }

	    // term → factor (( "*" | "/" ) factor )*
	    private double term() {
	        double value = factorWithPower();
	        while (pos < tokens.length) {
	            String tok = tokens[pos];
	            if (tok.equals("*")) {
	                pos++;
	                value *= factor();
	            } else if (tok.equals("/")) {
	                pos++;
	                double divisor = factor();
	                if (divisor == 0) {
	                    throw new ArithmeticException("Division by zero!");
	                }
	                value /= divisor;
	            } else {
	                break;
	            }
	        }
	        return value;
	    }
	    
	    
	 // Handles optional '^' power on factors
	    private double factorWithPower() {
	        double base = factor();  // could be number or parenthesized expression

	        if (tokens[pos].equals("^")) {
	            pos++;
	            double exponent = factorWithPower();  // right-associative: don't use factorWithPower here!
	            return Math.pow(base, exponent);
	        }

	        return base;
	    }
	    
	    // factor → NUMBER | "(" expr ")"
	    private double factor() {
	        String tok = tokens[pos];
	        
	        // Unary minus support
	        if (tok.equals("-")) {
	            pos++;
	            return -factor();  // apply negation to the next factor
	        }

	        if (isInteger(tok)) {
	            pos++;
	            return Integer.parseInt(tok);
	        } 
	        else if(isDouble(tok)) {
	        	pos++;
	            return Double.parseDouble(tok);
	        }
	        else if (tok.equals("(")) {
	            pos++;
	            double value = expr();
	            if (!tokens[pos].equals(")")) {
	                throw new RuntimeException("Expected ')' but found: " + tokens[pos]);
	            }
	            pos++;
	            return value;
	        } else {
	            throw new RuntimeException("Unexpected token in factor: " + tok);
	        }
	    }


	    // Check if a string is an Integer
	    private boolean isInteger(String s) {
	        try {
	            Integer.parseInt(s);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    // Check if a string is a Double
	    private boolean isDouble(String s) {
	        try {
	            Double.parseDouble(s);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }

}
