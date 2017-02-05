import java.util.*;
import java.io.*;
import java.util.Scanner;

public class PrettyPrint {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        getMatrix(a);
    }

    public static ArrayList<ArrayList<Integer>> getMatrix(int a) {
        ArrayList<ArrayList<Integer>> lst = new ArrayList<ArrayList<Integer>>();
        int k = 2*a-1;
        int[][] spiral = new int[k][k];
        int n=a, c1=0, c2=k-1, r1=0, r2=k-1;
        for(int i=0; i<k; i++){
            for(int j=0; j<k; j++) {
                spiral[i][j] = 0;
            }
        }

        while(n>=1) {
            for(int i=c1; i<=c2; i++) {
                spiral[r1][i] = n;
            }
            for(int j=r1+1; j<=r2; j++) {
                spiral[j][c2] = n;
            }
            for(int i=c2-1; i>=c1; i--) {
                spiral[r2][i] = n;
            }
            for(int j=r2-1; j>=r1+1; j--) {
                spiral[j][c1] = n;
            }
            c1++;
            c2--;
            r1++;
            r2--;
            n--;
        }
        //for(int i=0; i<spiral.length; i++) {
          //  for(int j=0; j<spiral[0].length; j++) {
            //    System.out.println(" " + spiral[i][j]);
           // }
            //System.out.println("");
        //}

        //converting 2-D matrix to arraylist
        
        for(int i=0; i<spiral.length; i++) {
            lst.add(new ArrayList<Integer>());
            for(int j=0; j<spiral[0].length; j++) {
                lst.get(i).add(spiral[i][j]);
            }
        }
        return lst;
    }
}
