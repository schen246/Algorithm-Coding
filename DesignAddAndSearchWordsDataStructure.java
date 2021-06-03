import java.util.HashMap;

public class DesignAddAndSearchWordsDataStructure {
    Node root;

    public DesignAddAndSearchWordsDataStructure() {
        root = new Node();
    }

    public void addWord(String word) {// time: O(word.length())
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

    public boolean search(String word) {// time: O(word.length() + cnt(".") * 26)
        char[] arr = word.toCharArray();
        return dfs(arr, 0, root);
    }

    private boolean dfs(char[] arr, int index, Node node) {
        if (index == arr.length) {
            return node.isWord;
        }
        // "."
        if (arr[index] == '.') {
            for (Node nei : node.children.values()) {
                if (dfs(arr, index + 1, nei)) {
                    return true;
                }
            }
            return false;
        }
        // lower case english letter
        if (!node.children.containsKey(arr[index])) {
            return false;
        }
        return dfs(arr, index + 1, node.children.get(arr[index]));
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