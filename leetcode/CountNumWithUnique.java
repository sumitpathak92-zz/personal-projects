import java.util.*;
import java.io.*;

public class CountNumWithUnique {
    
    public static void main(String[] args) {
        int number = 3;
        int finalRes = countNumbersWithUniqueDigits(number);
        System.out.println("Numbers with unique digit are " +finalRes);
    }

    public static int countNumbersWithUniqueDigits(int n) {
        if(n==0)
            return 1;
        int res = 10;
        int unqDig = 9;
        int avgNum = 9;
        while(n--> 1 && avgNum>0) {
            unqDig = unqDig*avgNum;
            res+=unqDig;
            avgNum --;
        }
        return res;
  }
}
