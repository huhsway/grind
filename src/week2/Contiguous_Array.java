import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        // key: 누적합 (sum), value: 해당 sum이 처음 나타난 인덱스
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        
        // 초기 조건 설정: 누적합 0은 인덱스 -1에서 시작했다고 가정 (전체 배열이 균형을 이룰 때를 대비)
        sumToIndex.put(0, -1);
        
        int maxLength = 0;
        int currentSum = 0; // 누적합 (0은 -1, 1은 +1로 계산)
        
        for (int i = 0; i < nums.length; i++) {
            // 1. 0을 -1로 변환하여 누적합 계산
            if (nums[i] == 0) {
                currentSum += -1;
            } else {
                currentSum += 1;
            }
            
            // 2. 현재 누적합이 이미 맵에 존재하는지 확인
            if (sumToIndex.containsKey(currentSum)) {
                // 이전에 같은 sum이 나타난 인덱스(j)를 가져옴
                int previousIndex = sumToIndex.get(currentSum);
                
                // 두 인덱스 사이의 부분 배열은 합이 0이므로 (0과 1의 개수가 같음)
                // 현재 길이 (i - previousIndex)를 계산하고 최대값을 업데이트
                maxLength = Math.max(maxLength, i - previousIndex);
            } else {
                // 현재 누적합이 처음 나타났다면, 현재 인덱스를 저장
                // (가장 긴 길이를 보장하기 위해, 항상 첫 번째 인덱스만 저장해야 함)
                sumToIndex.put(currentSum, i);
            }
        }
        
        return maxLength;
    }
}
