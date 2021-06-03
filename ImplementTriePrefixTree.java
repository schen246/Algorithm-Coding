import java.util.HashMap;

public class ImplementTriePrefixTree {
    Node root;

    public ImplementTriePrefixTree() {
        root = new Node();
    }

    public void insert(String word) {// O(word.length())
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                Node nei = new Node();
                cur.children.put(c, nei);
            }
            cur = cur.children.get(c);
        }
        cur.isWord = true;
    }

    public boolean search(Strng word) {// O(word.length)
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {// O(prefix.length())
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return true;
    }
}

class Node {
    Map<Character, Node> children;
    boolean isWord;

    public Node() {
        children = new HashMap<>();
        isWord = false;
    }
}