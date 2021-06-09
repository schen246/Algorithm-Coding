public class LongestMountainInArray {
    // two pointers - time: O(n) space: O(1)
    public int longestMountain(int[] A) {
        int res = 0;
        int increase = 0, decrease = 0;
        int j = 1;
        while (j < A.length) {
            increase = 0;
            decrease = 0;
            while (j < A.length && A[j] == A[j - 1]) {
                j++;
            }
            while (j < A.length && A[j] > A[j - 1]) {
                j++;
                increase++;
            }
            while (j < A.length && A[j] < A[j - 1]) {
                j++;
                decrease++;
            }
            if (increase > 0 && decrease > 0) {
                res = Math.max(res, increase + decrease + 1);
            }
        }
        return res;
    }    
}
