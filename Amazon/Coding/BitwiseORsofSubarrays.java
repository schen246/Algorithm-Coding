package Amazon;

import java.util.HashSet;
import java.util.Set;

public class BitwiseORsofSubarrays {
    // M1: bf - time: O(n^2 * n) space: O(n^2)
    // M2: pre set - time: O(n^2) space: O(n^2)
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> pre = new HashSet<>();
        for (int num : arr) {
            Set<Integer> cur = new HashSet<>();
            cur.add(num);
            for (int preNum : pre) {
                cur.add(preNum | num);
            }
            pre = cur;
            res.addAll(cur);
        }
        return res.size();
    }
}
