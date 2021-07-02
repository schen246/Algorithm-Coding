import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    // M1: dfs backtracking - time: O(n!) space: O(n)
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] nums = new int[n];
        dfs(n, 0, nums, res);
        return res;
    }

    private void dfs(int n, int index, int[] nums, List<List<String>> res) {
        if (index == n) {
            List<String> ans = new ArrayList<>();
            char[] arr = new char[n];
            Arrays.fill(arr, '.');
            for (int num : nums) {
                arr[num] = 'Q';
                ans.add(new String(arr));
                arr[num] = '.';
            }
            res.add(ans);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(index, i, nums)) {
                nums[index] = i;
                dfs(n, index + 1, nums, res);
            }
        }
    }

    private boolean isValid(int row, int col, int[] nums) {
        for (int i = 0; i < row; i++) {
            if (nums[i] == col || Math.abs(row - i) == Math.abs(col - nums[i])) {
                return false;
            }
        }
        return true;
    }
}
