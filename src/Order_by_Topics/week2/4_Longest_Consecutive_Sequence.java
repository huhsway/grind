import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * LeetCode 128. Longest Consecutive Sequence 문제의 O(n) 풀이
     * HashSet을 사용하여 연속된 시퀀스의 길이를 찾습니다.
     *
     * @param nums 정렬되지 않은 정수 배열
     * @return 가장 긴 연속된 요소 시퀀스의 길이
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1. 모든 요소를 HashSet에 추가 (중복 제거 및 O(1) 탐색 보장)
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 2. Set의 각 요소를 순회하며 시퀀스 시작점을 찾고 길이를 측정
        for (int num : numSet) {
            
            // 현재 숫자(num)가 시퀀스의 시작점인지 확인
            // -> num - 1 이 Set에 없으면 num이 시퀀스의 시작점입니다.
            if (!numSet.contains(num - 1)) {
                
                int currentNum = num;
                int currentStreak = 1;

                // 시퀀스 확장: currentNum + 1, + 2, ... 를 Set에서 찾습니다.
                // 각 숫자가 while에서 최대 1번만 확인되기 때문에 O(1)이라고 본다
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // 최대 길이 업데이트
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
