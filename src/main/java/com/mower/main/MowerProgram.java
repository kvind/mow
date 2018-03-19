package com.mower.main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mower.controller.MowerController;
import com.mower.parser.FileParser;
import com.mower.parser.Parser;

public class MowerProgram {

	static Logger logger = LogManager.getLogger(MowerProgram.class);

	public static void main(String[] args) {

		logger.info("----- WELCOME IN THE MOWER PROGRAM -----");
		
		
		if (args.length > 0) {
			Parser fileParser = new FileParser(args[0]);
			if (fileParser.init() && fileParser.parse()) {
				MowerController mowerController = new MowerController(fileParser.getExtractedSurface(),
						fileParser.getMowerInformation());
				
				mowerController.initMowersOnSurface();
				
				mowerController.moveMowers();
			}
		} else {
			logger.error("No file was given. Stopping the program.");
		}

	}

}
