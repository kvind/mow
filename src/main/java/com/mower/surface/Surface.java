package com.mower.surface;

import com.mower.coordinate.Position;

public interface Surface {
	
	/**
	 * Set the mower initial position on the surface.
	 * @param mower
	 * @return true if the mower could be added.
	 */
	boolean initializedPositionOnSurface(Position position);
		
	
	/**
	 * Check if the position is out of bound
	 * @param position
	 * @return true if it is out of bound.
	 */
	boolean isOutOfBound(Position position);
	
	/**
	 * Check if the position is not already taken by others.
	 * @param position
	 * @return true if no one is occupying this position.
	 */
	boolean isAuthorizedPosition(Position position);
	
	/**
	 * Add the position in the list of unauthorized position.
	 * @param position
	 */
	void addUnauthorizedPosition(Position position);
	
	/**
	 * Remove the position in the list of unauthorized position.
	 * @param position
	 */
	void removeUnauthorizedPosition(Position position);

}
