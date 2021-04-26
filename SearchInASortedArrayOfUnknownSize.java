public class SearchInASortedArrayOfUnknownSize {
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 1;
        while (reader.get(right) < target) {
            right *= 2;
        }
        left = right / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = reader.get(mid);
            if (val == target) {
                return mid;
            }
            if (val > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

interface ArrayReader {
    public int get(int index);
}