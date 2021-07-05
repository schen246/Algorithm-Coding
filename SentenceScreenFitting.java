public class SentenceScreenFitting {
    // string - time: O(rows * largest_word.length) space: O(sum of word.length)
    public int wordsTyping(String[] sentence, int rows, int cols) {
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
}
