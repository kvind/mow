package com.mower.test.position;

import org.junit.Assert;
import org.junit.Test;

import com.mower.coordinate.Position;

public class TestPosition {
	
	static Position position1,position2,position3,position4;
	
	@Test
	public void initPosition() {
		position1 = new Position(5,5);
		position2 = new Position(5,5);
		position3 = new Position(4,5);
		position4 = new Position(5,4);
	}
	
	@Test
	public void testEquals() {
		Assert.assertTrue(position1.equals(position1));
		Assert.assertTrue(position1.equals(position2));
		Assert.assertFalse(position1.equals(position3));
		Assert.assertFalse(position1.equals(position4));
		Assert.assertFalse(position1.equals(null));
		Assert.assertFalse(position1.equals("test"));
	}
	
	@Test
	public void testHashCode() {
		Assert.assertTrue(position1.hashCode() == position2.hashCode());
		Assert.assertFalse(position1.hashCode() == position3.hashCode());
	}

}
