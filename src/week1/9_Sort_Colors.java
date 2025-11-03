class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // If the current element is 0, swap it with the element at the 'low' pointer
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // If the current element is 1, it's in the right place relative to 0s and 2s
                mid++;
            } else { // nums[mid] == 2
                // If the current element is 2, swap it with the element at the 'high' pointer
                swap(nums, mid, high);
                high--;
                // Do NOT increment mid, as the new element at nums[mid] needs to be checked
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}