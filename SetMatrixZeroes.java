import java.util.HashSet;
import java.util.Set;

public class SetMatrixZeroes {
    // M1: new matrix - O(m * n) space
    // M2: two sets - O(k) space, k is the number of zeroes
    public void setZeros(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        int m = matrix.length, n = matrix[0].length;
        // find zeros
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        // set zeros
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
