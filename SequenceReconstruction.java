import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        buildGraph(map, indegree, seqs);
        if (indegree.size() != org.length) {
            return false;
        }
        return bfs(map, indegree, org);
    }

    private boolean bfs(Map<Integer, List<Integer>> map, Map<Integer, Integer> indegree, int[] org) {
        Queue<Integer> q = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
        }
        int index = 0;
        while (q.size() > 0) {
            if (q.size() > 1) {
                return false;
            }
            int cur = q.poll();
            if (cur != org[index++]) {
                return false;
            }
            for (int nei : map.get(cur)) {
                if (indegree.get(nei) == 1) {
                    q.offer(nei);
                }
                indegree.put(nei, indegree.get(nei) - 1);
            }
        }
        return index == org.length;
    }

    private void buildGraph(Map<Integer, List<Integer>> map, Map<Integer, Integer> indegree, int[][] seqs) {
        for (int[] seq : seqs) {
            for (int i = 0; i < seq.length; i++) {
                map.putIfAbsent(seq[i], new ArrayList<>());
                indegree.putIfAbsent(seq[i], 0);
                if (i == 0) {
                    continue;
                }
                int pre = seq[i - 1];
                map.get(pre).add(seq[i]);
                indegree.put(seq[i], indegree.get(seq[i]) + 1);
            }
        }
    }
    // time: o(seqs.length * seq.length) + O(V + E) space: O(V)
}
