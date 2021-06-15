import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int num : nums) {
            cur.add(num);
        }
        dfs(cur, 0, res);
        return res;
    }
    
    private void dfs(List<Integer> cur, int index, List<List<Integer>> res) {
        if (index == cur.size()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < cur.size(); i++) {
            if (set.add(cur.get(i))) {
                Collections.swap(cur, index, i);
                dfs(cur, index + 1, res);
                Collections.swap(cur, index, i);
            }
        }
    }
    // time: O(n!) space: O(n + n-1 + n-2 +...) = O(n^2)
}
