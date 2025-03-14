package hugeInteger;

import java.util.*;


public class HugeInteger {

    // Internal representation: the digits are stored in reverse order.
    // For example, if the number is 1234 then digits[0]=4, digits[1]=3, digits[2]=2, digits[3]=1.
    List<Integer> decimalDigits2 = new ArrayList<Integer>();
    
    // 'length' holds the number of digits currently used (in our minimal representation).
    private int length;         
    
    // 'capacity' is the maximum number of digits this object is allowed to store.
    //The capacity field tells you how many digits are allowed to be "in use" for that particular object. 
    //Even though the array is large enough for 300 digits, the arithmetic operations check against the object's 
    //capacity and will throw an exception if the result exceeds that limit.
    //This separation makes the code easier to write because of my several attempt to implement the arithmetics operations, 
    // and further to my conversation with the T.A. Always having a fixed buffer (size 300) to work with, 
    //while still being able to enforce different maximum digit limits on different HugeInteger objects.
    private final int capacity; 

    // Enum for sign.
    public enum Sign {
        PLUS, MINUS
    }
    private Sign sign;

    /* *******************************
     * Constructors
     * *******************************
     */

    /**
     * Constructor that creates a HugeInteger of given capacity (max allowed digits).
     * The resulting number is initialized to 0.
     * @param capacity the maximum number of digits (must be between 1 and HUGE_INTEGER_SIZE)
     */
    public HugeInteger(int length) {
    	if(length < 0) {
    		throw new IllegalArgumentException("Lenght cannot be negative");
    	}

        this.capacity = length;  // This is assumming that we want HugeInteger to be BOUNDED to a specific capacity
        for(int i = 0 ; i< length; i++) {
        	this.decimalDigits2.add((Integer) 0) ;
        }
        
        this.length = 1;
        this.sign = Sign.PLUS; // This assumes that the default number is a positive number
    }

    /**
     * Constructor that creates a HugeInteger from a string.
     * The string may begin with an optional '+' or '-' sign.
     * The capacity is set to the number of digits provided.
     * Digits are stored in reverse order.
     * @param str the string representation (e.g., "-12345")
     */
    public HugeInteger(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string is empty");
        }
        int startIndex = 0;
        
        str = str.trim(); //Eliminates any erroneous leading or trailing white spaces 
        
        if (str.charAt(0) == '-') {
            this.sign = Sign.MINUS;
            startIndex = 1;
        } else if (str.charAt(0) == '+') {
            this.sign = Sign.PLUS;
            startIndex = 1;
        } else {
            this.sign = Sign.PLUS; // This assumes that the default number is a positive number
        }
        
        
        // Determine number of digit characters.
        int numDigits = str.length() - startIndex;
        
        // In this constructor, we set the allowed capacity equal to the number of digits provided.
        this.capacity = numDigits;
        // Always allocate the full underlying array (we use HUGE_INTEGER_SIZE) but we will restrict ourselves to capacity.

        // Copy the digits from the string into the array in reverse order.
        // For example, "1234" is stored as digits[0]=4, digits[1]=3, etc.
        // With the least significant digit at index 0, we can perform addition, subtraction, 
        // and multiplication starting from index 0 (the units digit) and moving upward. 
        // This mirrors how we perform these operations manually (starting from the rightmost digit) 
        // and makes the implementation cleaner and more intuitive.
        for (int i = 0; i < numDigits; i++) {
            char c = str.charAt(str.length() - 1 - i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Invalid character in input string: " + c);
            }
            this.decimalDigits2.add((Integer) Character.getNumericValue(c));
        }
        
        // Check if there are leading zeros, and decreases the actual length to start from the first significant figure.
        int actualLen = numDigits;
        while (actualLen > 1 && this.decimalDigits2.get(actualLen - 1) == 0) {
            actualLen--;
        }
        this.length = actualLen;
        // If the number is 0, we always use the positive sign. This is because we can't have -0
        if (this.length == 1 && this.decimalDigits2.get(0) == 0) {
            this.sign = Sign.PLUS;
        }
    }

    /* *******************************
     * Accessor methods
     * *******************************
     */

    /**
     * Returns the current number of digits used in the minimal representation.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Returns the digit at the specified index (starting at 0 for the least significant digit).
     * If the index is out of bounds, prints an error message and returns 0.
     */
    public int getDigit(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Index " + index + " is out of bounds.");
            return 0;
        }
        return decimalDigits2.get(index);
    }

    /**
     * Returns a string representation of this HugeInteger.
     * The most-significant digit is printed first. If negative, the string begins with '-'.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.sign == Sign.MINUS) {
            sb.append("-");
        }
        // Append digits from most significant (at index length-1) to least significant.
        for (int i = this.length - 1; i >= 0; i--) {
            sb.append(this.decimalDigits2.get(i));
        }
        return sb.toString();
    }

    /**
     * A static print method that prints the HugeInteger.
     */
    public static void print(HugeInteger i) {
        if (i == null) {
            System.out.println("null");
        } else {
            System.out.println(i.toString());
        }
    }

    /**
     * Assignment operation: copies the value of the given HugeInteger into this object.
     */
    public void assign(HugeInteger other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot assign from null.");
        }
        
        // Note: We do not change this.capacity.
        this.sign = other.sign;
        this.length = other.length;
        // Copy only the used portion of the array.
        if(this.length >= 1) {
        	this.decimalDigits2.clear();
        }
        
        this.decimalDigits2.addAll(other.decimalDigits2);
        
    }

    /**
     * Checks whether this HugeInteger is equal to the other.
     */
    public boolean equals(HugeInteger other) {
        if (other == null) {
            return false;
        }
        if (this.sign != other.sign || this.length != other.length) {
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            if (this.decimalDigits2.get(i) != other.decimalDigits2.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this HugeInteger is greater than the other.
     * Comparison is done digit-by-digit (taking into account the sign).
     */
    public boolean greaterThan(HugeInteger other) {
        if (other == null) {
            throw new IllegalArgumentException("Null argument.");
        }
        // Different signs: positive is always greater than negative.
        if (this.sign != other.sign) {
            return this.sign == Sign.PLUS;
        }
        // Both have the same sign.
        if (this.sign == Sign.PLUS) {
            if (this.length != other.length) {
                return this.length > other.length;
            }
            for (int i = this.length - 1; i >= 0; i--) {
                if (this.decimalDigits2.get(i) != other.decimalDigits2.get(i)) {
                    return this.decimalDigits2.get(i) > other.decimalDigits2.get(i);
                }
            }
            return false; // They are equal.
        } else {
            // For negative numbers the comparison is reversed.
            if (this.length != other.length) {
                return this.length < other.length;
            }
            for (int i = this.length - 1; i >= 0; i--) {
                if (this.decimalDigits2.get(i) != other.decimalDigits2.get(i)) {
                    return this.decimalDigits2.get(i) < other.decimalDigits2.get(i);
                }
            }
            return false; // They are equal.
        }
    }

    /**
     * Returns true if this HugeInteger is less than the other.
     */
    public boolean lessThan(HugeInteger other) {
        return (!this.equals(other)) && !this.greaterThan(other);
    }

    /**
     * Returns a new HugeInteger that is the absolute value of this one.
     * The capacity of the new object is the same as this one.
     */
    public HugeInteger abs() {
        // Create a new HugeInteger with the same capacity.
        HugeInteger result = new HugeInteger(this.capacity);
        result.length = this.length;
        result.decimalDigits2.clear();
        result.decimalDigits2.addAll(this.decimalDigits2);
        result.sign = Sign.PLUS;
        return result;
    }

    /* *******************************
     * Arithmetic Operations (in-place) according to the UML
     * public void assign(HugeInteger i)
     * public void add(HugeInteger i)
	 * public void mult(HugeInteger i)
	 * public void divide(HugeInteger i)
     * *******************************
     *
     * In each operation the computed result is stored in the calling object.
     * Before updating, the result is computed in a temporary array.
     * If the computed number of digits exceeds this object's capacity,
     * an Exception is thrown. This is in accordance to my conversation with the T.A via discord on 02/17/2025
     */

    /**
     * In-place addition: this = this + other.   
     * Uses digit-by-digit addition (with carry propagation).
     * If the result requires more digits than this.capacity, an exception is thrown.
     */
    public void add(HugeInteger other) throws Exception {
        if (other == null) {
            throw new IllegalArgumentException("Null argument in add.");
        }
        // If both operands have the same sign i.e (-) and (-) or (+) and (+), perform addition.
        if (this.sign == other.sign) {
            int maxLen = (this.length > other.length) ? this.length : other.length;
            // Temporary array for the result (worst-case length is maxLen+1)
            List<Integer> tempArray = new ArrayList<Integer>();
            int carry = 0;
            int i = 0;
            
            for (; i < maxLen; i++) {
                int d1 = (i < this.length ? this.decimalDigits2.get(i) : 0);
                int d2 = (i < other.length ? other.decimalDigits2.get(i) : 0);
                int sum = d1 + d2 + carry;
                tempArray.add((Integer) (sum % 10));
                carry = sum / 10;
            }
            if (carry > 0) {
                tempArray.add( (Integer) carry);
            }

            this.decimalDigits2.clear();
            this.decimalDigits2.addAll(tempArray);
            this.length = i;
            // Sign remains unchanged.
            if (this.length == 1 && this.decimalDigits2.get(0) == 0) {
                this.sign = Sign.PLUS;
            }
        } else {
            // When the signs differ, a + (-b) equals a - |b| (and similarly for (-a) + b).
            if (this.sign == Sign.PLUS) {
                // this + (-other) = this - |other|
                this.subtract(other.abs());
            } else {
                // (-this) + other = other - |this|, then the result takes the sign of the larger absolute value.
                HugeInteger temp = other.abs();
                temp.subtract(this.abs());

                this.decimalDigits2.clear();
                this.decimalDigits2.addAll(temp.decimalDigits2);
                this.length = temp.decimalDigits2.size();
                this.sign = temp.sign;
            }
        }
    }

    /**
     * In-place subtraction: this = this - other.
     * Uses digit-by-digit subtraction (with borrow propagation).
     * If the result requires more digits than this.capacity, an exception is thrown.
     */
    public void subtract(HugeInteger other) throws Exception {
        if (other == null) {
            throw new IllegalArgumentException("Null argument in subtract.");
        }
        // If the signs differ, subtraction becomes addition.
        if (this.sign != other.sign) {
            // a - (-b) = a + b, and (-a) - b = -(a + b)
            HugeInteger aAbs = this.abs();
            HugeInteger bAbs = other.abs();
            aAbs.add(bAbs);

            this.decimalDigits2.clear();
            this.decimalDigits2.addAll(aAbs.decimalDigits2);
            this.length = aAbs.decimalDigits2.size();
            this.sign = (this.sign == Sign.PLUS ? Sign.PLUS : Sign.MINUS);
            // Special case: if the result is zero, force positive sign.
            if (this.length == 1 && this.decimalDigits2.get(0) == 0) {
                this.sign = Sign.PLUS;
            }
            return;
        } else {
            // Both operands have the same sign.
            // We first compare the absolute values.
            int cmp = compareAbsolute(this, other);
            // If they are equal, the result is 0.
            if (cmp == 0) {
                this.decimalDigits2.set(0,0);
                this.length = 1;
                this.sign = Sign.PLUS;
                return;
            }
            List<Integer> temp = new ArrayList<>();
            int borrow = 0;
            int resultLen;
            if (cmp > 0) {
                // |this| > |other|; result takes the same sign as this.
                for (int i = 0; i < this.length; i++) {
                    int d1 = this.decimalDigits2.get(i);
                    int d2 = (i < other.length ? other.decimalDigits2.get(i) : 0);
                    int diff = d1 - d2 - borrow;
                    if (diff < 0) {
                        diff += 10;
                        borrow = 1;
                    } else {
                        borrow = 0;
                    }
                    temp.add((Integer) diff);
                }
                resultLen = this.length;
            } else { 
                // |this| < |other|; result sign will be the opposite of this.
                for (int i = 0; i < other.length; i++) {
                    int d1 = other.decimalDigits2.get(i);
                    int d2 = (i < this.length ? this.decimalDigits2.get(i) : 0);
                    int diff = d1 - d2 - borrow;
                    if (diff < 0) {
                        diff += 10;
                        borrow = 1;
                    } else {
                        borrow = 0;
                    }
                    temp.add((Integer) diff);
                }
                resultLen = other.length;
            }
            // Remove any extra leading zeros.
            while (resultLen > 1 && temp.get(resultLen - 1) == 0) {
                resultLen--;
            }
            this.decimalDigits2.clear();
            this.decimalDigits2.addAll(temp);
            this.length = resultLen;
            // Set the sign: if |this| was larger, sign remains; otherwise, flip it.
            if (cmp < 0) {
                this.sign = (this.sign == Sign.PLUS ? Sign.MINUS : Sign.PLUS);
            }
            if (this.length == 1 && this.decimalDigits2.get(0) == 0) {
                this.sign = Sign.PLUS;
            }
        }
    }

    /**
     * In-place multiplication: this = this * other.
     * Uses the straightforward digit-by-digit multiplication algorithm.
     * If the result requires more digits than this.capacity, an exception is thrown.
     */
    public void mult(HugeInteger other) throws Exception {
        if (other == null) {
            throw new IllegalArgumentException("Null argument in multiply.");
        }
        int n = this.decimalDigits2.size();
        int m = other.decimalDigits2.size();
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n + m, 0));
        
        ArrayList<Integer> this_copy = new ArrayList<>();
        ArrayList<Integer> other_copy = new ArrayList<>();
        
        for (int i = n - 1; i >= 0; i--) {
        	this_copy.add(this.decimalDigits2.get(i));
        }
        
        for (int i = m - 1; i >= 0; i--) {
        	other_copy.add(other.decimalDigits2.get(i));
        }

        // Perform digit-by-digit multiplication (like primary school multiplication)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int product = this_copy.get(i) * other_copy.get(j);
                int pos1 = i + j, pos2 = i + j + 1;
                int sum = product + result.get(pos2);

                result.set(pos2, sum % 10);
                result.set(pos1, result.get(pos1) + sum / 10);
            }
        }
        
     // Remove leading zeros
        while (result.size() > 1 && result.get(0) == 0) {
            result.remove(0);
        }

        
        this.decimalDigits2.clear();
        for(int i = result.size() - 1; i >= 0; i--)
        	this.decimalDigits2.add(result.get(i));
        this.length = result.size();
        // Determine the resulting sign.
        this.sign = (this.sign == other.sign ? Sign.PLUS : Sign.MINUS);
        if (this.length == 1 && this.decimalDigits2.get(0) == 0) {
            this.sign = Sign.PLUS;
        }
    }
    
    
    public void divide(HugeInteger divisor) throws Exception {
        if (divisor == null) {
            throw new IllegalArgumentException("Divisor cannot be null.");
        }
        // Check for division by zero.
        HugeInteger absDivisor = divisor.abs();
        if (absDivisor.equals(new HugeInteger("0"))) {
            throw new Exception("Division by zero.");
        }
        // Work with absolute values.
        HugeInteger absDividend = this.abs();
        // If absDividend < absDivisor then the quotient is 0.
        if (absDividend.lessThan(absDivisor)) {
            // Set this to 0.
            this.decimalDigits2.set(0, 0);
            this.length = 1;
            this.sign = Sign.PLUS;
            return;
        }
        // Convert the absolute dividend to its string (normal order) representation.
        String dividendStr = absDividend.toString();
        StringBuilder quotientBuilder = new StringBuilder();
        // 'remainderStr' will hold the current remainder as a string.
        String remainderStr = "";
        // Process each digit of the dividend.
        for (int i = 0; i < dividendStr.length(); i++) {
            // Bring down the next digit.
            remainderStr += dividendStr.charAt(i);
            remainderStr = removeLeadingZeros(remainderStr);
            // Create a HugeInteger from remainderStr (if empty, treat as 0).
            HugeInteger remainder = new HugeInteger(remainderStr.isEmpty() ? "0" : remainderStr);
            int count = 0;
            // Subtract absDivisor repeatedly until remainder < absDivisor.
            while (!remainder.lessThan(absDivisor)) {
                remainder.subtract(absDivisor);
                count++;
            }
            // Append the computed digit to the quotient.
            quotientBuilder.append(count);
            // Update remainderStr for the next iteration.
            remainderStr = remainder.toString();
            if (remainderStr.startsWith("-")) {
                // Should not happen for absolute values.
                remainderStr = remainderStr.substring(1);
            }
            if (remainderStr.equals("0")) {
                remainderStr = "";
            }
        }
        // Remove any leading zeros from the quotient.
        String quotientStr = removeLeadingZeros(quotientBuilder.toString());
        if (quotientStr.equals("")) {
            quotientStr = "0";
        }

        // Create a new HugeInteger from the quotient string.
        HugeInteger quotient = new HugeInteger(quotientStr);
        // Set the sign: if the original dividend and divisor have the same sign, the quotient is positive.
        quotient.sign = (this.sign == divisor.sign ? Sign.PLUS : Sign.MINUS);
        // Replace this object's value with the quotient.
        this.assign(quotient);
    }
    
    public static void multPerformance(HugeInteger a, HugeInteger b) {
    	long startTime = System.nanoTime();
    	
    	try{
    		a.mult(b);
    	}catch(Exception e) {
			System.out.println("Exception thrown");
		};
    	
    	long endTime = System.nanoTime();

    	long duration = endTime - startTime;  // Time in milliseconds
    	System.out.println("multPerformance for " + a +" Multiplied " + b + " Operation took " + duration + " nanoseconds");
    	
    }

    /* *******************************
     * Private Helper Methods
     * *******************************
     */
    
    
    /**
     * Removes any leading zeros from a numeric string.
     * For example, "000123" becomes "123".
     */
    private static String removeLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        return s.substring(i);
    }
    

    /**
     * Compares the absolute values of two HugeIntegers.
     * Returns 1 if a > b, 0 if equal, and -1 if a < b.
     */
    private static int compareAbsolute(HugeInteger a, HugeInteger b) {
        if (a.length > b.length)
            return 1;
        if (a.length < b.length)
            return -1;
        for (int i = a.length - 1; i >= 0; i--) {
            if (a.decimalDigits2.get(i) > b.decimalDigits2.get(i))
                return 1;
            if (a.decimalDigits2.get(i) < b.decimalDigits2.get(i))
                return -1;
        }
        return 0;
    }

}
