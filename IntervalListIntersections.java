public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return new int[0][0];
        }
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i][1] < B[j][0]) {
                i++;
            } else if (A[i][0] > B[j][1]) {
                j++;
            } else {
                res.add(new int[]{Math.max(A[i][0], B[j][0]), Math,min(A[i][1], B[j][1])});
                if (A[i][1] < B[j][1]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    // time: O(n) space: O(1) not consider List<int[]> res since any solution will need it
}
