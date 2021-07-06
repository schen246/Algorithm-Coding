import java.util.ArrayList;
import java.util.List;

public class _24Game {
    // dfs backtracking - time: O(n! * n) space: O(n)
    public boolean judgePoint24(int[] cards) {
        List<Double> a = new ArrayList<>();
        for (int card : cards) {
            a.add((double)card);
        }
        return dfs(a);
    }

    private boolean dfs(List<Double> a) {
        if (a.size() == 1) {
            return Math.abs(a.get(0) - 24) < 0.001;
        }
        int n = a.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<Double> b = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        b.add(a.get(k));
                    }
                }
                for (double d : compute(a.get(i), a.get(j))) {
                    b.add(d);
                    if (dfs(b)) {
                        return true;
                    }
                    b.remove(b.size() - 1);
                }
            }

        }
        return false;
    }

    private double[] compute(double x, double y) {
        return new double[]{x + y, x - y, y - x, x * y, x / y, y / x};
    }
}
