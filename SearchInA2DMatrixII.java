public class SearchInA2DMatrixII {
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }
}
