import java.util.HashMap;
import java.util.Map;

public class StreamOfCharacters {
    Node root;
    StringBuilder sb;
    public StreamOfCharacters(String[] words) {// time: O(m * n) space: O(m * n)
        root = new Node();
        sb = new StringBuilder();
        for (String word : words) {// O(words.length)
            insert(word);// O(word.length)
        }
    }
    
    public boolean query(char letter) {// O(max_word.length)
        sb.append(letter);
        Node cur = root;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (cur.children.get(c) == null) {
                return false;
            }
            cur = cur.children.get(c);
            if (cur.isWord) {
                return true;
            }
        }
        return false;
    }
    
    private void insert(String word) {
        Node cur = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            cur.children.putIfAbsent(c, new Node());
            cur = cur.children.get(c);
        }
        cur.isWord = true;
    }

    class Node {
        Map<Character, Node> children;
        boolean isWord;
        public Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }
}
