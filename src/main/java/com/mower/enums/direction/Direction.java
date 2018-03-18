package com.mower.enums.direction;

import com.mower.coordinate.Position;

public interface Direction {

	public Position moveForward(Position position);

	public Direction turnRight();

	public Direction turnLeft();

}
