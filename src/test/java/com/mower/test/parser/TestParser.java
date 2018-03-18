package com.mower.test.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mower.parser.FileParser;
import com.mower.parser.Parser;

public class TestParser {
	
	private static Parser fileParser;
	
	@Before
	public void init() {
		fileParser = new FileParser("src/test/resources/fileToTest.txt");
	}
	
	@Test
	public void initParser() {
		Assert.assertTrue(fileParser.init());
		fileParser = new FileParser("src/test/resources/imaginaryFile");
		Assert.assertFalse(fileParser.init());
	}
	
	@Test
	public void testParseMethod() {
		fileParser.init();
		Assert.assertTrue(fileParser.parse());
		
		fileParser = new FileParser("src/test/resources/fileInError1.txt");
		fileParser.init();
		Assert.assertFalse(fileParser.parse());
		
		fileParser = new FileParser("src/test/resources/fileInError2.txt");
		fileParser.init();
		Assert.assertFalse(fileParser.parse());
		
	}

}
