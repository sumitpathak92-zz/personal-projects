import java.util.*;
import java.io.*;

public class LongestCommonPrefix {
    
    public static void main(String[] args) {
        String[] strs = {"leets", "leetcode", "leet", "leeds"};
        System.out.println("longest common prefix is "+ longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        String prefix = strs[0];
        for(int i=1; i<strs.length; i++) {
            System.out.println("string is " + strs[i]);
            while(strs[i].indexOf(prefix)!=0) {
                System.out.println("prefix "+ prefix);
                prefix = prefix.substring(0, prefix.length()-1);
                System.out.println("new prefix "+ prefix);
                if(prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}
