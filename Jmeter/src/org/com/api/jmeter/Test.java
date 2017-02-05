package org.com.api.jmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Test extends Thread {
	private Thread t;
	private String threadName;

	Test( String name){
		threadName = name;
		System.out.println("Creating " +  threadName );
	}
	public void run() {
		System.out.println("Running " +  threadName );
		try {
			APITestRun obj = null;
			File input = new File("URL_data.csv");
			
			if(input.exists())
			{
			  //Scanner scanner = new Scanner(new File("URL_data.csv")); //Reading from csv
				Scanner scanner = new Scanner(input); // to run from command line
				scanner.nextLine();//to skip the first line in the csv

				while (scanner.hasNextLine()) {
					String arg = "";
					String url = "";
					String apiName = "";
					String s = scanner.nextLine();
					String[] csvParse = s.split(",");
					apiName = csvParse[0]; 
					url = csvParse[1];
					for(int i=2; i< csvParse.length;i++) {
						arg = arg + "&" + csvParse[i];
					}
					if(url != null && !url.isEmpty()) {
						obj = new APITestRun(url,apiName,arg);
						obj.Runner();
					}
					else{
						System.out.println("URL for " + apiName + " API is not entered");
					}
				}
				scanner.close();
				// Let the thread sleep for a while.
//				Thread.sleep(5);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start ()
	{
		System.out.println("Starting " +  threadName );
		if (t == null)
		{
			t = new Thread (this, threadName);
			t.start ();
		}
	}

}

