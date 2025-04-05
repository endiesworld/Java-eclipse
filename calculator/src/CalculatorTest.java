
public class CalculatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runTests();
	}
	
	
	private static void runTests() {
	    // Valid test cases
	    test("2+3", 5.0);
	    test("2+3*4", 14.0);
	    test("(2+3)*4", 20.0);
	    test("2^3", 8.0);
	    test("2^3^2", 512.0);
	    test("(2+1)^2", 9.0);
	    test("(2+1)^(1+1)", 9.0);
	    test("-(2+3)^2", -25.0);
	    test("-5+2", -3.0);
	    test("2.5*2", 5.0);
	    test("2^2^2", 16.0);
	    test("(((2)))", 2.0);
	    test("-(-5)", 5.0);
	    test("-(3^2)+1", -8.0);

	    // Invalid input tests
	    testExpectError("2++3");
	    testExpectError("5*/2");
	    testExpectError("4^");
	    testExpectError("()");
	    testExpectError("3+");
	    testExpectError("-(5");
	    testExpectError("2^2)");
	    testExpectError("2+*3");

	    System.out.println("All test cases passed!");
	}
	
	private static void test(String input, double expected) {
	    Calculator parser = new Calculator(input);
	    double result = parser.parse();

	    if (Math.abs(result - expected) > 1e-9) {
	        throw new AssertionError("Test failed for input: " + input +
	                                 "\nExpected: " + expected + ", but got: " + result);
	    } else {
	        System.out.println(input + " = " + result);
	    }
	}
	
	private static void testExpectError(String input) {
	    try {
	        Calculator parser = new Calculator(input);
	        parser.parse();
	        throw new AssertionError("Expected error but got success for input: " + input);
	    } catch (RuntimeException e) {
	        System.out.println("(error caught) " + input + " → " + e.getMessage());
	    }
	}
}
