package com.mower.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mower.coordinate.Position;
import com.mower.enums.direction.Direction;
import com.mower.mower.Mower;
import com.mower.surface.RectangularSurface;
import com.mower.surface.Surface;

public class MowerController {

	private final Surface surface;

	private final List<Mower> mowers;

	private final Map<Position, Entry<String, Direction>> allMowerInfo;

	private final Logger logger;

	public MowerController(Position surfaceSize, Map<Position, Entry<String, Direction>> mowerInfo) {
		this.surface = new RectangularSurface(surfaceSize);
		this.allMowerInfo = mowerInfo;
		this.mowers = new ArrayList<>();
		this.logger = LogManager.getLogger(this.getClass());
	}
	
	/**
	 * Initialize all mowers on the current surface.
	 */
	public void initMowersOnSurface() {
		final AtomicInteger atomic = new AtomicInteger(0);
		for (Entry<Position, Entry<String, Direction>> mowerToAdd : allMowerInfo.entrySet()) {
			final Entry<String, Direction> instructionAndDirection = mowerToAdd.getValue();
			final Mower mower = new Mower(atomic.incrementAndGet(), mowerToAdd.getKey(),
					instructionAndDirection.getValue(), instructionAndDirection.getKey());
			if (this.surface.initializedPositionOnSurface(mower.getPosition())) {
				this.mowers.add(mower);
			}
			else {
				this.logger.warn("Mower " + mower.toString() + " will not be added");
			}
		}
	}
	
	/**
	 * Simulate all mowers movement.
	 */
	public void moveMowers() {
		this.logger.info("Start moving all the mowers !");
		mowers.forEach(mower -> mower.move(this.surface));
		this.logger.info("All the mowers have moved !");
		this.logger.info(showAllMowerFinalPosition());
	}
	
	/**
	 * 
	 * @return the string representing all mowers final position
	 */
	public String showAllMowerFinalPosition() {
		final StringBuilder mowerListing = new StringBuilder();
		mowerListing.append("\n");
		for (Mower mower : mowers) {
			mowerListing.append(mower.toString());
			mowerListing.append("\n");
		}
		return mowerListing.toString();
	}

}
