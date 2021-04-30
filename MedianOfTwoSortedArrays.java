public class MedianOfTwoSortedArrays {
    public double findMedainSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        if (size % 2 == 1) {
            return (double)search(nums1, 0, nums2, 0, size / 2 + 1);
        }
        return (search(nums1, 0, nums2, 0, size / 2) + search(nums1, 0, nums2, 0, size/2 + 1)) / 2.0;
    }

    private int search(int[] a, int aLeft, int[] b, int bLeft, int k) {
        if (aLeft >= a.length) {
            return b[bLeft + k - 1];
        }
        if (bLeft >= b.length) {
            return a[aLeft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }
        int amid = aLeft + k / 2 - 1;
        int bmid = bLeft + k / 2 - 1;
        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
        if (aval <= bval) {
            return search(a, amid + 1, b, bLeft, k - k / 2);
        }
        return search(a, aLeft, b, bmid + 1, k - k / 2);
    }
}
