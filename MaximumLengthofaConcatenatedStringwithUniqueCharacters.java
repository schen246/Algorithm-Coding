import java.util.List;

public class MaximumLengthofaConcatenatedStringwithUniqueCharacters {
    // dfs backtracking + bit - time: O(2^n & n) space: O(n)
    public int maxLength(List<String> arr) {
        int[] res = new int[1];
        StringBuilder sb = new StringBuilder();
        dfs(arr, 0, "", res);
        return res[0];
    }
    
    private void dfs(List<String> arr, int index, String cur, int[] res) {
        if (index == arr.size()) {
            if (isValid(cur) && cur.length() > res[0]) {
                res[0] = cur.length();
            }
            return;
        }
        dfs(arr, index + 1, cur + arr.get(index), res);
        dfs(arr, index + 1, cur, res);
    }
    
    private boolean isValid(String cur) {
        int res = 0;
        for (int i = 0; i < cur.length(); i++) {
            char ch = cur.charAt(i);
            if (((res >> (ch - 'a')) & 1) == 1) {
                return false;
            }
            res |= (1 << (ch - 'a'));
        }
        return true;
    }
}
