import java.io.*;
import java.util.*;

public class longestIncrsSubsqnc {
    public static void main(String[] args) {
        int[] array={10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println(lengthOfLIS(array));
    }

    public static int lengthOfLIS(int[] nums) {
       if(nums==null || nums.length==0) return 0;
       int[] max = new int[nums.length];
       for(int i=0; i<nums.length; i++) {
            max[i]=1;
            for(int j=0; j<i;j++) {
                if(nums[i]>nums[j]) {
                    max[i]=Math.max(max[i], max[j]+1);
                }
            }
       }
       //int result=0;
       //for(int i=0; i<max.length; i++) {
       //     if(max[i]>result) {
       //         result=max[i];
       //     }
       //}
       return max[nums.length-1];
    }
}
