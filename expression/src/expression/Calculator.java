package expression;

import java.util.*;

public class Calculator {
	private static final Map<String, Double> variableTable = new HashMap<>();
	 private String[] tokens;  // Token array (e.g. {"2", "+", "3"})
	    private int pos = 0;      // Current position in the token array

	    // Constructor takes the raw input string and tokenizes it
	    public Calculator(String input) {
	    	String[] raw = tokenizeWithoutSpaces(input.trim());
	        tokens = new String[raw.length + 1];
	        System.arraycopy(raw, 0, tokens, 0, raw.length);
	        tokens[raw.length] = "EOF";  // end of input

	    }

	    
	    // Tokenizer builder
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
	            else if ("+-*/()^=".indexOf(c) != -1) {
	                result.add(String.valueOf(c));
	                i++;
	            }

	            
	            
	            else if(Character.isLetter(c)) {
	            	StringBuilder str = new StringBuilder();
	            	while(i < input.length() && Character.isLetterOrDigit(input.charAt(i))) {
	            		str.append(input.charAt(i));
	            		i++;
	            	}
	            	
	            	result.add(str.toString());
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

	 
	 // Entry point to parse either expression or definition
	    public double parseCalculation() {
//	        if (tokens[pos].equals("define")) {
//	            return handleDefinition();
//	        } else {
//	            double result = expr();
//	            if (!tokens[pos].equals("EOF")) {
//	                throw new RuntimeException("Unexpected token after expression: " + tokens[pos]);
//	            }
//	            return result;
//	        }
	    	
	        if (tokens[pos].equals("define")) {
	            return handleDefinition();
	        } else if (isIdentifier(tokens[pos]) && peekNextToken().equals("=")) {
	            return handleAssignment();
	        } else {
	            double result = expr();
	            if (!tokens[pos].equals("EOF")) {
	                throw new RuntimeException("Unexpected token after expression: " + tokens[pos]);
	            }
	            return result;
	        }
	    }
	    
	    
	 // define <identifier> = <expression>
	    private double handleDefinition() {
	        pos++; // skip "define"

	        if (!isIdentifier(tokens[pos])) {
	            throw new RuntimeException("Invalid identifier: " + tokens[pos]);
	        }

	        String name = tokens[pos];
	        pos++;

	        if (!tokens[pos].equals("=")) {
	            throw new RuntimeException("Expected '=' in definition but found: " + tokens[pos]);
	        }

	        pos++; // skip '='
	        double value = expr();
	        variableTable.put(name, value);
	        return value;
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
	                value *= factorWithPower();
	            } else if (tok.equals("/")) {
	                pos++;
	                double divisor = factorWithPower();
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
	            return -factorWithPower();  // apply negation to the next factor
	        }
	        
	        if (tok.equals("+")) {
	            pos++;
	            return factorWithPower();   // Just return the value
	        }
	        
	        // Check identifier first
	        if (isIdentifier(tok)) {
	            if (!variableTable.containsKey(tok)) {
	                throw new RuntimeException("Undefined identifier: " + tok);
	            }
	            pos++;
	            return variableTable.get(tok);
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
	    
	    private boolean isIdentifier(String s) {
	        return s.matches("[a-zA-Z][a-zA-Z0-9_]*");
	    }

	    private double handleAssignment() {
	        String name = tokens[pos];
	        if (! variableTable.containsKey(name)) {
	            throw new RuntimeException("Cannot assign to undefined variable: " + name);
	        }
	        pos++; // skip identifier

	        if (!tokens[pos].equals("=")) {
	            throw new RuntimeException("Expected '=' in assignment but found: " + tokens[pos]);
	        }
	        pos++; // skip '='

	        double value = expr();
	        variableTable.put(name, value); // update existing variable
	        return value;
	    }
	    
	    private String peekNextToken() {
	        if (pos + 1 < tokens.length) {
	            return tokens[pos + 1];
	        }
	        return "EOF";
	    }


}
