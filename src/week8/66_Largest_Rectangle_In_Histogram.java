import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        // Add a sentinel value (0) at the end to handle remaining bars in the stack
        int[] extendedHeights = new int[n + 1];
        System.arraycopy(heights, 0, extendedHeights, 0, n);
        extendedHeights[n] = 0;
        
        for (int i = 0; i <= n; i++) {
            // While the stack is not empty and the current height is smaller than the height at the top of the stack
            while (!stack.isEmpty() && extendedHeights[i] < extendedHeights[stack.peek()]) {
                // Pop the top of the stack
                int h = extendedHeights[stack.pop()];
                
                // Calculate the width. The new top of the stack is the left boundary.
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                
                maxArea = Math.max(maxArea, h * w);
            }
            // Push the current index onto the stack
            stack.push(i);
        }
        
        return maxArea;
    }
}