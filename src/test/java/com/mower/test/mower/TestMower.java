package com.mower.test.mower;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mower.coordinate.Position;
import com.mower.enums.direction.CardinalDirection;
import com.mower.mower.Mower;
import com.mower.surface.RectangularSurface;
import com.mower.surface.Surface;

public class TestMower {

	static Mower mower1,mower2,mower3,mower4;
	static Surface surface;

	@Before
	public void init() {
		mower1 = new Mower(1, new Position(1, 2), CardinalDirection.NORTH, "AADA");
		mower2 = new Mower(2, new Position(1,4), CardinalDirection.WEST, "ADA");
		mower3 = new Mower(3, new Position(5,5), CardinalDirection.NORTH, "A");
		mower4 = new Mower(4, new Position(2,2), CardinalDirection.NORTH, "BKA");
		surface = new RectangularSurface(new Position(5,5));
	}

	@Test
	public void testMoveMower() {
		Assert.assertTrue(new Position(2, 4).equals(mower1.move(surface)));
		Assert.assertTrue(CardinalDirection.EAST.equals(mower1.getDirection()));
	}
	
	@Test
	public void testOutOfBound() {
		final Position finalPosition = mower3.move(surface);
		Assert.assertTrue(finalPosition.equals(new Position(5,5)));
	}
	
	@Test
	public void testBadInstruction() {
		final Position finalPosition = mower4.move(surface);
		Assert.assertTrue(finalPosition.equals(new Position(2,3)));
	}
	
	@Test
	public void moveUnderCollision() {
		surface.initializedPositionOnSurface(mower1.getPosition());
		surface.initializedPositionOnSurface(mower2.getPosition());
		mower1.move(surface);
		
	}

}
