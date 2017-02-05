import java.util.*;
import java.io.*;

public class ReverseString {
    //static String str = "hellosumit";
    //String result = reversedStr("hellosumit");
    //System.out.println("Reversed String is " +result);

    public static void main(String[] args) {
        
        String result = reversedStr("hellosumit");
        System.out.println("Reversed String is " +result);
    }

    public static String reversedStr(String string) {
        if (string.length()==0 || string.length()==1)
            return string;
        Stack<Character> st = new Stack<Character>();
        for(int i=0; i<string.length(); i++){
            st.push(string.charAt(i));
        }
        String res = "";
        while(!st.isEmpty()) {
            res = res+st.pop();
        }
        return res;
    }
}
