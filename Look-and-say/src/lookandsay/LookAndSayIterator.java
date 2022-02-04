package lookandsay;

import java.math.BigInteger;

public class LookAndSayIterator implements RIterator<BigInteger> {
	final BigInteger startingSeed;
	final BigInteger endVal;
	
	BigInteger current;

	// Constructor that takes two arguments: a starting seed and an end value
	public LookAndSayIterator(BigInteger startingSeed, BigInteger endVal) {
		if (startingSeed.compareTo(BigInteger.ZERO) <= 0){
            throw new IllegalArgumentException("starting seed must be positive");
		}
		if (startingSeed.compareTo(endVal) >= 0){
			throw new IllegalArgumentException("starting seed must be less than the end");
		}
		if (startingSeed.toString().contains("0")){
			throw new IllegalArgumentException("starting seed must not have any zeroes in it");
		}
	
		this.startingSeed = startingSeed;
		this.endVal = endVal;
		
		this.current = this.startingSeed;
	}
	
	// Constructor that takes a starting seed as its only argument
	public LookAndSayIterator(BigInteger startingSeed) {
		this.endVal = new BigInteger("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		
		if (startingSeed.compareTo(BigInteger.ZERO) <= 0){
            throw new IllegalArgumentException("starting seed must be positive");
		}
		if (startingSeed.compareTo(endVal) >= 0){
			throw new IllegalArgumentException("starting seed must be less than the end");
		}
		if (startingSeed.toString().contains("0")){
			throw new IllegalArgumentException("starting seed must not have any zeroes in it");
		}
	
		this.startingSeed = startingSeed;
		
		this.current = this.startingSeed;
	}
	
	// Constructor that takes no arguments
	public LookAndSayIterator() {
		this.startingSeed = new BigInteger("1");
		this.endVal = new BigInteger("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
		
		this.current = this.startingSeed;
	}
	
	
	
	@Override
	public boolean hasNext() {
		return current.compareTo(endVal) < 0;
	}

	@Override
	public BigInteger next() {
		BigInteger temp = current;
		if (hasNext()) {
			String curr = current.toString();
			String next = "";
			
			int same = 1;
			for (int i = 1; i < curr.length(); i++) {
				if (curr.charAt(i) == curr.charAt(i - 1)) {
					same++;
	            } else {
	            	next += (String.valueOf(same) + curr.charAt(i - 1));	

	                same = 1;
	            }
				
			}
			next += (String.valueOf(same) + curr.charAt(curr.length() - 1));
			
			current = new BigInteger(next);
		}
		return temp;
	}
	
	@Override
	public boolean hasPrevious() {
		return current.toString().length() % 2 == 0;
	}

	@Override
	public BigInteger prev() {
		BigInteger temp = current;
		if (hasPrevious()) {
			String curr = current.toString();
			String prev = "";
			
			for (int i = 0; i < curr.length(); i = i + 2) {
				for (int j = 0; j < Character.getNumericValue(curr.charAt(i)); j++) {
					prev += curr.charAt(i + 1);
				}
			}
			
			current = new BigInteger(prev);
		}
		return temp;
	}
	
	/*
	public static void main(String[] args) {
		BigInteger a = new BigInteger("3");
		RIterator<BigInteger> iterator = new LookAndSayIterator(a);
		System.out.println(iterator.next().toString());
		System.out.println(iterator.next().toString());
		System.out.println(iterator.next().toString());

		
		System.out.println(iterator.prev().toString());
		System.out.println(iterator.prev().toString());
		
		iterator = new LookAndSayIterator();
		System.out.println(iterator.next().toString());
		System.out.println(iterator.next().toString());
		System.out.println(iterator.next().toString());

		
		System.out.println(iterator.prev().toString());
		System.out.println(iterator.prev().toString());

	}
	*/
	


}
