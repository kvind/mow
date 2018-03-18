package com.mower.enums.direction;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mower.coordinate.Position;

public enum CardinalDirection implements Direction {

	NORTH("N") {
		@Override
		public Position moveForward(Position position) {
			return new Position(position.getX(), position.getY() + 1);
		}

		@Override
		public CardinalDirection turnRight() {
			return EAST;
		}

		@Override
		public CardinalDirection turnLeft() {
			return WEST;
		}
	},
	EAST("E") {
		@Override
		public Position moveForward(Position position) {
			return new Position(position.getX() + 1, position.getY());
		}

		@Override
		public CardinalDirection turnRight() {
			return SOUTH;
		}

		@Override
		public CardinalDirection turnLeft() {
			return NORTH;
		}
	},
	WEST("W") {
		@Override
		public Position moveForward(Position position) {
			return new Position(position.getX() - 1, position.getY());
		}

		@Override
		public CardinalDirection turnRight() {
			return NORTH;
		}

		@Override
		public CardinalDirection turnLeft() {
			return SOUTH;
		}
	},
	SOUTH("S") {
		@Override
		public Position moveForward(Position position) {
			return new Position(position.getX(), position.getY() - 1);
		}

		@Override
		public CardinalDirection turnRight() {
			return WEST;
		}

		@Override
		public CardinalDirection turnLeft() {
			return EAST;
		}
	};

	private final String direction;

	private CardinalDirection(String cardinalDirection) {
		this.direction = cardinalDirection;
	}

	private static final Map<String, CardinalDirection> stringToEnum = Stream.of(values())
			.collect(Collectors.toMap(CardinalDirection::getDirection, e -> e));

	public static CardinalDirection getDirectionFromString(String symbol) {
		return stringToEnum.get(symbol);
	}

	public String getDirection() {
		return this.direction;
	}

}
