package org.com.api.jmeter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) throws IOException {
		APITestRun obj = null;
		File input = new File(args[0]);

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
			System.exit(0);
		}
		else{
			System.out.println("File not found");
			//				for(int runcounter=0; runcounter<4;runcounter++)
			//				{
			//				PostReqCreate runObj = new PostReqCreate();
			//				runObj.Runner();
			//				}
							PutrequestEdit runObj = new PutrequestEdit();
							runObj.Runner();
		}
		System.exit(1);
	}

}

