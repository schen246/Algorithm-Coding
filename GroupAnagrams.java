public class GroupAnagrams {
    // Map<String, List<String>> - time: O(n * L log L) space: O(nL)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cur = str.toCharArray();
            Arrays.sort(cur);
            String s = new String(cur);
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(str);
        }
        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}
