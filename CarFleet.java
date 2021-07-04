import java.util.Arrays;

public class CarFleet {
    // sort - time: O(nlogn) space: O(n)
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) return 0;
        Item[] items = new Item[n];
        for (int i = 0; i < position.length; i++) {
            items[i] = new Item(position[i], (target - position[i] + 0.0) / speed[i]);
        }
        Arrays.sort(items, (a, b) -> a.position - b.position);
        int res = 1;
        double curTime = items[n - 1].time;
        for (int i = n - 2; i >= 0; i--) {
            if (items[i].time <= curTime) continue;
            curTime = items[i].time;
            res++;
        }
        return res;
    }
    
    class Item {
        int position;
        double time;
        public Item(int position, double time) {
            this.position = position;
            this.time = time;
        }
    }
}
