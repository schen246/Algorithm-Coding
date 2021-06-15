import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (wordSearch(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean wordSearch(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, 0, word, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean dfs(char[][] board, int i, int j, int index, String word, boolean[][] visited) {
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
                if (dfs(board, x, y, index + 1, word, visited)) {
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
    }
    // time: O(words.length * k * 4^word.length) space: O(m * n)
}

// M2: trie + dfs time: O(V + E) = O(mn * 4^mn) space: O(trie nodes)
class Solution2 {
    Node root;
    
    public List<String> findWords(char[][] board, String[] words) {
        root = new Node();
        for (String word : words) {
            addWord(word);
        }
        int m = board.length, n = board[0].length;
        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, sb, res, root);
            }
        }
        return new ArrayList<>(res);
    }
    
    private void dfs(char[][] board, int i, int j, StringBuilder sb, Set<String> res, Node cur) {
        char c = board[i][j];
        if (c == '#') {
            return;
        }
        if (cur.children.get(c) == null) {
            return;
        }
        cur = cur.children.get(c);
        board[i][j] = '#';
        sb.append(c);
        if (cur.isWord) {
            res.add(sb.toString());
        }
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                dfs(board, x, y, sb, res, cur);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        board[i][j] = c;
    }
    
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private void addWord(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            cur.children.putIfAbsent(c, new Node());
            cur = cur.children.get(c);
        }
        cur.isWord = true;
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