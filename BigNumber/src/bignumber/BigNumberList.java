package bignumber;

public class BigNumberList{
	private int value;
	private BigNumberList next;
	
	public void setValue(int val){
		if (val < 0 || val>9) {
			throw new IllegalArgumentException("Value must between 0 to 9. \\n");
		}
		this.value = val;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setNext(BigNumberList next){
		this.next = next;
	}
	
	public BigNumberList getNext() {
		return this.next;
	}
	
	
	
	public BigNumberList(int value) {
		this.setValue(value);
		this.setNext(null);
	}
	
	public BigNumberList(int value, BigNumberList next) {
	      if (value < 0 || value > 9) {
	        throw new IllegalArgumentException("Digit must be between 0 and 9.");
	      }

	      this.setValue(value);
	      this.setNext(next);
	}
	
	  public int length() {
		    int count = 1;
		    BigNumberList temp = this;

		    while (temp.getNext() != null) {
		      temp = temp.getNext();
		      count++;
		    }

		    return count;
		  }
	  
	  public int getDigitAt(int positionOfDigit) throws IllegalArgumentException {
		    if (positionOfDigit < 0 || positionOfDigit > length()) {
		      throw new IllegalArgumentException("There is no digit at this position.");
		    }

		    BigNumberList temp = this;

		    for (int i = 0; i < positionOfDigit; i++) {
		    	temp = temp.getNext();
		    }

		    return temp.getValue();
		  }
	  
	  
	  public BigNumberList copy() {
		  if(this.length() > 1)
	        return new BigNumberList(value, next.copy());
		  else
			return new BigNumberList(value, null);
	  }
	  
	  public String toString() {
	    String result = "";
	    BigNumberList temp = this;

	    while (temp != null) {
	      result = String.format("%d%s", temp.getValue(), result);
	      temp = temp.getNext();
	    }

	    return result;
	  }


	
}


