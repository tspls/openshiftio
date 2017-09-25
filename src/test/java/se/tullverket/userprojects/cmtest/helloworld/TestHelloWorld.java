package se.tullverket.userprojects.cmtest.helloworld;

import org.junit.Assert;
import org.junit.Test;

public class TestHelloWorld {		

	@Test	
	public void testGetGreeting() {
		
		HelloWorld hw = new HelloWorld();
		String retVal = hw.getGreeting("Per");
		Assert.assertEquals(retVal, "Ha en bra dag, Per");
	}
	
}