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
        boolean hasCycle = false;
        for (int i = 0; i < n; i++) {
            int[] next = nextDay(cells);
            String s = Arrays.toString(next);
            if (set.add(s)) {
                cnt++;
            } else {
                hasCycle = true;
                break;
            }
            cells = next;
        }
        if (hasCycle) {
            n %= cnt;
            for (int i = 0; i < n; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }
    
    private int[] nextDay(int[] cells) {
        int[] res = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            res[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return res;
    }
}
