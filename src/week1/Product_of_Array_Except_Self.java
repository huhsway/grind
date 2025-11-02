class Solution {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];

        int left = 1;
        for (int i = 0; i < length; i++) {
            result[i] = left;
            left *= nums[i];
        }

        int right = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }

        return result;
    }
}