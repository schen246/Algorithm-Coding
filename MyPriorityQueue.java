import java.util.ArrayList;

public class MyPriorityQueue {
    ArrayList<Integer> arr = new ArrayList<>();

    public int size() {
        return arr.size();
    }

    public int peek() {
        return arr.get(0);
    }

    public void offer(int num) {
        arr.add(num);
        int index = arr.size() - 1;
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr.get(parent) < arr.get(index)) break;
            swap(parent, index);
            index = parent;
        }
    }

    public int poll() {
        int ans = peek();
        swap(0, arr.size() - 1);
        arr.remove(arr.size() - 1);
        int index = 0;
        while (index < arr.size()) {
            int leftIndex = (index + 1) * 2 - 1;
            int rightIndex = (index + 1) * 2;
            int minIndex = index;
            if (leftIndex < arr.size() && arr.get(leftIndex) < arr.get(minIndex)) {
                minIndex = leftIndex;
            }
            if (rightIndex < arr.size() && arr.get(rightIndex) < arr.get(minIndex)) {
                minIndex = rightIndex;
            }
            if (index == minIndex) break;
            swap(index, minIndex);
            index = minIndex;
        }
        return ans;
    }

    private void swap(int i, int j) {
        int tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }
}