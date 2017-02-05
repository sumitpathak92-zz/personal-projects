import java.util.*;
import java.io.*;

public class CountElementOccurence{

    public static int getNoofOccurence(int[] sortedArray, int element, boolean searchFirst) {
        int lo=0, hi=sortedArray.length-1;
        int count = 0, result=-1;
        while(lo<=hi) {
            int mid = lo + (hi-lo)/2;
            if(sortedArray[mid]==element) {
                result = mid;
                if(searchFirst) {
                    hi = mid-1;
                }
                else 
                    lo = mid + 1;

            }
            else if(element<sortedArray[mid]) 
                hi = mid-1;
            else 
                lo = mid + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 2, 2, 3, 4, 4, 4, 5, 8, 9};
        int toFind = 4;
        int noOfOccurence = getNoofOccurence(array, toFind, true);
        int firstIndex = getNoofOccurence(array, toFind, true);
        int lastIndex = getNoofOccurence(array, toFind, false);
        System.out.println("No of Occurence is"+ (lastIndex - firstIndex + 1));
    }
}
