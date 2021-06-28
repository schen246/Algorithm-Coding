import java.util.List;

public class ValidWordSquare {
    // iteration - time: O(words.len * word.len) space: O(1)
    public boolean validWordSquare(List<String> words) {
        int n = words.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (j >= n || words.get(j).length() <= i || words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
