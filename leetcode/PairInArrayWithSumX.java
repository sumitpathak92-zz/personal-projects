import java.io.*;

public class PairInArrayWithSumX {
    private static final int MAX = 100000;

    static void printpairs(int arr[], int sum) {
        boolean[] binmap = new boolean[MAX];

        for(int i=0; i<arr.length; i++) {
            int temp = sum - arr[i];
            //checking for condition
            if(temp>0 && binmap[temp]) {
                System.out.println("Pair is " +arr[i] + ", " +temp);
            }
            binmap[arr[i]] = true;
        }
    }

    public static void main(String[] args) {
        int A[] = {1, 4, 45, 6, 10, 8};
        int n = 16;
        printpairs(A, n);
    }
}
