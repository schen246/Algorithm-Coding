import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinCostToConnectAllPoints {
    class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {// average O(1)
            if (parent[p] == p) {
                return p;
            }
            parent[p] = find(parent[p]);
            return parent[p];
        }
        
        public boolean union(int p, int q) {// average O(1)
            int i = find(p);
            int j = find(q);
            if (i != j) {
                parent[j] = i;
                return true;
            }
            return false;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(new int[]{Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]), i, j});
            }
        }
        Collections.sort(edges, (a, b) -> a[0] - b[0]);// O(nlogn)
        UnionFind uf = new UnionFind(points.length);// O(n) time, O(n) space
        int res = 0;
        for (int[] edge : edges) {
            if (uf.union(edge[1], edge[2])) {// average O(1)
                res += edge[0];
            }
        }
        return res;
    }
}
