package se.tullverket.userprojects.cmtest.helloworld;

public class HelloWorld {
	
	private String phrase = "Ha en bra dag, ";
	
	public String getGreeting(String name) {
		String greeting = phrase + name;
		return greeting;
	}
	
}