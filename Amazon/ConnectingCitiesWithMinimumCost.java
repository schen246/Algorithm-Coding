public class ConnectingCitiesWithMinimumCost {
    // sort as weight
    // union find
    // time: O(mlogm) + O(m) * O(logn) union find space: O(n)
    public int minimumCost(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        int res = 0;
        UnionFind uf = new UnionFind(n);
        for (int[] connection : connections) {
            int n1 = connection[0], n2 = connection[1];
            if (uf.find(n1) != uf.find(n2)) {
                res += connection[2];
                uf.union(n1, n2);
                n--;
            }
        }
        return n == 1 ? res : -1;
    }
}

class UnionFind {
    int[] parent;
    public UnionFind(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int p) {// O(logn) worst case, average O(1)
        if (p == parent[p]) {
            return p;
        }
        parent[p] = find(parent[p]);
        return parent[p];
    }
    
    public void union(int p, int q) {// O(logn) worst case, average O(1)
        int i = find(p);
        int j = find(q);
        if (i != j) {
            parent[i] = j;
        }
    }
}