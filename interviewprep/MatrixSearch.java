import java.util.*;
import java.io.*;

public class MatrixSearch {
    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        List<Integer> finalList = new ArrayList<Integer>();
        for(List<Integer> innerList: a) {
            for(Integer number : innerList) {
                finalList.add(number);
            }
        }
        int[] finalArray = new int[finalList.size()];
        for(int i=0;i<finalArray.length; i++){
            finalArray[i]=finalList.get(i);
        }
        // binary search starts here
        int start = 0, end = finalArray.length-1, mid = start + (end-start)/2;
        while(start<=end){
            if(finalArray[mid]==b){
                return 1;
            }
            else if(b<finalArray[mid]){
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        return 0;
    }

}
