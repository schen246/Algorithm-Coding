public class SmallestRectangleEnclosingBlackPixels {
    // time: O(mlogn + nlogm) space: O(1)
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int left = findLeft(image, 0, y);// first 1
        int right = findRight(image, y, n - 1);// last 1
        int up = findUp(image, 0, x);// first 1
        int down = findDown(image, x, m - 1);// last 1
        return (right - left + 1) * (down - up + 1);
    }
    
    private int findLeft(char[][] image, int left, int right) {
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
    
    private int findRight(char[][] image, int left, int right) {
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
    
    private int findUp(char[][] image, int left, int right) {
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
    
    private int findDown(char[][] image, int left, int right) {
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
    
    private boolean valid(char[][] image, int index, boolean dir) {
        if (dir) {// left right
            for (int i = 0; i < image.length; i++) {
                if (image[i][index] == '1') {
                    return true;
                }
            }
        } else {// up down
            for (int i = 0; i < image[0].length; i++) {
                if (image[index][i] == '1') {
                    return true;
                }
            }
        }
        return false;
    }
}
