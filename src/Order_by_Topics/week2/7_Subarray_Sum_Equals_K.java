import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * LeetCode 560. Subarray Sum Equals K 문제의 O(n) 풀이
     * 누적합(Prefix Sum)과 HashMap을 사용합니다.
     *
     * @param nums 정수 배열
     * @param k 찾고자 하는 부분 배열의 합
     * @return 합이 k와 같은 부분 배열의 총 개수
     */
    public int subarraySum(int[] nums, int k) {
        // key: 누적합 값, value: 해당 누적합이 나타난 횟수
        Map<Integer, Integer> prefixSumCounts = new HashMap<>();
        
        // 초기 조건: 배열 시작 전(인덱스 -1)의 누적합은 0이며, 이는 1번 존재한다.
        prefixSumCounts.put(0, 1);
        
        int currentSum = 0; // 현재까지의 누적합
        int count = 0;      // 합이 k인 부분 배열의 총 개수

        for (int num : nums) {
            // 1. 현재 누적합 업데이트
            currentSum += num;

            // 2. 목표 과거 누적합 (target) 계산
            // target = currentSum - k
            int targetSum = currentSum - k;

            // 3. HashMap에서 targetSum이 몇 번 나타났는지 확인
            // 나타난 횟수만큼 현재 인덱스에서 끝나는 유효한 부분 배열이 존재함
            if (prefixSumCounts.containsKey(targetSum)) {
                count += prefixSumCounts.get(targetSum);
            }

            // 4. 현재 누적합을 맵에 기록 또는 횟수 업데이트
            // getOrDefault를 사용하여 맵에 있으면 횟수+1, 없으면 1로 초기화
            prefixSumCounts.put(currentSum, prefixSumCounts.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}
