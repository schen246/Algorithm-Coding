import java.util.HashSet;
import java.util.Set;


public class AndroidUnlockPatterns {
    public int numberOfPatterns(int m, int n) {
        int[] res = new int[1];
        Set<Integer> path = new HashSet<>();
        dfs(m, n, 0, path, res);
        return res[0];
    }

    private void dfs(int m, int n, int pre, Set<Integer> path, int[] res) {
        if (path.size() >= m) {
            res[0]++;
        }
        if (path.size() == n) {
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (validPattern(i, pre, path)) {
                path.add(i);
                dfs(m, n, i, path, res);
                path.remove(i);
            }
        }
    }

    private boolean validPattern(int cur, int pre, Set<Integer> path) {
        if (path.size() == 0) {
            return true;
        }
        if (path.contains(cur)) {
            return false;
        }
        int px = (pre - 1) / 3, py = (pre - 1) % 3;
        int cx = (cur - 1) / 3, cy = (cur - 1) % 3;
        if (Math.abs(px - cx) == 2 && Math.abs(py - cy) != 1) {
            return path.contains((px + cx)/2 * 3 + (py + cy)/2 + 1);
        }
        if (Math.abs(py - cy) == 2 && Math.abs(px - cx) == 0) {
            return path.contains((px + cx)/2 * 3 + (py + cy)/2 + 1);
        }
        return true;
    }
    // time: O(9^n) space: O(n)
}
