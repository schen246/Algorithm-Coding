public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);//time: O(n) space: O(n)
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {// average O(1)
                return edge;
            }
        }
        return new int[]{-1, -1};
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int len) {
        parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }
    }

    private int find(int p) {
        if (parent[p] == p) {
            return p;
        }
        parent[p] = find(parent[p]);
        return parent[p];
    }

    public boolean union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i != j) {
            parent[i] = j;
            return true;
        }
        return false;
    }
}