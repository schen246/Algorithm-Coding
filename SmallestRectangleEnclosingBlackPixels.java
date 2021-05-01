public class SmallestRectangleEnclosingBlackPixels {
    // time: O(mlogn + nlogm) space: O(1)
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        int left = searchColFirst(image, 0, y);
        int right = searchColLast(image, y, n - 1);
        int top = searchRowFirst(image, 0, x);
        int bottom = searchRowLast(image, x, m - 1);
        return (right - left + 1) * (bottom - top + 1);
    }
    
    private int searchColFirst(char[][] image, int left, int right) {
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (valid(image, mid, true)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    
    private int searchColLast(char[][] image, int left, int right) {
        int res = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (valid(image, mid, true)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
    
    private int searchRowFirst(char[][] image, int left, int right) {
        int res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (valid(image, mid, false)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    
    private int searchRowLast(char[][] image, int left, int right) {
        int res = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (valid(image, mid, false)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
    
    private boolean valid(char[][] image, int num, boolean type) {
        if (type) {
            for (int i = 0; i < image.length; i++) {
                if (image[i][num] == '1') {
                    return true;
                }
            }
            return false;
        } else {
            for (int i = 0; i < image[0].length; i++) {
                if (image[num][i] == '1') {
                    return true;
                }
            }
            return false;
        }
    }
}
