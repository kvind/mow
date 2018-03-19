package com.mower.enums.direction;

import com.mower.coordinate.Position;

public interface Direction {
	
	/**
	 * Increment the position in the specific direction.
	 * @param position
	 * @return the new position
	 */
	public Position moveForward(Position position);
	
	/**
	 * Execute a turn right
	 * @return the new direction after turning right
	 */
	public Direction turnRight();
	
	/**
	 * Execute a turn left
	 * @return the new direction after turning left
	 */
	public Direction turnLeft();

}
