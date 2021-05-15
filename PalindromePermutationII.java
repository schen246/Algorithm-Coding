public class PalindromePermutationII {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if ((map.get(c) & 1) == 1) {
                cnt++;
            } else {
                cnt--;
            }
        }
        if (cnt > 1) {
            return res;
        }
        // find all permutations - str + ch + str
        char[] arr = new char[s.length() / 2];
        char ch = 0;
        int k = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if ((val & 1) == 1) {
                ch = key;
            }
            for (int i = 0; i < val / 2; i++) {
                arr[k++] = key;
            }
        }
        dfs(arr, 0, res, ch);
        return res;
    }

    private void dfs(char[] arr, int index, List<String> res, char ch) {
        if (index == arr.length) {
            String cur = new String(arr);
            res.add(cur + (ch == 0 ? "" : ch) + new StringBuilder(cur).reverse().toString());
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (set.add(arr[i])) {
                swap(arr, i, index);
                dfs(arr, index + 1, res, ch);
                swap(arr, i, index);
            }
        }
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    // time: O((n/2)! * n) space: O(n/2)
}
