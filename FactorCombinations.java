public class FactorCombinations {
    // dfs + backtracking - time: O(branches^layers) = O(logn ^ logn) space: O(layers) = O(logn)
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 1) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        List<Integer> factors = getAllFactors(n);
        dfs(n, 0, factors, cur, res);
        return res;
    }
    
    private void dfs(int n, int index, List<Integer> factors, List<Integer> cur, List<List<Integer>> res) {
        if (n == 1) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i < factors.size(); i ++) {
            int factor = factors.get(i);
            if (n >= factor && n % factor == 0) {
                cur.add(factor);
                dfs(n / factor, i, factors, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    private List<Integer> getAllFactors(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                res.add(i);
            }
        }
        return res;
    }
}
