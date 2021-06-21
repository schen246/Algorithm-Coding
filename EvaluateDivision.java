import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class EvaluateDivision {
    // M1: bfs (queue) - time: O(queries.length * nodes) space: O(nodes)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair>> map = new HashMap<>();// from, list of <to, double from / to>
        for (int i = 0; i < equations.size(); i++) {
            String s1 = equations.get(i).get(0), s2 = equations.get(i).get(1);
            map.putIfAbsent(s1, new ArrayList<>());
            map.get(s1).add(new Pair(s2, values[i]));
            map.putIfAbsent(s2, new ArrayList<>());
            map.get(s2).add(new Pair(s1, 1.0 / values[i]));
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            res[i] = bfs(queries.get(i), map);
        }
        return res;
    }
    
    private double bfs(List<String> query, Map<String, List<Pair>> map) {
        String src = query.get(0), dst = query.get(1);
        if (!map.containsKey(src) || !map.containsKey(dst)) {
            return -1.0;
        }
        if (src.equals(dst)) {
            return 1.0;
        }
        Queue<Pair> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        q.offer(new Pair(src, 1.0));
        set.add(src);
        while (q.size() > 0) {
            Pair cur = q.poll();
            String param = cur.param;
            double val = cur.val;
            for (Pair next : map.get(param)) {
                if (next.param.equals(dst)) {
                    return val * next.val;
                }
                if (set.add(next.param)) {
                    q.offer(new Pair(next.param, val * next.val));
                }
            }
        }
        return -1.0;
    }

    class Pair {
        String param;
        double val;
        public Pair(String param, double val) {
            this.param = param;
            this.val = val;
        }
    }
}
