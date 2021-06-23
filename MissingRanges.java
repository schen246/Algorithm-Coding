import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    // iteration - time: O(n) space: O(1) not consider result
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int num : nums) {
            if (lower < num) {
                res.add(getString(lower, num - 1));
            }
            lower = num + 1;
        }
        if (lower <= upper) {
            res.add(getString(lower, upper));
        }
        return res;
    }

    private String getString(int i, int j) {
        if (i == j) {
            return String.valueOf(i);
        }
        return String.valueOf(i) + "->" + String.valueOf(j);
    }
}
