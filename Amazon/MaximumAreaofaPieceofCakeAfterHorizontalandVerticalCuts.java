public class MaximumAreaofaPieceofCakeAfterHorizontalandVerticalCuts {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);// O(mlogm)
        Arrays.sort(verticalCuts);// O(nlogn)
        long height = h - horizontalCuts[horizontalCuts.length - 1], prevHeight = 0;
        for (int i = 0; i < horizontalCuts.length; i++) {// O(m)
            height = Math.max(height, horizontalCuts[i] - prevHeight);
            prevHeight = horizontalCuts[i];
        }
        long width = w - verticalCuts[verticalCuts.length - 1], prevWidth = 0;
        for (int i = 0; i < verticalCuts.length; i++) {// O(n)
            width = Math.max(width, verticalCuts[i] - prevWidth);
            prevWidth = verticalCuts[i];
        }
        return (int)(height * width % 1000000007);
    }
}
