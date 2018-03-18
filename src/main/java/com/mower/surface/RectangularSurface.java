package com.mower.surface;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mower.coordinate.Position;

public class RectangularSurface implements Surface {
	
	/**
	 * the top right position of this surface
	 */
	private final Position topRightPosition;
	
	/**
	 * A set of occupied position for the surface
	 */
	private Set<Position> unauthorizedPosition;

	private final Logger logger = LogManager.getLogger(this.getClass());
	
	/**
	 * Initialize the Rectangular surface by setting the top right position.
	 * 
	 * @param topPositionRight {@link Position} 
	 */
	public RectangularSurface(Position topPositionRight) {
		this.topRightPosition = topPositionRight;
		this.unauthorizedPosition = new HashSet<>();
		this.logger.info("Initialized " + this.getClass().getSimpleName() + " " + toString());
	}
	
	@Override
	public boolean initializedPositionOnSurface(Position position) {
		if (!isOutOfBound(position) && isAuthorizedPosition(position)) {
			addUnauthorizedPosition(position);
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Surface at " + position.toString() + " is now occupied.");
			}
			return true;
		} else {
			this.logger.warn("Position " + position.toString() + " is out of bound or already taken.");;
			return false;
		}
		
	}
	
	@Override
	public boolean isOutOfBound(Position position) {
		return position.getX() > topRightPosition.getX()
				|| position.getY() > topRightPosition.getY() && (position.getY() >= 0 && position.getX() >= 0);
	}

	@Override
	public boolean isAuthorizedPosition(Position position) {
		return !this.unauthorizedPosition.contains(position);
	}

	@Override
	public void addUnauthorizedPosition(Position position) {
		this.unauthorizedPosition.add(position);
	}

	@Override
	public void removeUnauthorizedPosition(Position position) {
		this.unauthorizedPosition.remove(position);
	}

	@Override
	public String toString() {
		return "Size " + topRightPosition;
	}

}
