class Solution {
    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int maxValue = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxValue = Math.max(maxValue + nums[i], nums[i]);
            result = Math.max(result, maxValue);
        }

        return result;
    }
}