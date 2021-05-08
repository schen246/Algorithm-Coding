public class FindMedianFromDataStream {
    private PriorityQueue<Integer> maxH;
    private PriorityQueue<Integer> minH;

    public FindMedianFromDataStream() {
        maxH = new PriorityQueue<>(Collections.reverseOrder());
        minH = new PriorityQueue<>();
    }

    // M1: sort - time O(nlogn) space O(n)
    // M2: insertion sort - time O(n) space O(n)
    // M3: two heaps - time O(logn) space O(n)
    public void addNum(int num) {
        if (minH.isEmpty() || num < minH.peek()) {
            maxH.offer(num);
            if (maxH.size() > minH.size() + 1) {
                minH.offer(maxH.poll());
            }
        } else {
            minH.offer(num);
            if (minH.size() > maxH.size()) {
                maxH.offer(minH.poll());
            }
        }
    }

    public double findMedian() {
        if (maxH.size() == minH.size()) {
            return ((double)maxH.peek() + minH.peek()) * 0.5;
        }
        return (double)maxH.peek();
    }
}
