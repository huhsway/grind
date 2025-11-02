import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 배열 정렬: 투 포인터를 사용하기 위한 필수 단계 (O(N log N))
        Arrays.sort(nums);
        int n = nums.length;
        
        // 초기 최솟값 설정: 첫 세 원소의 합으로 초기화합니다.
        // 문제 조건에 따라 해답은 항상 존재하므로, 초기값을 배열 내 값으로 설정하는 것이 안전합니다.
        int closestSum = nums[0] + nums[1] + nums[2];
        
        // 첫 번째 원소 (i)를 고정하는 반복문
        for (int i = 0; i < n - 2; i++) {
            // 투 포인터 설정
            int left = i + 1;       // i 다음 원소부터 시작
            int right = n - 1;      // 배열의 맨 끝에서 시작
            
            // 투 포인터가 교차하기 전까지 반복
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                // 1. 최솟값 갱신 확인
                // 현재 합과 target의 차이의 절대값 vs. 기존 closestSum과의 차이의 절대값 비교
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                
                // 2. 합 조정 및 포인터 이동
                if (sum < target) {
                    // 합이 목표보다 작다면, 합을 늘리기 위해 left 포인터를 오른쪽으로 이동
                    left++;
                } else if (sum > target) {
                    // 합이 목표보다 크다면, 합을 줄이기 위해 right 포인터를 왼쪽으로 이동
                    right--;
                } else {
                    // sum == target인 경우, 이 합이 가장 가까운 합이므로 (차이가 0) 즉시 반환
                    return sum;
                }
            }
        }
        
        return closestSum;
    }
}
