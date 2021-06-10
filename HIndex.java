public class HIndex {
    // M1: sort - time: O(nlogn) + O(n) space: O(1) depends on sorting
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int res = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= citations.length - i) {
                res = citations.length - i;
                continue;
            }
            break;
        }
        return res;
    }
    
    // M2: sort + binary search - find first occurrence i st. citations[i] >= len - i
    // time: O(nlogn) + O(logn) space: O(1) depends on sorting
    public int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int left = 0, right = citations.length - 1;
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= citations.length - mid) {
                res = citations.length - mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
