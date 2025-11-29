import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        
        int closestSum = nums[0] + nums[1] + nums[2];
        
        for (int standard = 0; standard < n - 2; standard++) {
            // 중복 제거: i
            if (standard > 0 && nums[standard] == nums[standard - 1]) {
                continue;
            }
            
            int left = standard + 1;
            int right = n - 1;
            
            while (left < right) {
                int total = nums[standard] + nums[left] + nums[right];
                
                // 최솟값 갱신
                if (Math.abs(total - target) < Math.abs(closestSum - target)) {
                    closestSum = total;
                }
                
                if (total == target) {
                    // 정확히 target과 같으면 즉시 반환
                    return total;
                } else if (total < target) {
                    // 중복 제거: left
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                } else {
                    // 중복 제거: right
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                }
            }
        }
        
        return closestSum;
    }
}