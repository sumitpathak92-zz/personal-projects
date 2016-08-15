import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;

public static int tc;
public static int n, m;
public String input;
public static void public static void main(String[] args) {
	tc = Scanner.nextInt();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st=  new StringTokenizer(br.readLine());
	n = Integer.parseInt(st.nextToken());
	m = Integer.parseInt(st.nextToken());

	for (int i=0; i<n; i++)
	{
		input = br.readLine();
		int lenOfLine = m;
		int[] output = new int[lenOfLine];
		int latestPrevWhite = 0;
		for(int j=0; j<lenOfLine; j++)
		{	if(Integer.parseInt(input[j]) == 1)
				posOfPrevWhite = j;
				latestPrevWhite = posOfPrevWhite;
			else
				posOfPrevWhite = latestPrevWhite;
			output[j] = Math.min(returnDistFrmPreviousWhite(j, posOfPrevWhite), distFrmNxtWhite(i, lenOfLine));
		}
		System.out.println(output[0]);
	}
}

public static int returnDistFrmPreviousWhite(int currIndx, int posOfPrevWhite) {
	if (currIndx == 0)
		return 0;
	return (currIndx - posOfPrevWhite);
}

public static int distFrmNxtWhite(int currIndx, int lenOfLine)
{
	if (currIndx == lenOfLine) return 0;
	for(int i=currIndx+1; i<lenOfLine;i++)
		{ 
			if(input[i] == 1) {
				break;
			}
		}
	return (i-currIndx); 
}