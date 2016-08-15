import java.util.HashMap;
import java.util.Map;

public class Trie {
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    /*
     *Iterative impl of insert into trie
     */

    public void insert(String word) {
        TrieNode current = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    /*
     *recursive version of insert into trie
     */

    public void insertRecursive(String word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode current, String word, int index) {
        if(index == word.length()) {
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if(node==null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertRecursive(node, word, index+1);
    }

    /*
     * Iterative version of search into Trie
     */

    public boolean search(String word) {
        TrieNode current = root;
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            TreeNode node = current.children.get(ch);
            if(node==null) {
                return false;
            }
            current = node;
        }
        return current.endOfWord;
    }

    /*
     * search recursive version
     */

    public boolean searchrecursive(String word) {
        return searchrecursive(root, word, 0);
    }

    private boolean searchrecursive(TrieNode current, String word, int index) {
        if(index==word.length()) return current.endOfWord;
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if(node==null) return false;
        return searchrecursive(node, word, index+1);
    }

    /*
     * Delete word from trie
     */

    public void delete(String word) {
        delete(root, word, 0);
    }

    /*
     * return true if parent should delete the mapping
     */

    private boolean delete(TrieNode current, String word, int index) {
        if(index==word.length()) {
            if(!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            return current.children.size()==0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if(node==null) {
            return false;
        }
        boolean shoulddeleteCurrentNode = delete(node, word, index+1);

        if(shoulddeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.size()==0;
        }
        return false;
    }
}
