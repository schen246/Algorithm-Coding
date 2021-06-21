import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class TheSkylineProblem {
    // M1: sorted map + max heap - time: O(nlogn) + O(n * n) space: O(n)
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Map<Integer, List<int[]>> map = new TreeMap<>();// sorted index, list of building
        for (int[] b : buildings) {
            map.putIfAbsent(b[0], new ArrayList<>());
            map.putIfAbsent(b[1], new ArrayList<>());
            map.get(b[0]).add(b);
            map.get(b[1]).add(b);
        }
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[2] - a[2]);// building
        List<List<Integer>> res = new ArrayList<>();
        for (int a : map.keySet()) {
            List<int[]> list = map.get(a);
            for (int[] b : list) {
                if (b[0] == a) {
                    maxHeap.offer(b);
                } else {
                    maxHeap.remove(b);
                }
            }
            if (maxHeap.size() == 0) {
                res.add(Arrays.asList(a, 0));
            } else {
                int maxHeight = maxHeap.peek()[2];
                if (res.size() == 0 || res.get(res.size() - 1).get(1) != maxHeight) {
                    res.add(Arrays.asList(a, maxHeight));
                }
            }
        }
        return res;
    }
}
