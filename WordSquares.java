import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSquares {
    // M1: dfs backtracking - time: O(26^L * (n * L)) space: O(L ^ L)
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        dfs(words, 0, words[0].length(), cur, res);
        return res;
    }

    private void dfs(String[] words, int index, int n, List<String> cur, List<List<String>> res) {
        if (index == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {// O(L) time, O(L) space
            sb.append(cur.get(i).charAt(index));
        }
        String prev = sb.toString();// O(L)
        for (String word : words) {// O(n)
            if (word.startsWith(prev)) {// O(L) startsWith
                cur.add(word);
                dfs(words, index + 1, n, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // M2: dfs backtracking + hashMap - time: O(26^L) + O(L * L) space: O(n * L! * L)
    public List<List<String>> wordSquares2(String[] words) {
        Map<String, List<String>> map = new HashMap<>();
        buildPrefixMap(words, map);
        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        dfs(words, 0, words[0].length(), map, cur, res);
        return res;
    }
    
    private void dfs(String[] words, int index, int n, Map<String, List<String>> map, List<String> cur, List<List<String>> res) {
        if (index == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {// O(L) time, O(L) space
            sb.append(cur.get(i).charAt(index));
        }
        String prev = sb.toString();// O(L)
        if (map.get(prev) != null) {
            for (String word : map.get(prev)) {
                cur.add(word);
                dfs(words, index + 1, n, map, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    private void buildPrefixMap(String[] words, Map<String, List<String>> map) {
        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                String cur = word.substring(0, i);
                map.putIfAbsent(cur, new ArrayList<>());
                map.get(cur).add(word);
            }
        }
    }

    // M3: dfs backtracking + trie - time: O(n * L) + O(26^L * L) space: O(n * L! * L)
    class Node {
        Map<Character, Node> children;
        List<String> wordList;
        public Node() {
            children = new HashMap<>();
            wordList = new ArrayList<>();
        }
    }
    Node root;
    
    public List<List<String>> wordSquares3(String[] words) {
        root = new Node();
        for (String word : words) {
            addWord(word);
        }
        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        dfs3(words, 0, words[0].length(), cur, res);
        return res;
    }
    
    private void addWord(String word) {
        Node cur = root;
        cur.wordList.add(word);
        for (char ch : word.toCharArray()) {
            cur.children.putIfAbsent(ch, new Node());
            cur.children.get(ch).wordList.add(word);
            cur = cur.children.get(ch);
        }
    }
    
    private List<String> getWords(String prev) {
        Node cur = root;
        for (char ch : prev.toCharArray()) {
            if (cur.children.get(ch) == null) {
                return new ArrayList<>();
            }
            cur = cur.children.get(ch);
        }
        return cur.wordList;
    }
    
    private void dfs3(String[] words, int index, int n, List<String> cur, List<List<String>> res) {
        if (index == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            sb.append(cur.get(i).charAt(index));
        }
        String prev = sb.toString();
        for (String word : getWords(prev)) {
            cur.add(word);
            dfs3(words, index + 1, n, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
