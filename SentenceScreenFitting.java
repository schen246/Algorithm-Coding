public class SentenceScreenFitting {
    // M2: greedy - cnt total number of chars put on the screen
    // time: O(rows * largest_word.length) space: O(sum of word.length)
    public int wordsTyping2(String[] sentence, int rows, int cols) {
        StringBuilder sb = new StringBuilder();
        for (String word : sentence) {
            sb.append(word + " ");
        }
        String str = sb.toString();
        int len = str.length();
        int index = 0;// number of chars have been put to the screen
        for (int i = 0; i < rows; i++) {
            index += cols;
            if (str.charAt(index % len) == ' ') {
                index++;
            } else {
                while (index - 1 >= 0 && str.charAt((index - 1) % len) != ' ') {
                    index--;
                }
            }
        }
        return index / len;
    }

    // M1: bf - time: O(rows * cols / word.length) space: O(1)
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        int index = 0, cnt = 0;
        for (int row = 0; row < rows; row++) {
            int j = 0;
            while (j < cols && j + sentence[index].length() <= cols) {
                j += sentence[index].length() + 1;
                index++;
                if (index == n) {
                    cnt++;
                    index = 0;
                }
            }
        }
        return cnt;
    }
}
