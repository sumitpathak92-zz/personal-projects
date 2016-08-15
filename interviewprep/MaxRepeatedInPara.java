import java.util.*;
import java.io.*;

public class MaxRepeatedInPara {
    public static void main(String[] args) {
        Map<String, Integer> wordsWithCount = constructMap("");
        List<Entry<String, Integer>> list = sortedmapbyvalue(wordsWithCount);
        System.out.println("Max occuring word is ");
        for (Map.Entry<String, Integer> entries : list) {
            System.out.println(entries.getValue());
            break; 
            }
        }

    public static Map<String, Integer> constructMap(String nameOfFile) {
        Map<String, Integer> wordsWithCount = new HashMap<>();
        try(FileInputStream fs = new FileInputStream(nameOfFile);
                DataInputStream ds = new DataInputStream(fs);
                BufferedReader br = new BufferedReader(new InputStreamReader(ds))) {
                    Pattern p = Pattern.compile("\\s+");
                    String line = null;
                    while((line=br.readLine())!=null) {
                        line= line.toLowerCase();
                        String[] words = p.split(line);
                        for(String word: words) {
                            if(wordsWithCount.containsKey(word)) {
                                wordsWithCount.put(word, (wordsWithCount.get(word)+1));
                            }
                            else {
                                wordsWithCount.put(word, 1);
                            }
                        }
                    }
                        
                } catch(IOException exc) {
                    System.out.println(exc);
                }
        return wordsWithCount;
    }

    public static List<Entry<String, Integer>> sortedmapbyvalue(Map<String, Integer>) {
        Set<Entry<String, Integer>> entries = wordsWithCount.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()) {
            @override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer>)
            {
                return (o2.getValue())
            }
        }
    }

    }

