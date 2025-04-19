package expression;
import java.util.*;


public class CalculatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		runTests();
		
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter calculations (type 'exit' to quit):");
        
        while (true) {
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) break;
            if (line.isEmpty()) continue;

            try {
                Calculator calc = new Calculator(line);
                double result = calc.parseCalculation();
                System.out.println("=> " + result);
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
	}
	
	
	
	private static void runTests() {
        // Reset symbol table before test run
        System.out.println("📌 Running Calculator Tests...\n");

        test("define a = 5 + 3", 8.0);
        test("define b = a + 2", 10.0);
        test("a + b", 18.0);
        test("define c = (2 + 3) ^ 2", 25.0);
        test("c ^ c", Math.pow(25, 25));
        test("define z = ((c + a) ^ (c - 1)) ^ (2 ^ 2)", 5.988778191689467E145);  // Likely overflow

        // Unary tests
        test("define u = +4", 4.0);
        test("define v = -5", -5.0);
        test("u + v", -1.0);

        // Parentheses and precedence
        test("define m = (2 + 3) * 4", 20.0);
        test("define n = 2 + 3 * 4", 14.0);

        // Reuse of variables in powers
        test("define p = 2", 2.0);
        test("define q = (p + 3) ^ (1 + 1)", 25.0);

        // Final expected value
        test("q + 10", 35.0);

        // Negative test — undefined variable
        try {
            test("undefinedVar + 2", 0); // expected to fail
            System.out.println("Test FAILED (undefined variable not caught)");
        } catch (RuntimeException e) {
            System.out.println("Passed undefined variable test: " + e.getMessage());
        }
    }

    private static void test(String input, double expected) {
        Calculator calc = new Calculator(input);
        double result = calc.parseCalculation();

        if (Double.isInfinite(expected)) {
            if (Double.isInfinite(result)) {
                System.out.println( input + " => ∞ (overflow)");
            } else {
                System.out.println( input + " => " + result + ", expected ∞");
            }
        } else if (Math.abs(result - expected) < 1e-6) {
            System.out.println(input + " => " + result);
        } else {
            System.out.println(input + " => " + result + ", expected " + expected);
        }
    }
	
}
