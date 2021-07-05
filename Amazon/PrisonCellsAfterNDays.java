package Amazon;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrisonCellsAfterNDays {
    // set check cycle - time: O(K * min(n, 2^K)) K is the number of cells space: O(K * 2^K) use set of string
    public int[] prisonAfterNDays(int[] cells, int n) {
        // check cycle
        Set<String> set = new HashSet<>();
        int cnt = 0;
        boolean found = false;
        for (int i = 1; i <= n; i++) {// min(n, 2^k) * k
            int[] next = findNext(cells);
            String cur = Arrays.toString(next);
            if (set.add(cur)) {
                cnt++;
            } else {
                found = true;
                break;
            }
            cells = next;
        }
        if (found) {
            n %= set.size();
            for (int i = 1; i <= n; i++) {
                cells = findNext(cells);
            }
        }
        return cells;
    }
    
    private int[] findNext(int[] cells) {// O(k)
        int[] res = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            res[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return res;
    }
}
