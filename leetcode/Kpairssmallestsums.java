import java.util.*;
import java.io.*;

public class Kpairssmallestsums {
    public static List<int[]> ksmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> finalList = new ArrayList<int[]>();
        if(nums1.length == 0 || nums2.length == 0 || k==0) {
            return finalList;
        }

        int[] interimArray = new int[nums1.length];
        while(k-->0) {
            int min_val = Integer.MAX_VALUE;
            int in = -1;
            for(int i=0; i<nums1.length; i++) {
                if(interimArray[i] >= nums2.length) {
                    continue;
                }

                if(nums1[i] + nums2[interimArray[i]]<min_val) {
                    min_val = nums1[i] + nums2[interimArray[i]];
                    in = i;
                }
            }

            if(in == -1) break;
            int[] temp = {nums1[in], nums2[interimArray[in]]};
            finalList.add(temp);
            interimArray[in]++;
        }

        return finalList;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {4, 5, 6};
        int k = 3;
        ksmallestPairs(nums1, nums2, k);
    }
}
