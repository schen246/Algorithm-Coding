import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CheckingExistenceOfEdgeLengthLimitedPaths {
    // M1: bfs - time: O(N * (V + E)), space: O(V + E)
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        // build graph
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashMap<>());
        }
        for (int[] edge : edgeList) {
            int n1 = edge[0], n2 = edge[1], w = edge[2];
            Map<Integer, Integer> cur1 = map.get(n1);
            cur1.put(n2, Math.min(w, cur1.getOrDefault(n2, Integer.MAX_VALUE)));
            Map<Integer, Integer> cur2 = map.get(n2);
            cur2.put(n1, Math.min(w, cur2.getOrDefault(n1, Integer.MAX_VALUE)));
        }
        // bfs
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = bfs(queries[i], map);
        }
        return res;
    }
    
    private boolean bfs(int[] query, Map<Integer, Map<Integer, Integer>> map) {
        if (query[0] == query[1]) {
            return true;
        }
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        q.offer(query[0]);
        set.add(query[0]);
        while (q.size() > 0) {
            int cur = q.poll();
            Map<Integer, Integer> neiMap = map.get(cur);
            for (int nei : neiMap.keySet()) {
                if (neiMap.get(nei) < query[2]) {
                    if (nei == query[1]) {
                        return true;
                    }
                    if (set.add(nei)) {
                        q.offer(nei);
                    }
                }
            }
        }
        return false;
    }

    // M2: union find - time: O(NlogN) + O(MlogM) + O(V + E) space: O(n)
    public boolean[] distanceLimitedPathsExist2(int n, int[][] edgeList, int[][] queries) {
        int N = queries.length, M = edgeList.length;
        int[][] q = new int[N][4];
        for (int i = 0; i < N; i++) {
            q[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(q, (a, b) -> a[2] - b[2]);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        boolean[] res = new boolean[N];
        UnionFind uf = new UnionFind(n);
        int j = 0;
        for (int i = 0; i < N; i++) {
            while (j < M && edgeList[j][2] < q[i][2]) {
                uf.union(edgeList[j][0], edgeList[j][1]);// average O(1)
                j++;
            }
            res[q[i][3]] = uf.find(q[i][0]) == uf.find(q[i][1]);// average O(1)
        }
        return res;
    }
    
    class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {
            if (parent[p] == p) {
                return p;
            }
            parent[p] = find(parent[p]);
            return parent[p];
        }
        
        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i != j) {
                parent[i] = j;
            }
        }
    }
}
