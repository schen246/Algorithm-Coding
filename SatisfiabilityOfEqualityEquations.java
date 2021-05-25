public class SatisfiabilityOfEqualityEquations {
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                uf.union(str.charAt(0) - 'a', str.charAt(3) - 'a');// average O(1)
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                if (uf.find(str.charAt(0) - 'a') == uf.find(str.charAt(3) - 'a')) {// average O(1)
                    return false;
                }
            }
        }
        return true;
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