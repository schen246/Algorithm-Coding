public class BreakAPalindrome {
    // string - time: O(n) space: O(1)
    public String breakPalindrome(String palindrome) {
        if (palindrome.length() <= 1) return "";
        char[] arr = palindrome.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] == 'a') continue;
            arr[i] = 'a';
            return new String(arr);
        }
        arr[arr.length - 1] = 'b';
        return new String(arr);
    }
}
