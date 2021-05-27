public class FindMedianFromDataStream {
    PriorityQueue<Integer> smaller;
    PriorityQueue<Integer> larger;
    
    public MedianFinder() {
        smaller = new PriorityQueue<>((a, b) -> (b - a));// maxHeap
        larger = new PriorityQueue<>();// minHeap
    }
    
    public void addNum(int num) {
        if (smaller.size() == 0) {
            smaller.add(num);
            return;
        }
        if (num <= smaller.peek()) {
            smaller.offer(num);
        } else {
            larger.offer(num);
        }
        while (smaller.size() > larger.size() + 1) {
            larger.offer(smaller.poll());
        }
        while (larger.size() > smaller.size()) {
            smaller.offer(larger.poll());
        }
    }
    
    public double findMedian() {
        if (smaller.size() == 0) {
            return 0.0;
        }
        if (smaller.size() > larger.size()) {
            return smaller.peek() * 1.0;
        }
        return (smaller.peek() + larger.peek()) / 2.0;
    }

    // M1: sort - time O(nlogn) space O(n)
    // M2: insertion sort - time O(n) space O(n)
    // M3: two heaps - time O(logn) space O(n)
}
