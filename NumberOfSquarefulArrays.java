public class NumberOfSquarefulArrays {
    public int numSquarefulPerms(int[] A) {
        int[] res = new int[1];
        Arrays.sort(A);
        dfs(A, 0, res);
        return res[0];
    }

    private void dfs(int[] A, int index, int[] res) {
        if (index == A.length) {
            res[0]++;
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < A.length; i++) {
            if (set.add(A[i])) {
                swap(A, i, index);
                if (valid(A, index)) {
                    dfs(A, index + 1, res);
                }
                swap(A, i, index);
            }
        }
    }

    private boolean valid(int[] A, int i) {
        if (i == 0) {
            return true;
        }
        double res = Math.sqrt(A[i] + A[i - 1]);
        return res == Math.floor(res);
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    // time: O(n!) space: O(n)
}
