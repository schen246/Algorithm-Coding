import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, cur, res);
        return res;
    }
    
    private void dfs(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        // add
        cur.add(nums[index]);
        dfs(nums, index + 1, cur, res);
        cur.remove(cur.size() - 1);
        
        while (index + 1 < nums.length && nums[index + 1] == nums[index]) {
            index++;
        }
        // not add
        dfs(nums, index + 1, cur, res);
    }
    // time: O(2^n) space: O(n)
}
