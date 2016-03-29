package edu.toronto.csc301.test.cases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.toronto.csc301.IUser;

public class AdFilterTests extends AbsTest {


	//-------------------------------------------------------------------------

	private IUser createTestUser(IUser.AdFilter adFilter) throws Exception {
		IUser u = super.createTestUser();
		u.setAdFilter(adFilter);
		return u;
	}


	private void assertEquals(IUser.AdFilter expected, IUser.AdFilter actual){
		assertNotNull(actual);
		boolean[] B = {false, true};

		// Check that the two filters return the same value for all input combinations
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B.length; j++) {
				assertTrue(expected.displayAd(B[i], B[j]) == actual.displayAd(B[i], B[j]));
			}
		}
	}

	//-------------------------------------------------------------------------

	/**
	 * Convenience helper to generate an ad-filter based on the
	 * binary representation of <code>number</code>. 
	 */
	private IUser.AdFilter generateAdFilter(int number){
		return (x, y) ->  {
			if(!x && !y){
				return getBit(number, 0);
			} else if(!x && y){
				return getBit(number, 1);
			} else if(x && !y){
				return getBit(number, 2);
			} else {
				return getBit(number, 3);
			}
		};
	}

	/**
	 * Get the <code>bitIndex</code> bit of the binary representation of 
	 * <code>number</code>.
	 */
	private boolean getBit(int number, int bitIndex){
		return ((number & (1 << bitIndex)) != 0);
	}


	//-------------------------------------------------------------------------


	@Test
	public void serializeDeserializeUserAndCheckAdFilter1() throws Exception{
		// AdFilter is a functional interface.
		// Therefore, we can use a lambda expression as an implementation of an AdFilter.
		IUser.AdFilter f = (x, y) -> x && y;
		assertEquals(f, serializeDeserialize(createTestUser(f)).getAdFilter());
	}

	@Test
	public void serializeDeserializeUserAndCheckAdFilter2() throws Exception{
		IUser.AdFilter f = (x, y) -> x || y;
		assertEquals(f, serializeDeserialize(createTestUser(f)).getAdFilter());
	}


	@Test
	public void serializeDeserializeUserAndCheckAdFilter3() throws Exception{
		IUser.AdFilter f = (x, y) -> true;
		assertEquals(f, serializeDeserialize(createTestUser(f)).getAdFilter());
	}

	@Test
	public void serializeDeserializeUserAndCheckAdFilter4() throws Exception{
		IUser.AdFilter f = (x, y) -> x ^ y;
		assertEquals(f, serializeDeserialize(createTestUser(f)).getAdFilter());
	}

	@Test
	public void serializeDeserializeUserAndCheckAdFilter5() throws Exception{
		IUser.AdFilter f = (x, y) -> x && (! y);
		assertEquals(f, serializeDeserialize(createTestUser(f)).getAdFilter());
	}

	@Test
	public void serializeDeserializeUserAndCheckAdFilter6() throws Exception{
		IUser.AdFilter f = (x, y) -> x;
		assertEquals(f, serializeDeserialize(createTestUser(f)).getAdFilter());
	}
	
	@Test
	public void serializeDeserializeUserAndCheckAdFilter7() throws Exception{
		// Generate 16 different ad filters, and test each one ...
		for (int i = 0; i < 16; i++) {
			IUser.AdFilter f = generateAdFilter(i);
			assertEquals(f, serializeDeserialize(createTestUser(f)).getAdFilter());
		}	
	}

}
