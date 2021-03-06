package com.mower.enums.instruction;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mower.coordinate.Position;
import com.mower.enums.direction.Direction;

public enum BasicInstruction {

	LEFT("G") {
		@Override
		public Entry<Direction, Position> applyInstruction(Direction direction, Position position) {
			return new AbstractMap.SimpleEntry<Direction, Position>(direction.turnLeft(), position);
		}
	},
	RIGHT("D") {
		@Override
		public Entry<Direction, Position> applyInstruction(Direction direction, Position position) {
			return new AbstractMap.SimpleEntry<Direction, Position>(direction.turnRight(), position);
		}
	},
	FORWARD("A") {
		@Override
		public Entry<Direction, Position> applyInstruction(Direction direction, Position position) {
			return new AbstractMap.SimpleEntry<Direction, Position>(direction, direction.moveForward(position));

		}
	};

	private final String instruction;

	private BasicInstruction(final String instruction) {
		this.instruction = instruction;
	}
	
	/**
	 * 
	 * @return the symbol representing the instruction.
	 */
	public String getInstruction() {
		return this.instruction;
	}

	private static final Map<String, BasicInstruction> stringToEnum = Stream.of(values())
			.collect(Collectors.toMap(BasicInstruction::getInstruction, e -> e));
	
	/**
	 * 
	 * @param symbol
	 * @return an Optional with the instruction or empty.
	 */
	public static Optional<BasicInstruction> getInstructionFromString(String symbol) {
		return Optional.ofNullable(stringToEnum.get(symbol));
	}
	
	/**
	 * Apply an instruction to modify direction or position given.
	 * @param direction
	 * @param position
	 * @return The new calculated instruction after applying the instruction.
	 */
	public abstract Entry<Direction, Position> applyInstruction(Direction direction, Position position);

}
