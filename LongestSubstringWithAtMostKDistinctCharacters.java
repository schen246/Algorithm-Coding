public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // - Clarification:
        // lower case: A/a !!! -> same
        // - Assumption:
        // length/size: fit memory, within integer range
        // - Method: sliding window -> two pointers
        // - Data Structure: Map<Character, Integer> map -> map.size() 
        // - Example:
        // s = "eceba", k = 2
        //         i
        //           j
        // res    3
        // - Time: O(n) Space: O(n)
        // - Testing
        
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0, res = 0;
        while (j < s.length()) {
            // add j
            char c1 = s.charAt(j);
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            while (map.size() > k) {
                // remove i
                char c2 = s.charAt(i);
                Integer n2 = map.get(c2);
                if (n2 == 1) {
                    map.remove(c2);
                } else {
                    map.put(c2, n2 - 1);
                }
                i++;
            }
            j++;
            res = Math.max(res, j - i);
        }
        return res;
    }
}
