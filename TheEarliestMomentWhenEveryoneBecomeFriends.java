public class TheEarliestMomentWhenEveryoneBecomeFriends {
    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, (a, b) -> {
            return a[0] - b[0];
        });
        UnionFind uf = new UnionFind(N);// time: O(N) space: O(N)
        for (int[] log : logs) {
            if (uf.union(log[1], log[2])) {// average O(1)
                N--;
                if (N == 1) {
                    return log[0];
                }
            }
        }
        return -1;
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