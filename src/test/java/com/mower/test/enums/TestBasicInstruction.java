package com.mower.test.enums;

import org.junit.Assert;
import org.junit.Test;

import com.mower.enums.instruction.BasicInstruction;

public class TestBasicInstruction {
	
	@Test
	public void testBasicInstruction() {
		Assert.assertEquals(BasicInstruction.FORWARD, BasicInstruction.getInstructionFromString("A").get());
		Assert.assertEquals(BasicInstruction.LEFT, BasicInstruction.getInstructionFromString("G").get());
		Assert.assertEquals(BasicInstruction.RIGHT, BasicInstruction.getInstructionFromString("D").get());
		Assert.assertNotEquals(BasicInstruction.FORWARD, BasicInstruction.getInstructionFromString("K"));
	}
	
}
