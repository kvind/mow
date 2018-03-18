package com.mower.parser;

import java.util.Map;
import java.util.Map.Entry;

import com.mower.coordinate.Position;
import com.mower.enums.direction.Direction;

public interface Parser {
	
	/**
	 * read the parameters given to to the constructor
	 * @return true if no errors has been detected.
	 */
	boolean init();
	
	/**
	 * read the content of what have been read 
	 * @return true if parse was ok.
	 */
	boolean parse();
	
	/**
	 * @return a map with position as key to initialize only one mower per position.
	 */
	Map<Position, Entry<String, Direction>> getMowerInformation();
	
	/**
	 * @return a position representing the top right coordinate of the surface.
	 */
	Position getExtractedSurface();

}
