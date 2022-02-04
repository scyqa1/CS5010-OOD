package bignumber;

/**
 * This will build a linked list of single digit numbers that will be used to
 * represent a single very large number.
 */
public class BigNumberImpl implements BigNumber {
  private BigNumberList number;

  /**
   * This constructor takes no arguments and creates a list with the number zero.
   */
  public BigNumberImpl() {
    this.number = new BigNumberList(0);
  }

  /**
   * Create a BigNumber instance by inputting a IListOfNumber instance,
   * which is the internal implementation of BigNumber.
   *
   * @param number the IListOfNumber instance
   */
  public BigNumberImpl(BigNumberList number) {
    if (number == null) {
      throw new IllegalArgumentException("Invalid number.");
    }

    this.number = number;
  }
  
  public BigNumberImpl(String data) {   
	int length = data.length();  
 	
	char singleNumber = data.charAt(length-1);
	    
	int singleNum = Character.getNumericValue(singleNumber);
 	this.number = new BigNumberList(singleNum);
 	BigNumberList temp = this.number;
	
 	for(int i= (length-2); i >= 0; i--) {
 	    singleNumber = data.charAt(i);
 	    singleNum = Character.getNumericValue(singleNumber);
 		temp.setNext(new BigNumberList(singleNum));
 		temp = temp.getNext();
 	}
 	
  }



  @Override
  public int length() {
    return this.number.length();
  }

  @Override
  public void shiftLeft(int numberOfShifts) throws IllegalArgumentException {
	if (numberOfShifts < 0) {
		this.shiftRight(-numberOfShifts);
	}
	else{
		if(this.number.getValue() != 0 || this.number.getNext() != null) {
			while (numberOfShifts > 0) {		
				this.number = new BigNumberList(0, this.number);	    	
				numberOfShifts--;
			}
		}
		
	}
    
  }

  @Override
  public void shiftRight(int numberOfShifts) throws IllegalArgumentException {
	  if (numberOfShifts < 0) {
			this.shiftLeft(-numberOfShifts);
		}
	  else if (numberOfShifts >= this.length()) {
	      this.number = new BigNumberList(0);
	    }
	  else {
		  while (numberOfShifts > 0) {
			  this.number = this.number.getNext();
			  numberOfShifts--;
			}
	  }
  }

  @Override
  public void addDigit(int singleDigit) throws IllegalArgumentException {
	if(singleDigit < 0 || singleDigit >9)
		throw new IllegalArgumentException("Invalid sigleDigit.");
	
    int temp = this.number.getValue() + singleDigit;
    if (temp < 10) {
    	this.number.setValue(temp);
    }
    else {
    	this.number.setValue(temp - 10);
    	
    	if(this.number.getNext() == null) {
    		BigNumberList firstDigit = new BigNumberList(1);
			this.number = new BigNumberList(this.number.getValue(), firstDigit);
		}else if (this.number.getNext().getValue() != 9) {
    		this.number.getNext().setValue(this.number.getNext().getValue() + 1);
    	}
    	else {
    		this.number.getNext().setValue(0);
    		if(this.number.getNext().getNext() != null) {
    			this.number.getNext().getNext().setValue(this.number.getNext().getNext().getValue() + 1); 
    		}else {
    			BigNumberList firstDigit = new BigNumberList(1);
    			this.number.setNext(new BigNumberList(this.number.getNext().getValue(), firstDigit)); 
    		}
    		   		
    	}
    }
  }

  @Override
  public int getDigitAt(int positionOfDigit) throws IllegalArgumentException {
    return this.number.getDigitAt(positionOfDigit);
  }

  @Override
  public BigNumber copy() {
    return new BigNumberImpl(this.number.copy());
  }

  @Override
  public BigNumber add(BigNumber numberToAdd) {
	    String firstString = this.toString();
	    String secondString = numberToAdd.toString();
	    if (firstString.length() > secondString.length()) {
	      return new BigNumberImpl(this.addStr(firstString, secondString));
	    }
	    return new BigNumberImpl(this.addStr(secondString, firstString));
	  }
 
  
  private String addStr(String lrg, String sml) {

	    byte[] n1 = new byte[lrg.length()];
	    byte[] n2 = new byte[sml.length()];

	    for (int i = 0; i < lrg.length(); i++) {
	      char c = lrg.charAt(i);
	      byte in = (byte) Character.getNumericValue(c);
	      n1[i] = in;
	    }
	    for (int i = 0; i < sml.length(); i++) {
	      char c = sml.charAt(i);
	      byte in = (byte) Character.getNumericValue(c);
	      n2[i] = in;
	    }
	    int mx = Math.max(n1.length, n2.length);
	    byte[] n3 = new byte[mx + 1];
	    int r1 = n1.length - 1;
	    int r2 = n2.length - 1;
	    int r3 = n3.length - 1;
	    byte carry = 0;

	    while (r3 >= 0) {
	      byte sum = carry;

	      if (r1 >= 0) {
	        sum += n1[r1--];
	      }
	      if (r2 >= 0) {
	        sum += n2[r2--];
	      }
	      carry = (byte) (sum / 10);
	      n3[r3--] = (byte) (sum % 10);
	    }

	    char[] cc = new char[n3.length];
	    for (int b = 0; b < n3.length; b++) {
	      cc[b] = toChar(n3[b]);
	    }

	    String ret = new String(cc);
	    if (ret.charAt(0) == '0') {
	      ret = ret.substring(1);
	    }
	    return ret;
	  }

  private char toChar(byte b) {
	    switch (b) {
	      case 1:
	        return '1';
	      case 2:
	        return '2';
	      case 3:
	        return '3';
	      case 4:
	        return '4';
	      case 5:
	        return '5';
	      case 6:
	        return '6';
	      case 7:
	        return '7';
	      case 8:
	        return '8';
	      case 9:
	        return '9';
	      default:
	        return '0';
	    }
	    
  }
  
  @Override
  public int compareTo(BigNumber number) {    
    String firstNumber = this.toString();
    String secondNumber = number.toString();
    return isBigger(firstNumber, secondNumber);
  }

  private int isBigger(String firstNumber, String secondNumber) {
	    if (firstNumber.length() > secondNumber.length()) {
	      return 1;
	    } else if (secondNumber.length() > firstNumber.length()) {
	      return -1;
	    } else {
	      for (int index = 0; index < firstNumber.length(); index++) {
	        char firstNumberCharacter = firstNumber.charAt(index);
	        char secondNumberCharacter = secondNumber.charAt(index);

	        if (Integer.parseInt(String.valueOf(firstNumberCharacter))
	                > Integer.parseInt(String.valueOf(secondNumberCharacter))) {
	          return 1;
	        } else if (Integer.parseInt(String.valueOf(secondNumberCharacter))
	                > Integer.parseInt(String.valueOf(firstNumberCharacter))) {
	          return -1;
	        }
	      }
	    }
	    return 0;
	  }
  
  @Override
  public String toString() {
    return this.number.toString();
  }

}