class Solution {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxArea = -1;

        while (left < right) {
            int area;

            if (height[left] < height[right]) {
                area = (right - left) * height[left];
                left++;

            } else {
                area = (right - left) * height[right];
                right--;
            }

            maxArea = Math.max(maxArea, area);

        }

        return maxArea;
        
    }
}