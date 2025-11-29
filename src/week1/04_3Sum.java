import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // 1. 배열 정렬 (필수)
        Arrays.sort(nums);

        for (int standard = 0; standard < n - 2; standard++) {
            // standard 중복 건너뛰기
            if (standard > 0 && nums[standard] == nums[standard - 1]) {
                continue;
            }

            int left = standard + 1;
            int right = n - 1;

            while (left < right) {
                int total = nums[standard] + nums[left] + nums[right];

                if (total == 0) {
                    // 🎯 정답을 찾은 경우: 3Sum의 핵심 로직 유지
                    result.add(Arrays.asList(nums[standard], nums[left], nums[right]));

                    // left/right 중복 제거 로직 (다음 고유한 쌍을 찾기 위함)
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // 정답을 찾았으므로 두 포인터 모두 이동
                    left++;
                    right--;

                } else if (total < 0) {
                    // 3Sum Closest 스타일: 합이 작으면 left 증가
                    left++;
                    
                    // 3Sum Closest처럼 이동 후 중복 처리 (필요하다면)
                    // (단, 3Sum에서는 total == 0 일 때만 중복 제거하는 것이 일반적)
                    
                } else { // total > 0
                    // 3Sum Closest 스타일: 합이 크면 right 감소
                    right--;
                    
                    // 3Sum Closest처럼 이동 후 중복 처리 (필요하다면)
                }
            }
        }
        return result;
    }
}
