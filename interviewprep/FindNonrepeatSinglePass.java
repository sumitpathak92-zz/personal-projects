import java.util.*;
import java.io.*;

public class FindNonrepeatSinglePass {
    static String inputstr = "aabcadeadfa";
    public static void main(String[] args) {
        HashMap<Character, Integer> mapCharacters = new HashMap<Character, Integer>();
        for(int i=0;i<inputstr.length(); i++) {
            char c = inputstr.charAt(i);
            if(mapCharacters.containsKey(c)) {
                int cnt = mapCharacters.get(c);
                mapCharacters.put(c, ++cnt);
            }
            else {
                mapCharacters.put(c, 1);
            }
        }
        Iterator<Map.Entry<Character, Integer>> iter = mapCharacters.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Character, Integer> entry = iter.next();
            if(entry.getValue()>1) {
                iter.remove();
            }
        }
        System.out.println(mapCharacters.keySet().toArray()[0]);
    }

}
