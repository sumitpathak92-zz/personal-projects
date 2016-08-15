import java.util.*;
import java.io.*;

public class ReplaceWithLargestRight {
    static void nextGreatest(int array[]) {
        int l = array.length;
        int max=array[l-1];
        array[l-1]=-1;
        for(int i=l-2; i>=0; i--) {
            int temp=array[i];
            array[i]=max;
            if(max<temp) {
                max=temp;
            }
        }
    }

    static void printArray(int array[]) {
        for(int i=0; i<array.length;i++) {
            System.out.println(array[i]+" ");
        }
    }

    public static void main(String[] args) {
        int arr[] = {6, 17, 34, 33, 15, 02};
        nextGreatest(arr);
        System.out.println("new array is");
        printArray(arr);
    }
}
