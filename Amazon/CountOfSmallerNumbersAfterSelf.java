public class CountOfSmallerNumbersAfterSelf {
    // divide and conquer - mergeSort
    // time: O(nlogn) space: O(n)
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(nums[i], i);
        }
        int[] count = new int[n];
        Item[] help = new Item[n];
        mergeSort(items, 0, n - 1, count, help);
        List<Integer> res = new ArrayList<>();
        for (int c : count) {
            res.add(c);
        }
        return res;
    }

    private void mergeSort(Item[] items, int left, int right, int[] count, Item[] help) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(items, left, mid, count, help);
        mergeSort(items, mid + 1, right, count, help);
        merge(items, left, mid, right, count, help);
    }

    private void merge(Item[] items, int left, int mid, int right, int[] count, Item[] help) {
        int i = left, j = mid + 1, k = left;
        int cnt = 0;
        while (i <= mid && j <= right) {
            if (items[i].val > items[j].val) {
                help[k++] = items[j++];
                cnt++;
            } else {
                count[items[i].index] += cnt;
                help[k++] = items[i++];
            }
        }
        while (i <= mid) {
            count[items[i].index] += cnt;
            help[k++] = items[i++];
        }
        while (j <= right) {
            help[k++] = items[j++];
        }
        for (int index = left; index <= right; index++) {
            items[index] = help[index];
        }
    }
}

class Item {
    int val;
    int index;

    public Item(int val, int index) {
        this.val = val;
        this.index = index;
    }
}