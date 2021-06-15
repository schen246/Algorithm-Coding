package Amazon;

import java.util.HashMap;
import java.util.Map;

public class RomantoInteger {
    // time: O(n) n is small, so O(1) space: O(1)
    Map<Character, Integer> map = new HashMap<>();
    {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }
    public int romanToInt(String s) {
        char lastChar = s.charAt(s.length() - 1);
        int lastVal = map.get(lastChar);
        int res = lastVal;
        for (int i = s.length() - 2; i >= 0; i--) {
            char curChar = s.charAt(i);
            int curVal = map.get(curChar);
            if (curVal < lastVal) {
                res -= curVal;
            } else {
                res += curVal;
            }
            lastVal = curVal;
        }
        return res;
    }

    public int romanToInt2(String s) {
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            char curChar = s.charAt(i);
            int curVal = map.get(curChar);
            if (i + 1 < s.length()) {
                char nextChar = s.charAt(i + 1);
                int nextVal = map.get(nextChar);
                if (curVal < nextVal) {
                    res += (nextVal - curVal);
                    i += 2;
                    continue;
                }
            }
            res += curVal;
            i += 1;
        }
        return res;
    }
}
