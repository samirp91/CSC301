package edu.toronto.csc301;

public class SimpleCalculator {
	private int count;


	/**
	 * @return The sum of x and y.
	 */
	public int add(int x, int y){
		count++
		return x+y; //FIXME: edit so passes unit tests
	}
	
	
	/**
	 * @return How many add operations were performed by this instance.
	 */
	public int countOperationsSoFar(){
		return count; //FIXME: edit so passes unit tests
	}

}
