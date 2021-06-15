import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(word, 0, 0, sb, res);// start from digit
        dfs(word, 1, 0, sb, res);// start from char
        return res;
    }

    private void dfs(String word, int level, int index, StringBuilder sb, List<String> res) {
        if (index == word.length()) {
            res.add(sb.toString());
            return;
        }
        int len = sb.length();
        for (int i = index; i < word.length(); i++) {
            if ((level & 1) == 0) {
                sb.append(String.valueOf(i - index + 1));
            } else {
                sb.append(word.substring(index, i + 1));
            }
            dfs(word, level + 1, i + 1, sb, res);
            sb.setLength(len);
        }
    }
    // time: O(2^word.length) space: O(word.length)
}
