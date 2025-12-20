class Solution {
    public int search(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length-1;

        return binarySearch(nums, left, right, target);
    }

    private int binarySearch(int[] nums, int left, int right, int target) {

        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            }
        }

        return -1;

    }
}