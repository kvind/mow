package com.mower.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mower.coordinate.Position;
import com.mower.enums.direction.CardinalDirection;
import com.mower.enums.direction.Direction;

public class FileParser implements Parser {
	
	private final Logger logger;
	
	/**
	 * the file path where surface and mower information are stored
	 */
	private final String filePath;
	
	/**
	 * The list representing the number of line read.
	 */
	private final List<String> linesInFile;
	
	/**
	 * the map of information generated about mowers.
	 */
	private Map<Position, Entry<String,Direction>> mowerGenerated;
	
	/**
	 * The extracted information about the surface, ie : the top right position.
	 */
	private Position extractedSurface;
	
	/**
	 * The pattern to extract Mower info
	 */
	private final static String positionPattern = "(\\d+) (\\d+) ([NEWS])";
	
	/**
	 * The instruction sequence pattern
	 */
	private final static String instructionPattern = "[GDA]+";
	
	/**
	 * 
	 * @param filePath {@link String} the file path of the file to parse.
	 */
	public FileParser(final String filePath) {
		this.filePath = filePath;
		this.linesInFile = new ArrayList<String>();
		this.mowerGenerated = new LinkedHashMap<>();
		this.logger = LogManager.getLogger(this.getClass());
		this.logger.info("Initialized "+this.getClass().getSimpleName());
	}
	
	@Override
	public boolean init() {
		try (Stream<String> streamOfLine = Files.lines(Paths.get(filePath))){
			streamOfLine.forEach(line -> this.linesInFile.add(line));
			this.logger.debug("Correctly read the file and add "+ this.linesInFile.size() + " lines to the parser.");
			return true;
		} catch (FileNotFoundException e) {
			this.logger.error("The file was not found !", e);
			return false;
		} catch (IOException e) {
			this.logger.error("Something wrong happened when reading the file", e);
			return false;
		}
	}

	@Override
	public boolean parse() {
		return this.extractSurface() && this.extractMowerInfo();
	}
	
	private boolean extractSurface() {
		final String surfacePattern = "(\\d+) (\\d+)";
		Pattern surfaceMatcher = Pattern.compile(surfacePattern);
		Matcher m = surfaceMatcher.matcher(linesInFile.get(0));
		if (m.find()) {
			this.extractedSurface = new Position(Integer.valueOf(m.group(1)),Integer.valueOf(m.group(2)));
			return true;
		}
		this.logger.error("Did not find digits to initialize the surface!");
		return false;
	}
	
	private boolean extractMowerInfo() {
		final List<String> mowerLinesInformation = linesInFile.subList(1, linesInFile.size());
		
		if ( !(mowerLinesInformation.size() % 2 == 0)) {
			this.logger.error("A line is missing, mower position and instructions have to form a pair");
			return false;
		}
		final Pattern positionMatcher = Pattern.compile(positionPattern);
		final Pattern instructionMatcher = Pattern.compile(instructionPattern);
		Matcher positionFound, instructionFound;
		final Iterator<String> mowerLinesIterator = mowerLinesInformation.iterator();
		while(mowerLinesIterator.hasNext()) {
			final String mowerPosition = mowerLinesIterator.next();
			positionFound = positionMatcher.matcher(mowerPosition);
			final String mowerInstructions = mowerLinesIterator.next();
			instructionFound = instructionMatcher.matcher(mowerInstructions);
			if (!(instructionFound.find() && positionFound.find())) {
				this.logger.error("Error while reading those lines : <"+mowerPosition+">" + "with " + "<" +mowerInstructions+ ">");
				return false;
			}
			this.mowerGenerated
			.put(new Position(Integer.valueOf(positionFound.group(1)), Integer.valueOf(positionFound.group(2))),
					new AbstractMap.SimpleEntry<String, Direction>(instructionFound.group(), 
							CardinalDirection.getDirectionFromString(positionFound.group(3))));
		}
		return true;
	}

	@Override
	public Map<Position, Entry<String, Direction>> getMowerInformation() {
		return this.mowerGenerated;
	}

	@Override
	public Position getExtractedSurface() {
		return this.extractedSurface;
	}

	@Override
	public String toString() {
		return "FileParser [filePath=" + filePath + "]";
	}
	
	

}
