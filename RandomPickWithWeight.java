public class RandomPickWithWeight {
    private int[] prefixSums;
    private int sum;

    public RandomPickWithWeight(int[] w) {
        prefixSums = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            prefixSums[i] = sum;
        }
    }

    public int pickIndex() {
        double target = sum * Math.random();
        // find smallest index st. prefixSums[index] >= target
        int left = 0, right = prefixSums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (prefixSums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return prefixSums[left] >= target ? left : right;
    }
}
