package edu.toronto.csc301.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.csc301.SimpleCalculator;

public class TestCases {


	private SimpleCalculator calc;

	// This method runs before each test method.
	// For more details, see http://www.mkyong.com/unittest/junit-4-tutorial-1-basic-usage/
	@Before
	public void setUp() throws Exception {
		calc = new SimpleCalculator();
	}
	
	
	

	@Test
	public void testBasicAddition1() {
		assertEquals(2, calc.add(1, 1));
	}
	
	@Test
	public void testBasicAddition2() {
		assertEquals(8, calc.add(3, 5));
	}
	
	@Test
	public void testBasicAddition3() {
		assertEquals(0, calc.add(1, -1));
	}
	
	@Test
	public void testBasicAddition4() {
		assertEquals(134, calc.add(102, 32));
	}
	
	
	
	@Test
	public void testCountOperationsOnNewCaluculator() {
		assertEquals(0, calc.countOperationsSoFar());
	}
	
	@Test
	public void testCountOperations1() {
		calc.add(1, 1);
		assertEquals(1, calc.countOperationsSoFar());
	}
	
	@Test
	public void testCountOperations2() {
		calc.add(1, 1);
		calc.add(3, 7);
		assertEquals(2, calc.countOperationsSoFar());
	}
	
	@Test
	public void testCountOperations3() {
		calc.add(1, 1);
		calc.add(3, 7);
		calc.add(-4, 27);
		assertEquals(3, calc.countOperationsSoFar());
	}
	
	@Test
	public void testCountOperations4() {
		int num = 17;
		for (int i = 0; i < num; i++) {
			calc.add(1, 1);
		}
		assertEquals(num, calc.countOperationsSoFar());
	}
	

}
