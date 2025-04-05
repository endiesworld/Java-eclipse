package simplePerser;

public class RecursiveDescentParserAddition {
	
	private String[] tokens;
	private int pos;
	
	public RecursiveDescentParserAddition(String str) {
		this.tokens = str.trim().split("\\s+");
		this.pos = 0; 
		
	}
	
	public int parseInteger() {
		int number ;
		
		if (this.pos < this.tokens.length) {
			try {
				number = Integer.parseInt(this.tokens[this.pos]);
				this.pos++;
			}
			catch(NumberFormatException e) {
				throw new RuntimeException("Expected a number at position " + pos);
			}
			
			return number;
		}
		throw new RuntimeException("Unexpected end of input; expected a number");
	}
	
	public String parseOperator() {
		
		if (this.pos < this.tokens.length) {
			String ops = this.tokens[this.pos];
			this.pos++;
			return ops;
		}
		throw new RuntimeException("Unexpected end of input; expected a number");
	}
	
	public int additionOps(int exp1, int exp2) {
		
		return exp1 + exp2;
	}
	
	public int expressionExecutor() {
		try {
			int exp1 = this.parseInteger(); 
			
			String ops = this.parseOperator();
			
			int exp2 = this.parseInteger(); 
			
			if(ops.equals("+")) {
				
				return this.additionOps(exp1, exp2);
			}
		
		}catch(NumberFormatException e) {
			throw new RuntimeException(e);
		}
		
		throw new RuntimeException("Unexpected end of input; expected a number");
	}
}
