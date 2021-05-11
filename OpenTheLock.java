public class OpenTheLock {
    // time: O(L^2 * 10^L) space: O(10^L)
    public int openLock(String[] deadends, String target) {
        // assume: all string 4-digits, target not inside deadends
        Set<String> d = new HashSet<>(Arrays.asList(deadends));
        if (d.contains("0000")) return -1;
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        if ("0000".equals(target)) return 0;
        visited.add("0000");
        q.offer("0000");
        int depth = 0;
        while (q.size() > 0) {
            int size = q.size();
            depth++;
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                for (String s : getNeighbors(d, cur)) {
                    if (s.equals(target)) return depth;
                    if (visited.add(s)) {
                        q.offer(s);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> getNeighbors(Set<String> d, String cur) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < cur.length(); i++) {//O(L)
            char[] arr = cur.toCharArray();
            for (char c : getChars(arr[i])) {//O(2)
                arr[i] = c;
                String s = new String(arr);//O(L)
                if (!d.contains(s)) {
                    res.add(s);
                }
            }
        }
        return res;
    }
    
    private List<Character> getChars(char c) {
        List<Character> res = new ArrayList<>();
        if (c == '0') {
            res.add('1');
            res.add('9');
        } else if (c == '9') {
            res.add('0');
            res.add('8');
        } else {
            res.add((char)(c + 1));
            res.add((char)(c - 1));
        }
        return res;
    }
}
