public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        dfs(s, 0, cur, res);
        return res;
    }
    
    private void dfs(String s, int index, List<String> cur, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                cur.add(s.substring(index, i + 1));
                dfs(s, i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
    // time: O(2^n * n) - each char two choices to create new palindrome substrings, one join with previous substring, another start a new palindrome substring
    // space: O(n)
}
