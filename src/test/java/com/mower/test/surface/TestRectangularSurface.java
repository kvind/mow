package com.mower.test.surface;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mower.coordinate.Position;
import com.mower.surface.RectangularSurface;
import com.mower.surface.Surface;

public class TestRectangularSurface {
	
	Surface rectangularSurface;
	
	@Before
	public void init() {
		rectangularSurface = new RectangularSurface(new Position(5,5));
	}
	
	@Test
	public void testInitialize() {
		Assert.assertTrue(rectangularSurface.initializedPositionOnSurface(new Position(3,3)));
		Assert.assertFalse(rectangularSurface.initializedPositionOnSurface(new Position(3,3)));
		Assert.assertFalse(rectangularSurface.initializedPositionOnSurface(new Position(6,3)));
	}
	
	@Test
	public void testIsOutOfBound() {
		Assert.assertTrue(rectangularSurface.isOutOfBound(new Position(6,6)));
		Assert.assertTrue(rectangularSurface.isOutOfBound(new Position(4,6)));
		Assert.assertTrue(rectangularSurface.isOutOfBound(new Position(6,2)));
		Assert.assertFalse(rectangularSurface.isOutOfBound(new Position(3,1)));
	}
	
	@Test
	public void testUnauthorizedPosition() {
		rectangularSurface.addUnauthorizedPosition(new Position(3,3));
		Assert.assertFalse(rectangularSurface.isAuthorizedPosition(new Position(3,3)));
		Assert.assertTrue(rectangularSurface.isAuthorizedPosition(new Position(4,3)));
	}
	
	@Test
	public void testRemoveUnauthorizedPosition() {
		rectangularSurface.addUnauthorizedPosition(new Position(3,3));
		rectangularSurface.removeUnauthorizedPosition(new Position(3,3));
		Assert.assertTrue(rectangularSurface.isAuthorizedPosition(new Position(3,3)));
	}

}
