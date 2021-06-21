public class FindTheCelebrity extends Relation{
    // M1: indegree - time: O(n * n) space: O(n)
    public int findCelebrity(int n) {
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (knows(i, j)) {
                    indegree[j]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (indegree[i] == n) {
                return i;
            }
        }
        return -1;
    }

    // M2: greedy - time: O(n) space: O(1)
    public int findCelebrity2(int n) {
        int res = 0;
        for (int i = 1; i < n; i++) {// O(n)
            if (knows(res, i)) {
                res = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != res && knows(res, i) || !knows(i, res)) {
                return -1;
            }
        }
        return res;
    }
}

class Relation {
    private int[][] graph;
    public void setGraph(int[][] matrix) {
        graph = matrix;
    }
    public boolean knows(int a, int b) {
        if (graph[a][b] == 1)
            return true;
        return false;
    }
}