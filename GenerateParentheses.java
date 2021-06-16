import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    // dfs + backtracking - time: O(2^2n) space: O(2n)
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(n, n, sb, res);
        return res;
    }
    
    private void dfs(int leftRem, int rightRem, StringBuilder sb, List<String> res) {
        if (leftRem == 0 && rightRem == 0) {
            res.add(sb.toString());
            return;
        }
        if (leftRem > 0) {
            sb.append('(');
            dfs(leftRem - 1, rightRem, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (leftRem < rightRem) {
            sb.append(')');
            dfs(leftRem, rightRem - 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
