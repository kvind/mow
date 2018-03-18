package com.mower.test.enums;

import org.junit.Test;

import com.mower.coordinate.Position;
import com.mower.enums.direction.CardinalDirection;

import org.junit.Assert;

public class TestCardinalDirection {
	
	Position position = new Position(3,3);
	
	@Test
	public void testGetEnumFromString() {
		Assert.assertTrue(CardinalDirection.EAST.equals(CardinalDirection.getDirectionFromString("E")));
		Assert.assertTrue(CardinalDirection.SOUTH.equals(CardinalDirection.getDirectionFromString("S")));
		Assert.assertTrue(CardinalDirection.NORTH.equals(CardinalDirection.getDirectionFromString("N")));
		Assert.assertTrue(CardinalDirection.WEST.equals(CardinalDirection.getDirectionFromString("W")));
	}
	
	@Test
	public void testTurnRight() {
		Assert.assertTrue(CardinalDirection.EAST.equals(CardinalDirection.getDirectionFromString("N").turnRight()));
		Assert.assertTrue(CardinalDirection.WEST.equals(CardinalDirection.getDirectionFromString("S").turnRight()));
		Assert.assertTrue(CardinalDirection.NORTH.equals(CardinalDirection.getDirectionFromString("W").turnRight()));
		Assert.assertTrue(CardinalDirection.SOUTH.equals(CardinalDirection.getDirectionFromString("E").turnRight()));
	}
	
	@Test
	public void testTurnLeft() {
		Assert.assertTrue(CardinalDirection.EAST.equals(CardinalDirection.getDirectionFromString("S").turnLeft()));
		Assert.assertTrue(CardinalDirection.WEST.equals(CardinalDirection.getDirectionFromString("N").turnLeft()));
		Assert.assertTrue(CardinalDirection.NORTH.equals(CardinalDirection.getDirectionFromString("E").turnLeft()));
		Assert.assertTrue(CardinalDirection.SOUTH.equals(CardinalDirection.getDirectionFromString("W").turnLeft()));
	}
	
	@Test
	public void testMoveForward() {
		Assert.assertTrue(CardinalDirection.EAST.moveForward(position).equals(new Position(4,3)));
		Assert.assertTrue(CardinalDirection.NORTH.moveForward(position).equals(new Position(3,4)));
		Assert.assertTrue(CardinalDirection.SOUTH.moveForward(position).equals(new Position(3,2)));
		Assert.assertTrue(CardinalDirection.WEST.moveForward(position).equals(new Position(2,3)));
	}

}
