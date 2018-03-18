package com.mower.mower;

import java.util.Map.Entry;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mower.coordinate.Position;
import com.mower.enums.direction.Direction;
import com.mower.enums.instruction.BasicInstruction;
import com.mower.surface.Surface;

public class Mower {

	/**
	 * The id of the mower.
	 */
	private final int id;

	/**
	 * the position of the mower.
	 */
	private Position position;

	/**
	 * The direction the mower is pointed on.
	 */
	private Direction direction;

	/**
	 * The sequence of instruction.
	 */
	private final String instructions;

	/**
	 * The logger of the mower.
	 */
	private final Logger logger;

	/**
	 * Construct a new mower.
	 * 
	 * @param id
	 *            The id representing the mower
	 * @param position
	 *            {@link Position} the initial mower position.
	 * @param direction
	 *            {@link Direction} the initial mower orientation.
	 * @param instructions
	 *            {@link BasicInstruction} the sequence of instruction for the
	 *            mower.
	 */
	public Mower(final int id, final Position position, final Direction direction, final String instructions) {
		this.id = id;
		this.setPosition(position);
		this.setDirection(direction);
		this.instructions = instructions;
		this.logger = LogManager.getLogger(this.getClass());
		this.logger.info("Initialized " + this.toString());
	}

	/**
	 * Move the mower using the list of instruction
	 * 
	 * @param currentSurface
	 *            the surface where the mower is moving
	 * @return the final position of the mower
	 */
	public Position move(final Surface currentSurface) {

		currentSurface.removeUnauthorizedPosition(this.position);

		for (final char instruction : instructions.toCharArray()) {
			Optional<BasicInstruction> currentInstruction = BasicInstruction
					.getInstructionFromString(String.valueOf(instruction));

			if (currentInstruction.isPresent()) {
				if (logger.isDebugEnabled()) {
					this.logger.debug("Receiving instruction " + currentInstruction.get());
				}
				final Entry<Direction, Position> updatedMowerState = currentInstruction.get()
						.applyInstruction(this.getDirection(), this.getPosition());
				this.setDirection(updatedMowerState.getKey());
				this.setNewCalculatedPosition(updatedMowerState.getValue(), currentSurface);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(toString());
				}
			} else {
				this.logger.warn(
						"Instruction " + instruction + " does not match with any kind of instruction -> Skipped.");
			}
		}

		currentSurface.addUnauthorizedPosition(this.position);

		return this.position;
	}

	private void setNewCalculatedPosition(final Position newCalculatedPosition, final Surface currentSurface) {
		if (!currentSurface.isOutOfBound(newCalculatedPosition)) {
			if (currentSurface.isAuthorizedPosition(newCalculatedPosition)) {
				this.setPosition(newCalculatedPosition);
			} else {
				this.logger.warn(newCalculatedPosition.toString() +" is occupied. Skip the instruction.");
			}
		} else {
			this.logger.warn(newCalculatedPosition.toString() + " is out of bound. Skip the instruction");
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the instruction
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * toString method, if called it will display for example Mower 1 [x=1, y=2]
	 * NORTH
	 */
	@Override
	public String toString() {
		return "Mower " + this.id + " " + position + " " + direction;
	}

}
