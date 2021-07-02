package Amazon;

public class CompareVersionNumbers {
    // time: O(m + n + max(m, n)) space: O(m + n)
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int len = Math.max(arr1.length, arr2.length);
        for (int i = 0; i < len; i++) {
            String a = i < arr1.length ? arr1[i] : "0";
            String b = i < arr2.length ? arr2[i] : "0";
            int cur = compare(a, b);
            if (cur != 0) {
                return cur;
            }
        }
        return 0;
    }
    
    private int compare(String a, String b) {
        int x = Integer.valueOf(a);
        int y = Integer.valueOf(b);
        return x == y ? 0 : x < y ? -1 : 1;
    }
}
