public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(num, target, 0, 0, 0, sb, res);
        return res;
    }

    private void dfs(String num, int target, int index, int pre, int val, StringBuilder sb, List<String> res) {
        if (index == num.length()) {
            if (val == target) {
                res.add(sb.toString());
            }
            return;
        }
        int len = sb.length();
        for (int i = index; i < num.length(); i++) {
            if (i > index && num.charAt(index) == '0') {
                break;
            }
            String str = num.substring(index, i + 1);
            long cur = Long.valueOf(str);
            if (sb.length() == 0) {
                dfs(num, target, i + 1, cur, cur, sb.append(cur), res);
                sb.setLength(len);
            } else {
                dfs(num, target, i + 1, cur, val + cur, sb.append('+').append(cur), res);
                sb.setLength(len);
                dfs(num, target, i + 1, -cur, val - cur, sb.append('-').append(cur), res);
                sb.setLength(len);
                dfs(num, target, i + 1, pre * cur, val - pre + pre * cur, sb.append('*').append(cur), res);
                sb.setLength(len);
            }
        }
    }
    // time: O(4^(n-1)) * O(n) toString space: O(n)
}
