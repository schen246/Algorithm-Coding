package Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastNumberofUniqueIntegersafterKRemovals {
    // linked list hashmap - time: O(nlogn) space: O(n) depends on sort
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();// val, freq
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list, (a, b) -> a - b);// small freq first
        int res = map.size();
        for (int i = 0; i < list.size(); i++) {
            k -= list.get(i);
            if (k < 0) {
                return res;
            }
            res--;
        }
        return res;
    }
}
