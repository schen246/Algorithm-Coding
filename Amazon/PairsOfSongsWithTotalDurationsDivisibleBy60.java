public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();// val, freq
        int res = 0;
        for (int cur : time) {
            cur %= 60;
            res += map.getOrDefault(cur == 0 ? 0 : 60 - cur, 0);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        return res;
    }
    // time: O(n) space: O(n)
}
