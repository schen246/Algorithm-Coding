import java.util.ArrayList;
import java.util.List;

public class NumberOfIslandsII {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[][] grid = new int[m][n];
        UnionFind uf = new UnionFind(m * n);
        int cnt = 0;
        for (int[] p : positions) {
            if (grid[p[0]][p[1]] == 1) {
                res.add(cnt);
                continue;
            }
            grid[p[0]][p[1]] = 1;
            cnt++;
            for (int[] dir : DIRS) {
                int x = p[0] + dir[0], y = p[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                    if (uf.union(p[0] * n + p[1], x * n + y)) {
                        cnt--;
                    }
                }
            }
            res.add(cnt);
        }
        return res;
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
