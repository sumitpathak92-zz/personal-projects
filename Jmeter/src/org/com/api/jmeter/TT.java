package org.com.api.jmeter;

public class TT {
	public static void main(String args[]) {

		Test T1 = new Test( "API-1");
		Test T2 = new Test( "API-2");
		
		T1.start();
		T2.start();
	}   
}